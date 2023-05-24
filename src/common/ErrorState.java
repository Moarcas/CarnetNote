package common;

public class ErrorState {
    private static ErrorState instance;
    private ErrorMessage errorMessage;
    
    private ErrorState() {
    }
    
    public static synchronized ErrorState getInstance() {
        if (instance == null) {
            instance = new ErrorState();
        }
        return instance;
    }
    
    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
