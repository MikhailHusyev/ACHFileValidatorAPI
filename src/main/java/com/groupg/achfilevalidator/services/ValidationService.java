package com.groupg.achfilevalidator.services;

import java.util.ArrayList;

import org.springframework.core.io.InputStreamSource;

import com.groupg.achfilevalidator.models.ValidationResponse;

public interface ValidationService {

    // Validate Service
    public ArrayList<ValidationResponse> validate(InputStreamSource file);

}