package lab4.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lab4.entities.TestResult;

@JsonAutoDetect
public class ResponseMessage {

    private String packageId;
    private boolean isSuccessful;
    private TestResult[] testResults;

    public ResponseMessage() {
    }

    public ResponseMessage(String packageId, TestResult[] testResults) {
        this.packageId = packageId;
        this.isSuccessful = true;
        this.testResults = testResults;

        for (TestResult result : this.testResults) {
            if (!result.isSuccessful()) {
                this.isSuccessful = false;
                break;
            }
        }
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
