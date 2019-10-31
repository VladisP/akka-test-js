package lab4.messages;

public class TestResultMessage {

    private String packageId;
    private boolean isSuccessful;
    private String result;
    private Test test;

    public TestResultMessage(String packageId, boolean isSuccessful, String result, Test test) {
        this.packageId = packageId;
        this.isSuccessful = isSuccessful;
        this.result = result;
        this.test = test;
    }

    public String getPackageId() {
        return packageId;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getResult() {
        return result;
    }

    public Test getTest() {
        return test;
    }
}
