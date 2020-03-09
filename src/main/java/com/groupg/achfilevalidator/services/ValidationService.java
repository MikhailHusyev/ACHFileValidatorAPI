package com.groupg.achfilevalidator.services;

import org.springframework.core.io.InputStreamSource;

public interface ValidationService {

    // Validate Service
    public String validate(InputStreamSource file);

}