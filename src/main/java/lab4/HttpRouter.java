package lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import lab4.messages.GetResultMessage;
import lab4.messages.TestMessage;

import java.util.concurrent.Future;

public class HttpRouter extends AllDirectives {

    private static final String TEST_STARTED_MESSAGE = "Test started!";

    public HttpRouter() {
    }

    public Route createRoute(ActorRef rootActor) {
        return route(
                path("test", () ->
                        post(() ->
                                entity(Jackson.unmarshaller(TestMessage.class), msg -> {
                                    rootActor.tell(msg, ActorRef.noSender());
                                    return complete(TEST_STARTED_MESSAGE);
                                })
                        )
                ),
                path("result", () ->
                        get(() ->
                                parameter("packageId", packageId -> {
                                    Future<Object> result = Patterns.ask(rootActor, new GetResultMessage(packageId), 5000);
                                    return complete("coming soon...");
                                })
                        )
                )
        );
    }
}
