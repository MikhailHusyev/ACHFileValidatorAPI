package com.groupg.achfilevalidator.controllers;

import com.groupg.achfilevalidator.models.ValidationResponse;
import com.groupg.achfilevalidator.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/file/validate")
@RestController
public class ValidationController{

    @Autowired
    @Qualifier("validationService")
    private ValidationService validationService;

    @PostMapping
    public ValidationResponse validateFile(@RequestParam("file") InputStreamSource file){
        return validationService.validate(file);
        
        //TODO API RESPONSE
    }
}