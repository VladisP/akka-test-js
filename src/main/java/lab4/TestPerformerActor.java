package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestPerformerActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, msg -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
                    engine.eval()

                    for (Test test : msg.getTests()) {
                        testRouter.tell(new TestMessage(
                                        msg.getPackageId(),
                                        msg.getJsScript(),
                                        msg.getFunctionName(),
                                        new Test[]{test}),
                                self()
                        );
                    }
                })
                .build();
    }
}
