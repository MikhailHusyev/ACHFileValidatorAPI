package com.groupg.achfilevalidator.services.validation;

import java.io.Reader;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.CompanyBatchHeader;
import com.groupg.achfilevalidator.models.EntryDetail;
import com.groupg.achfilevalidator.models.ErrorResponse;
import com.groupg.achfilevalidator.models.FileHeader;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.GroupBuilder;
import org.beanio.builder.StreamBuilder;

import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service("testValidate")
public class ValidateTest implements Validate{
    
    @Override
    public ErrorResponse convertFile(InputStreamSource file) {
        ErrorResponse error = new ErrorResponse("unknown error", 0, 0, 0, "Testing error response");
    	try {
            
            StreamFactory factory = StreamFactory.newInstance();
            Reader reader;

            reader = new InputStreamReader(file.getInputStream());
            factory.load("src/main/resources/ach.xml");
            BeanReader in = factory.createReader("ach", reader);            
            
            ACHFile convertedFile = null;

            while((convertedFile = (ACHFile) in.read()) != null){
                //Check that serviceClassCode is accurate
                switch (convertedFile.getCompanyBatchHeader().getServiceClassCode()) {
                case "200": {//Debit and Credit
                	
                }
                case "220": {//Credit Only
                	
                }
                case "225": {//Debit Only
                	
                }
                default:
                	error = new ErrorResponse("ServiceClassCode Error", 5, 2, 2, "The service code is not valid");
                }
            }

            return error;
        } catch (Exception e) {
            System.out.println(e);
        }
        return error;
    }

    @Override
    public ErrorResponse validateFile(ACHFile fileModel) {
        return null;
    }
}