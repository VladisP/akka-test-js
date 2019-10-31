package lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import lab4.actors.RootActor;

import java.util.concurrent.CompletionStage;

public class Launcher {

    public static final String ROOT_ACTOR_NAME = "rootActor";
    public static final String STORE_ACTOR_NAME = "storeActor";

    private static final String ACTOR_SYSTEM_NAME = "test-js";
    private static final String HOST_NAME = "localhost";
    private static final String START_MESSAGE = "Server online at http://localhost:8080/\nPress RETURN to stop...";
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME);
        ActorRef rootActor = system.actorOf(Props.create(RootActor.class), ROOT_ACTOR_NAME);

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        HttpRouter httpRouter = new HttpRouter();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                httpRouter.createRoute(rootActor).flow(system, materializer);

        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST_NAME, PORT),
                materializer
        );

        System.out.println(START_MESSAGE);
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }
}
