package lab4.messages;

public class GetResultMessage {

    private String packageId;

    public GetResultMessage(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }
}
