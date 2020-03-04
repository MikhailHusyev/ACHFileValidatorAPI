package com.groupg.achfilevalidator.controllers;

import com.groupg.achfilevalidator.services.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/file/validate")
@RestController
public class ValidationController{

    @Autowired
    private ValidationService validationService;

    @PostMapping
    public String validateFile(@RequestParam("file") InputStreamSource file){
        return String.valueOf(validationService.validate(file));
    }
    
    @GetMapping
    public String test() {
    	return "Can reach api";
    }

}