package com.groupg.achfilevalidator.services;

import com.groupg.achfilevalidator.services.validation.Validate;

import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

/**
 * CURRENT DEPENDENCY INJECTION NAME IS DEFAULT
 */
@Service("default")
public class ValidationServiceImpl implements ValidationService{

    @Override
    public String validate(InputStreamSource file) {
        Validate valid = new Validate();

        return valid.testValidation(file);
    }

}