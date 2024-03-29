package lab4.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonAutoDetect
@JsonPropertyOrder({"testName", "successful", "result", "expectedResult", "params"})
public class TestResult {

    @JsonIgnore
    private String packageId;
    @JsonProperty("successful")
    private boolean isSuccessful;
    private String result;
    private String testName;
    private String expectedResult;
    private Object[] params;

    public TestResult() {
    }

    public TestResult(
            String packageId,
            String testName,
            boolean isSuccessful,
            String result,
            String expectedResult,
            Object[] params
    ) {
        this.packageId = packageId;
        this.testName = testName;
        this.isSuccessful = isSuccessful;
        this.result = result;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
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
