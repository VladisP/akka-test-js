package lab4.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonAutoDetect
@JsonPropertyOrder({"testName", "isSuccessful", "result", "expectedResult", "params"})
public class TestResult {

    private String testName;
    private boolean isSuccessful;
    private String result;
    private String expectedResult;
    private Object[] params;

    public TestResult() {
    }

    public TestResult(String testName, boolean isSuccessful, String result, String expectedResult, Object[] params) {
        this.testName = testName;
        this.isSuccessful = isSuccessful;
        this.result = result;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
