package lab4;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TestMessage {

    private String packageId;
    private String jsScript;
    private String functionName;
    private Test[] tests;

    public TestMessage() {
    }

    public TestMessage(String packageId, String jsScript, String functionName, Test[] tests) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public void setJsScript(String jsScript) {
        this.jsScript = jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Test[] getTests() {
        return tests;
    }

    public void setTests(Test[] tests) {
        this.tests = tests;
    }
}
