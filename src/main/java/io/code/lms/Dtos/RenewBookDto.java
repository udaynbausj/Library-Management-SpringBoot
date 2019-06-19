package io.code.lms.Dtos;

import com.google.gson.Gson;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class RenewBookDto implements Serializable {

    @NotEmpty(message = "ScholarId should not be empty")
    private Integer scholarId;

    @NotEmpty(message = "BookId should not be empty")
    private Integer bookId;

    public Integer getScholarId() {
        return scholarId;
    }

    public void setScholarId(Integer scholarId) {
        this.scholarId = scholarId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
