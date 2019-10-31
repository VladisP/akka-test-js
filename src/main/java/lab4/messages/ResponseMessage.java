package lab4.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ResponseMessage {

    private String packageId;
    private String status;
    private TestResult[] testResults;

    public ResponseMessage() {
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TestResult[] getTestResults() {
        return testResults;
    }

    public void setTestResults(TestResult[] testResults) {
        this.testResults = testResults;
    }
}
