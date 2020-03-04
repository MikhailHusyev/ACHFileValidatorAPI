package com.groupg.achfilevalidator.services.validation;

import java.io.Reader;

import com.groupg.achfilevalidator.models.FileHeader;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service("testValidate")
public class ValidateTest implements Validate{
    
    @Override
    public String validate(InputStreamSource file) {
        try {
            
            StreamFactory factory = StreamFactory.newInstance();
            Reader reader;

            reader = new InputStreamReader(file.getInputStream());
            factory.load("src/main/resources/ach.xml");
            BeanReader in = factory.createReader("ach", reader);            
            
            FileHeader header = null;
            while((header = (FileHeader) in.read()) != null){
                String referenceCode = header.getPriorityCode();
                System.out.println(referenceCode);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return "TEST";
    }
}