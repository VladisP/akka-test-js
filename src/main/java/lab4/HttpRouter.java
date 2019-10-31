package lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import lab4.messages.GetResultMessage;
import lab4.messages.TestMessage;
import scala.concurrent.Future;

public class HttpRouter extends AllDirectives {

    private static final String TEST_STARTED_MESSAGE = "Test started!";
    private static final String TEST_PATH = "test";
    private static final String RESULT_PATH = "result";
    private static final String PARAMETER_PACKAGE_ID = "packageId";
    private static final int TIMEOUT = 5000;

    public HttpRouter() {
    }

    public Route createRoute(ActorRef rootActor) {
        return route(
                path(TEST_PATH, () ->
                        post(() ->
                                entity(Jackson.unmarshaller(TestMessage.class), msg -> {
                                    rootActor.tell(msg, ActorRef.noSender());
                                    return complete(TEST_STARTED_MESSAGE);
                                })
                        )
                ),
                path(RESULT_PATH, () ->
                        get(() ->
                                parameter(PARAMETER_PACKAGE_ID, packageId -> {
                                    Future<Object> result = Patterns.ask(rootActor, new GetResultMessage(packageId), TIMEOUT);
                                    return completeOKWithFuture(result, Jackson.marshaller());
                                })
                        )
                )
        );
    }
}
