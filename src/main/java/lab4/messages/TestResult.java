package lab4.messages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TestResult {

    private String testName;
    private boolean isSuccessful;
    private String result;
    private String expectedResult;
    private Object[] params;
}
