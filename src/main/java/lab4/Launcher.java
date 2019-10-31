package lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.AllDirectives;
import akka.stream.ActorMaterializer;

public class Launcher extends AllDirectives {

    private static final String ACTOR_SYSTEM_NAME = "test-js";

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME);
        ActorRef rootActor = system.actorOf(Props.create(RootActor.class));

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        
    }
}
