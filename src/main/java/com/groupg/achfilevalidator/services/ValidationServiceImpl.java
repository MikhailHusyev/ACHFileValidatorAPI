package com.groupg.achfilevalidator.services;

import com.groupg.achfilevalidator.models.ErrorResponse;
import com.groupg.achfilevalidator.services.validation.Validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

/**
 * CURRENT DEPENDENCY INJECTION NAME IS DEFAULT
 */
@Service("test")
public class ValidationServiceImpl implements ValidationService{

    @Autowired
    @Qualifier("testValidate")
    Validate validationService;

    @Override
    public ErrorResponse validate(InputStreamSource file) {
        return validationService.convertFile(file);
    }

}