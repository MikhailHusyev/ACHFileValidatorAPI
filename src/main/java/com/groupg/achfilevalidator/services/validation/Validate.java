package com.groupg.achfilevalidator.services.validation;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import com.afrunt.jach.ACH;
import com.afrunt.jach.metadata.ACHBeanMetadata;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;

import org.springframework.core.io.InputStreamSource;
import org.springframework.web.multipart.MultipartFile;

public class Validate{

    public String testValidation(InputStreamSource file){
        
        return "TEST";
    }
}