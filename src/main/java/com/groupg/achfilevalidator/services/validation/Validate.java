package com.groupg.achfilevalidator.services.validation;

import com.groupg.achfilevalidator.models.ACHFile;

import org.springframework.core.io.InputStreamSource;

/**
 * Validation Service
 */
public interface Validate{

    // Convert File To ACH Object
    Object convertFile(InputStreamSource file);
    // Validate the file according to specifications
    Object validateFile(ACHFile fileModel);
}
