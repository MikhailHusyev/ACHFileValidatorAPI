package com.groupg.achfilevalidator.services;

import org.springframework.core.io.InputStreamSource;

public interface ValidationService {

    public String validate(InputStreamSource file);

}