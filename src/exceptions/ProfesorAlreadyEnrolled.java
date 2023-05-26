package exceptions;

public class ProfesorAlreadyEnrolled extends RuntimeException {
    public ProfesorAlreadyEnrolled(String message) {
        super(message);
    }
}
