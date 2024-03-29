package lab4.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;
import lab4.Launcher;
import lab4.messages.GetResultsMessage;
import lab4.entities.Test;
import lab4.messages.TestMessage;

public class RootActor extends AbstractActor {

    private static final int INITIAL_POOL_SIZE = 3;

    private ActorRef storeActor = getContext().actorOf(Props.create(StoreActor.class), Launcher.STORE_ACTOR_NAME);
    private ActorRef testRouter = getContext().actorOf(
            new RoundRobinPool(INITIAL_POOL_SIZE)
                    .props(Props.create(TestPerformerActor.class))
    );

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, msg -> {
                    for (Test test : msg.getTests()) {
                        testRouter.tell(new TestMessage(
                                        msg.getPackageId(),
                                        msg.getJsScript(),
                                        msg.getFunctionName(),
                                        new Test[]{test}),
                                self()
                        );
                        System.out.println(test.getTestName() + " запущен!");
                    }
                })
                .match(GetResultsMessage.class, msg -> {
                    storeActor.tell(msg, sender());
                    System.out.println("Запрошены результаты тестов для packageId=" + msg.getPackageId());
                })
                .build();
    }
}
