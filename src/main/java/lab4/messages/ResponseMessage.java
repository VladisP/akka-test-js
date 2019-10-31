package lab4.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ResponseMessage {

    private String status;
    private TestResult[] testResults;
}
