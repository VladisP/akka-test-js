package lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import lab4.messages.TestMessage;

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
                                    return complete("coming soon...");
                                })
                        )
                )
        );
    }
}
