package lab4;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Test {

    private String testName;
    private String expectedResult;
    private int[] params;

    public Test() {
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public int[] getParams() {
        return params;
    }

    public void setParams(int[] params) {
        this.params = params;
    }
}