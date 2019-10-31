package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.messages.TestResultMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .match()
                .build();
    }
}
