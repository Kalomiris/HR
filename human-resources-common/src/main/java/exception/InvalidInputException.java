package exception;

public class InvalidInputException extends Exception {

    private static final long serialVersionUID = -2212077676503980584L;

    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(Exception e) {
        super(e.getMessage());
    }
}
