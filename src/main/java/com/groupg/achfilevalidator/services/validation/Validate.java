package com.groupg.achfilevalidator.services.validation;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ErrorResponse;

import org.springframework.core.io.InputStreamSource;

/**
 * Validation Service
 */
public interface Validate{

    // Convert File To ACH Object
    ErrorResponse convertFile(InputStreamSource file);
    // Validate the file according to specifications
    ErrorResponse validateFile(ACHFile fileModel);
}
