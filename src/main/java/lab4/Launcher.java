package lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Launcher {

    private static final String ACTOR_SYSTEM_NAME = "test-js";

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME);
        ActorRef rootActor = system.actorOf(Props.create(RootActor.class));
    }
}
