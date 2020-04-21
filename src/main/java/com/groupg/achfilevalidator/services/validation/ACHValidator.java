package com.groupg.achfilevalidator.services.validation;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.BatchControlInfo;
import com.groupg.achfilevalidator.models.BatchHeaderInfo;
import com.groupg.achfilevalidator.models.FileControlInfo;
import com.groupg.achfilevalidator.models.FileHeaderInfo;
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
    // Adds FileHeaderInfo to object
    FileHeaderInfo extractFileHeader(InputStreamSource file);
    // Adds BatchHeaderInfo to object
    ArrayList<BatchHeaderInfo> extractBatchHeader(InputStreamSource file);
    // Adds BatchControlInfo to object
    ArrayList<BatchControlInfo> extractBatchControl(InputStreamSource file);
    // Adds FileControlInfo to object
    FileControlInfo extractFileControl(InputStreamSource file);
    
}
