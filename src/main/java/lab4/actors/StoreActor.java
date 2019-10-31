package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.messages.GetResultMessage;
import lab4.messages.ResponseMessage;
import lab4.messages.TestResult;
import lab4.messages.TestResultMessage;

import java.util.*;

public class StoreActor extends AbstractActor {

    private Map<String, List<TestResultMessage>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResultMessage.class, msg -> {
                    store.computeIfAbsent(msg.getPackageId(), k -> new ArrayList<>());
                    store.get(msg.getPackageId()).add(msg);
                    System.out.println(msg.getTest().getTestName() + " записан в хранилище!");
                })
                .match(GetResultMessage.class, msg -> {
                    List<TestResultMessage> testResultMessages = store.get(msg.getPackageId());
                    testResultMessages.sort(Comparator.comparing(o -> o.getTest().getTestName()));
                    ResponseMessage response = new ResponseMessage(msg.getPackageId(), true, testResultMessages.size());

                    for (int i = 0; i < testResultMessages.size(); i++) {
                        TestResultMessage testResultMessage = testResultMessages.get(i);
                        if (!testResultMessage.isSuccessful()) {
                            response.setSuccessful(false);
                        }
                        response.getTestResults()[i] = new TestResult(
                                testResultMessage.getTest().getTestName(),
                                testResultMessage.isSuccessful(),
                                testResultMessage.getResult(),
                                testResultMessage.getTest().getExpectedResult(),
                                testResultMessage.getTest().getParams()
                        );
                    }

                    sender().tell(response, self());
                })
                .build();
    }
}
