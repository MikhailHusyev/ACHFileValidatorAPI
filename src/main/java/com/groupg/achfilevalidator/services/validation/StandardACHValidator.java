package com.groupg.achfilevalidator.services.validation;

import java.io.Reader;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ErrorResponse;
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
        ErrorResponse error = new ErrorResponse();

        ACHFile achFile = this.convertFile(file);
        error = validator.validBlockingCount(achFile);

        return null;
    }

    @Override
    public ACHFile convertFile(InputStreamSource file){
        ACHFile convertedFile = null;
        try{
            StreamFactory factory = StreamFactory.newInstance();
            Reader reader;
    
            reader = new InputStreamReader(file.getInputStream());
            factory.load("src/main/resources/testACH.xml");
            BeanReader in = factory.createReader("ach", reader);
            while((convertedFile = (ACHFile) in.read()) != null){
                
            }
            in.close();
        }catch(Exception ex){

        }


        return convertedFile;

    }
}