package io.code.lms.Dtos;

import com.google.gson.Gson;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ScholarDto implements Serializable {

    @NotEmpty(message = "Name should not be null ")
    private String name;
    private Integer status;

    public ScholarDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ScholarDto{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
