package lab4.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;
import lab4.messages.GetResultMessage;
import lab4.messages.Test;
import lab4.messages.TestMessage;

public class RootActor extends AbstractActor {

    private static final int POOL_SIZE = 5;

    private ActorRef storeActor = getContext().actorOf(Props.create(StoreActor.class), "storeActor");
    private ActorRef testRouter = getContext().actorOf(
            new RoundRobinPool(POOL_SIZE)
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
                .match(GetResultMessage.class, msg -> {
                    storeActor.tell(msg, self());
                    System.out.println("Запрошены результаты тестов для packageId");
                })
                .build();
    }
}
