package com.groupg.achfilevalidator.services;

import org.springframework.core.io.InputStreamSource;
import com.groupg.achfilevalidator.models.APIResponse;

public interface ValidationService {

    // Validate Service
    public APIResponse validate(InputStreamSource file);

}