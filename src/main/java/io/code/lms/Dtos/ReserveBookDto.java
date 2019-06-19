package io.code.lms.Dtos;

import com.google.gson.Gson;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ReserveBookDto implements Serializable {

    @NotEmpty(message = "BookId should not be empty")
    private Integer bookId;

    @NotEmpty(message = "ScholarId should not be empty")
    private Integer scholarId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getScholarId() {
        return scholarId;
    }

    public void setScholarId(Integer scholarId) {
        this.scholarId = scholarId;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
