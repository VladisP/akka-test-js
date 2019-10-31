package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StoreActor extends AbstractActor {

    private Map<>

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResultMessage.class, msg -> {

                })
                .build();
    }
}
