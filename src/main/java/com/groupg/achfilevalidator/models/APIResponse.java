package com.groupg.achfilevalidator.models;

import java.util.List;

public class APIResponse {

    private List<ValidationResponse> errors;

    public List<ValidationResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationResponse> errors) {
        this.errors = errors;
    }
}