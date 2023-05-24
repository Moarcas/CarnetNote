package common;

public class ErrorMessage {
    private int errorCode;
    private String errorMessage;
    
    public ErrorMessage(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
