package com.groupg.achfilevalidator.services;

import org.springframework.core.io.InputStreamSource;

import com.groupg.achfilevalidator.models.ValidationResponse;

public interface ValidationService {

    // Validate Service
    public ValidationResponse validate(InputStreamSource file);

}