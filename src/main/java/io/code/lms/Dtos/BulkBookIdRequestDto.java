package io.code.lms.Dtos;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class BulkBookIdRequestDto implements Serializable {

    private List<Integer>bookIds;

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
