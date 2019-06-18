package io.code.lms.Dtos;

import com.google.gson.Gson;

import java.io.Serializable;

public class BookDto implements Serializable {
    private String title;
    private Integer availabilityCount;
    private String publication;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAvailabilityCount() {
        return availabilityCount;
    }

    public void setAvailabilityCount(Integer availabilityCount) {
        this.availabilityCount = availabilityCount;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
