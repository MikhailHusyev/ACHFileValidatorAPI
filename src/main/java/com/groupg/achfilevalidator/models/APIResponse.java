package com.groupg.achfilevalidator.models;

import java.util.List;

public class APIResponse {

    private String value;
    private String location;
    private List<String> errors;

    public String getValue() {
        return value;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setValue(String value) {
        this.value = value;
    }

}