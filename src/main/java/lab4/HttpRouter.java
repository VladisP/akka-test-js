package lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

public class HttpRouter extends AllDirectives {

    private Route createRoute(ActorRef rootActor) {
        return route(
                path("test", () ->
                        post(() ->
                                entity(Jackson.unmarshaller(TestMessage.class), msg -> {
                                    //rootActor.tell(msg, ActorRef.noSender());
                                    return complete(TEST_STARTED_MESSAGE);
                                })
                        )
                ),
                path("result", () ->
                        get(() ->
                                parameter("key", packageId ->
                                        parameter("value", value -> {
                                            return complete("coming soon...");
                                        })
                                )
                        )
                )
        );
    }
}
