package lab4.messages;

public class GetResultsMessage {

    private String packageId;

    public GetResultsMessage(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }
}
