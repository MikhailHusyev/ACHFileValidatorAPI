package com.groupg.achfilevalidator.services;

import com.groupg.achfilevalidator.models.APIResponse;
import com.groupg.achfilevalidator.services.validation.StandardACHValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

/**
 * CURRENT DEPENDENCY INJECTION NAME IS DEFAULT
 */
@Service("validationService")
public class ValidationServiceImpl implements ValidationService{

    @Autowired
    @Qualifier("standardValidation")
    StandardACHValidator standardValidator;

    @Override
    public APIResponse validate(InputStreamSource file) {
        APIResponse response = new APIResponse();
    	
        response.setErrors(standardValidator.validateFile(file));
        response.setFileHeader(standardValidator.extractFileHeader(file));
        response.setBatchHeaders(standardValidator.extractBatchHeader(file));
        response.setBatchControls(standardValidator.extractBatchControl(file));
        response.setFileControl(standardValidator.extractFileControl(file));
        
        return response;  
    }

}