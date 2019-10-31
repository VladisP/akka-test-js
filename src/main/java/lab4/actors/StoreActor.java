package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.messages.GetResultsMessage;
import lab4.messages.ResponseMessage;
import lab4.entities.TestResult;

import java.util.*;

public class StoreActor extends AbstractActor {

    private Map<String, List<TestResult>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, msg -> {
                    store.computeIfAbsent(msg.getPackageId(), k -> new ArrayList<>());
                    store.get(msg.getPackageId()).add(msg);
                    System.out.println(msg.getTestName() + " записан в хранилище!");
                })
                .match(GetResultsMessage.class, msg -> {
                    List<TestResult> testResults = store.get(msg.getPackageId());
                    testResults.sort(Comparator.comparing(TestResult::getTestName));
                    ResponseMessage response = new ResponseMessage(msg.getPackageId(), true, testResults.size());

                    for (int i = 0; i < testResults.size(); i++) {
                        TestResult testResult = testResults.get(i);
                        if (!testResult.isSuccessful()) {
                            response.setSuccessful(false);
                        }
                    }

                    sender().tell(response, self());
                })
                .build();
    }
}
