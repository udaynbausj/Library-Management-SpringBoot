package io.code.lms.Exceptions.SQLExceptions;

public class DBExceptionBase extends Exception {
    public DBExceptionBase(String reason) {
        super(reason);
    }
}
