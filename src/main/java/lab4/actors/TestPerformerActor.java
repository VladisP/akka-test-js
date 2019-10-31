package lab4.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4.Launcher;
import lab4.entities.Test;
import lab4.entities.TestResult;
import lab4.messages.TestMessage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestPerformerActor extends AbstractActor {

    private static final String ENGINE_NAME = "nashorn";
    private static final String PATH_TO_STORE_ACTOR = "/user/" + Launcher.ROOT_ACTOR_NAME + "/" + Launcher.STORE_ACTOR_NAME;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, msg -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
                    engine.eval(msg.getJsScript());
                    Invocable invocable = (Invocable) engine;
                    Test test = msg.getTests()[0];
                    Object[] params = test.getParams();
                    String result = invocable.invokeFunction(msg.getFunctionName(), params).toString();
                    System.out.println(test.getTestName() + " завершен!");

                    getContext().actorSelection(PATH_TO_STORE_ACTOR)
                            .tell(new TestResult(
                                            msg.getPackageId(),
                                            test.getTestName(),
                                            result.equals(test.getExpectedResult()),
                                            result,
                                            test.getExpectedResult(),
                                            test.getParams()
                                    ),
                                    self()
                            );
                })
                .build();
    }
}
