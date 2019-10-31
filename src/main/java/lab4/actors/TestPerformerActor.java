package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.messages.Test;
import lab4.messages.TestMessage;
import lab4.messages.TestResultMessage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestPerformerActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, msg -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
                    engine.eval(msg.getJsScript());
                    Invocable invocable = (Invocable) engine;
                    Test test = msg.getTests()[0];
                    Object[] params = test.getParams();
                    String result = invocable.invokeFunction(msg.getFunctionName(), params).toString();

                    getContext().actorSelection("/user/rootActor/storeActor")
                            .tell(new TestResultMessage(
                                            msg.getPackageId(),
                                            result.equals(test.getExpectedResult()),
                                            result,
                                            test
                                    ),
                                    self()
                            );

                    System.out.println(test.getTestName() + " завершен!");
                })
                .build();
    }
}
