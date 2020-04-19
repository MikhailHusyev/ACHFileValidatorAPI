package com.groupg.achfilevalidator.services.validation;

import java.io.Reader;
import java.util.ArrayList;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ValidationResponse;
import com.groupg.achfilevalidator.services.validation.standardverification.VerificationService;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;


@Service("standardValidation")
public class StandardACHValidator implements ACHValidator {
    
    @Autowired
    @Qualifier("standardVerification")
    VerificationService validator;

    @Override
    public ArrayList<ValidationResponse> validateFile(InputStreamSource file) {
        ArrayList<ValidationResponse> error = new ArrayList<ValidationResponse>();

        ACHFile achFile = this.convertFile(file);
        System.out.println(achFile.getBatchDetail().get(0).getCompanyBatchHeader().getCompanyName());
        System.out.println("Here");
        error.addAll(validator.validBatchTotals(achFile));
        System.out.println("Here");
        error.add(validator.validFileTotals(achFile));
        for(int i = 0; i < error.size(); i++)
        	System.out.println(error.get(i).toString());
        return error;
    }

    @Override
    public ACHFile convertFile(InputStreamSource file){
        
        try{
            ACHFile convertedFile;
            StreamFactory factory = StreamFactory.newInstance();
            Reader reader;
    
            reader = new InputStreamReader(file.getInputStream());
            factory.load("src/main/resources/ach.xml");
            BeanReader in = factory.createReader("ach", reader);
            
            while((convertedFile = (ACHFile) in.read()) != null){
                return convertedFile;
            }
            in.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }


        return null;

    }
}