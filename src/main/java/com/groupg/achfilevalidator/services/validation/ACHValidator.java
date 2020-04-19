package com.groupg.achfilevalidator.services.validation;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ValidationResponse;

import java.util.ArrayList;

import org.springframework.core.io.InputStreamSource;

/**
 * Validation Service
 */
public interface ACHValidator {

    // Convert File To ACH Object
    ACHFile convertFile(InputStreamSource file) throws Exception;
    // Validate the file according to specifications
    ArrayList<ValidationResponse> validateFile(InputStreamSource file);
}
