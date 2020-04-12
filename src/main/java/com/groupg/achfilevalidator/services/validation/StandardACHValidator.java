package com.groupg.achfilevalidator.services.validation;

import java.io.Reader;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ValidationResponse;
import com.groupg.achfilevalidator.services.validation.standardverification.VerificationService;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service("standardValidation")
public class StandardACHValidator implements ACHValidator {
    
    @Autowired
    @Qualifier("standardVerification")
    VerificationService validator;

    @Override
    public ValidationResponse validateFile(InputStreamSource file) {
        ValidationResponse error = new ValidationResponse();
        try {
            ACHFile achFile = this.convertFile(file);
            error = validator.serviceClassHelper(achFile);

        } catch (Exception e) {
            System.out.println(e);
        }
        return error;
    }

    @Override
    public ACHFile convertFile(InputStreamSource file) throws Exception {

        StreamFactory factory = StreamFactory.newInstance();
        Reader reader;

        reader = new InputStreamReader(file.getInputStream());
        factory.load("src/main/resources/testACH.xml");
        BeanReader in = factory.createReader("ach", reader);
            
        ACHFile convertedFile = null;

        while((convertedFile = (ACHFile) in.read()) != null){
            
        }
        in.close();
        return convertedFile;

    }
}