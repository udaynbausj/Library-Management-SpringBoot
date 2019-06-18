package io.code.lms.Dtos;

import com.google.gson.Gson;

import java.io.Serializable;

public class ReserveBookDto implements Serializable {

    private Integer bookId;
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
