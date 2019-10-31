package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.Map;

public class StoreActor extends AbstractActor {

    private Map<String, TestResultMessage> store = 

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResultMessage.class, msg -> {

                })
                .build();
    }
}
