package lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class RootActor extends AbstractActor {

    private ActorRef storeActor = getContext().actorOf()

    @Override
    public Receive createReceive() {
        return null;
    }
}
