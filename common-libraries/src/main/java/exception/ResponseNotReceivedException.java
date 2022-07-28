package exception;

public class ResponseNotReceivedException extends RuntimeException{

    public ResponseNotReceivedException(String message) {
        super(message);
    }
}
