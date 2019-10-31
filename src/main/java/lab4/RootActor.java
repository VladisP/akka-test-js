package lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class RootActor extends AbstractActor {

    private ActorRef storeActor = getContext().actorOf(Props.create(StoreActor.class));
    private ActorRef testRouter = getContext().actorOf(new RoundRobinPool())

    @Override
    public Receive createReceive() {
        return null;
    }
}
