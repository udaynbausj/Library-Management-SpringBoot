package io.code.lms.Exceptions.CustomExceptions;

public class MaxBooksAlreadyIssuedException extends RuntimeException {

    public MaxBooksAlreadyIssuedException(String message) {
        super(message);
    }

    public MaxBooksAlreadyIssuedException(String message, Throwable cause) {
        super(message, cause);
    }
}
