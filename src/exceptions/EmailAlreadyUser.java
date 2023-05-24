package exceptions;

public class EmailAlreadyUser extends Exception {
    public EmailAlreadyUser(String message) {
        super(message);
    }
}
