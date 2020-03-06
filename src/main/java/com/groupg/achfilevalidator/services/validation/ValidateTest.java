package com.groupg.achfilevalidator.services.validation;

import java.io.Reader;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.CompanyBatchHeader;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;
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
            
            ACHFile header = null;
            while((header = (ACHFile) in.read()) != null){
                String referenceCode = header.getCompanyBatchHeader().companyData;
                System.out.println(referenceCode);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        return "TEST";
    }
}