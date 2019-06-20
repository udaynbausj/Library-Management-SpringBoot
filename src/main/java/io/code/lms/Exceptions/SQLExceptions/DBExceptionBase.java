package io.code.lms.Exceptions.SQLExceptions;

import java.sql.SQLException;

public class DBExceptionBase extends SQLException {
    public DBExceptionBase(String reason) {
        super(reason);
    }
}
