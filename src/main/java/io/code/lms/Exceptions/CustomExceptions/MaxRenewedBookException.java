package io.code.lms.Exceptions.CustomExceptions;

public class MaxRenewedBookException extends RuntimeException {

    public MaxRenewedBookException(String message) {
        super(message);
    }

    public MaxRenewedBookException(String message, Throwable cause) {
        super(message, cause);
    }

}
