package lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

public class RootActor extends AbstractActor {

    private static final int POOL_SIZE = 5;

    private ActorRef storeActor = getContext().actorOf(Props.create(StoreActor.class));
    private ActorRef testRouter = getContext().actorOf(
            new RoundRobinPool(POOL_SIZE)
                    .props(Props.create(TestPerformerActor.class))
    );

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class)
    }
}
