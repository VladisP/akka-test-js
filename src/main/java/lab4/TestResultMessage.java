package lab4;

public class TestResultMessage {

    private int packageId;
    private boolean isSuccessful;
    private String result;
    private Test test;

    public TestResultMessage(int packageId, boolean isSuccessful, String result, Test test) {
        this.packageId = packageId;
        this.isSuccessful = isSuccessful;
        this.result = result;
        this.test = test;
    }

    public int getPackageId() {
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
