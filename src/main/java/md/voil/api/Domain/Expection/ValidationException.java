package md.voil.api.Domain.Expection;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
