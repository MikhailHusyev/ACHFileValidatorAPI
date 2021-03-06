package com.groupg.achfilevalidator.controllers;

import com.groupg.achfilevalidator.models.APIResponse;
import com.groupg.achfilevalidator.services.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/file/validate")
@RestController
@CrossOrigin(origins="*")
public class ValidationController{

    @Autowired
    @Qualifier("validationService")
    private ValidationServiceImpl validationService;

    @PostMapping
    public APIResponse validateFile(@RequestParam("file") MultipartFile file){
    	return validationService.validate(file);
    }
}