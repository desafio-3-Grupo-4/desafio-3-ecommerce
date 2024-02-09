package application.exceptions;

public class ErrorConstraint extends RuntimeException{
    public ErrorConstraint(String message) {
        super(message);
    }
}
