package lab4.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ResponseMessage {

    private String packageId;
    private boolean isSuccessful;
    private TestResult[] testResults;

    public ResponseMessage() {
    }

    public ResponseMessage(String packageId, boolean isSuccessful, int testResultsCount) {
        this.packageId = packageId;
        this.isSuccessful = isSuccessful;
        this.testResults = new TestResult[testResultsCount];
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }


    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public TestResult[] getTestResults() {
        return testResults;
    }

    public void setTestResults(TestResult[] testResults) {
        this.testResults = testResults;
    }
}
