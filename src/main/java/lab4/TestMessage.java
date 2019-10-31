package lab4;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TestPackageMessage {

    private int packageId;
    private String jsScript;
    private String functionName;
    private Test[] tests;

    public TestPackageMessage() {
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
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
