package io.code.lms.Exceptions.CustomExceptions;

public class FineChargeException extends RuntimeException {

    public FineChargeException(String message) {
        super(message);
    }

    public FineChargeException(String message, Throwable cause) {
        super(message, cause);
    }
}
