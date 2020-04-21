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

    //TODO: extractInfo method to pull info from file into info Objects
    //TODO: make info objects for APIResponse
    
    @Override
    public ArrayList<ValidationResponse> validateFile(InputStreamSource file) {
        ArrayList<ValidationResponse> error = new ArrayList<ValidationResponse>();

        ACHFile achFile = this.convertFile(file);
        error.addAll(validator.validBatchTotals(achFile));
        error.add(validator.validBatchCount(achFile));
        error.add(validator.validBatchNum(achFile));
        error.add(validator.validBlockingCount(achFile));
        error.add(validator.validEntryCount(achFile));
        error.add(validator.validFileHash(achFile));
        error.add(validator.validFileTotals(achFile));
        error.addAll(validator.validAddenda(achFile));
        error.addAll(validator.validBatchEntryCount(achFile));
        error.addAll(validator.validBatchHash(achFile));
        error.addAll(validator.validCompanyID(achFile));
        error.addAll(validator.validServiceClass(achFile));
        error.removeIf(n -> (n == null));
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