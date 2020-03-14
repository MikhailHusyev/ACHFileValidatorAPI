package com.groupg.achfilevalidator.services;

import org.springframework.core.io.InputStreamSource;

import com.groupg.achfilevalidator.models.ErrorResponse;

public interface ValidationService {

    // Validate Service
    public ErrorResponse validate(InputStreamSource file);

}