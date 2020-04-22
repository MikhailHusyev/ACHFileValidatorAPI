package com.groupg.achfilevalidator.services.validation;

import java.io.Reader;
import java.util.ArrayList;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.BatchControlInfo;
import com.groupg.achfilevalidator.models.BatchHeaderInfo;
import com.groupg.achfilevalidator.models.FileControlInfo;
import com.groupg.achfilevalidator.models.FileHeaderInfo;
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
        error.addAll(validator.validBatchTotals(achFile));
        error.add(validator.validBatchCount(achFile));
        error.addAll(validator.validBatchNum(achFile));
        error.add(validator.validBlockingCount(achFile));
        error.add(validator.validEntryCount(achFile));
        error.add(validator.validFileHash(achFile));
        error.add(validator.validFileTotals(achFile));
        error.addAll(validator.validAddenda(achFile));
        error.addAll(validator.validBatchEntryCount(achFile));
        error.addAll(validator.validBatchHash(achFile));
        error.addAll(validator.validCompanyID(achFile));
        error.addAll(validator.validServiceClass(achFile));
        error.addAll(validator.validEffectiveDate(achFile));
        error.removeIf(n -> (n == null));
        return error;
    }

    @Override
	public FileHeaderInfo extractFileHeader(InputStreamSource file) {
		ACHFile achFile = this.convertFile(file);
    	FileHeaderInfo info = new FileHeaderInfo();
		
		info.setImmediateDestination(achFile.getFileHeader().getImmediateDestination());
		info.setImmediateOrigin(achFile.getFileHeader().getImmediateOrigin());
		info.setImmediateOriginName(achFile.getFileHeader().getImmediateOriginName());
		
		return info;
	}

	@Override
	public ArrayList<BatchHeaderInfo> extractBatchHeader(InputStreamSource file) {
		ACHFile achFile = this.convertFile(file);
		ArrayList<BatchHeaderInfo> info = new ArrayList<BatchHeaderInfo>();
		
		for(int i = 0; i < achFile.getBatchDetail().size(); i++) {
			BatchHeaderInfo batch = new BatchHeaderInfo();
			batch.setCompanyID(achFile.getBatchDetail().get(i).getCompanyBatchHeader().getCompanyID());
			batch.setCompanyName(achFile.getBatchDetail().get(i).getCompanyBatchHeader().getCompanyName());
			batch.setEffectiveDate(achFile.getBatchDetail().get(i).getCompanyBatchHeader().getEntryDate());
			info.add(batch);
		}
		return info;
	}

	@Override
	public ArrayList<BatchControlInfo> extractBatchControl(InputStreamSource file) {
		ACHFile achFile = this.convertFile(file);
		ArrayList<BatchControlInfo> info = new ArrayList<BatchControlInfo>();
		
		for(int i = 0; i < achFile.getBatchDetail().size(); i++) {
			BatchControlInfo batch = new BatchControlInfo();
			batch.setCompanyID(achFile.getBatchDetail().get(i).getCompanyBatchControl().getCompanyID());
			batch.setEntryAndAddendaCount(achFile.getBatchDetail().get(i).getCompanyBatchControl().getEntryCount());
			batch.setEntryHash(achFile.getBatchDetail().get(i).getCompanyBatchControl().getEntryHash());
			batch.setTTLCreditEntryAmount(achFile.getBatchDetail().get(i).getCompanyBatchControl().getTotalCreditAmount());
			batch.setTTLDebitEntryAmount(achFile.getBatchDetail().get(i).getCompanyBatchControl().getTotalDebitAmount());
			info.add(batch);
		}
		return info;
	}

	@Override
	public FileControlInfo extractFileControl(InputStreamSource file) {
		ACHFile achFile = this.convertFile(file);
		FileControlInfo info = new FileControlInfo();
		
		info.setEntryAndAddendaCount(achFile.getFileControl().getEntryCount());
		info.setEntryHash(achFile.getFileControl().getEntryHash());
		info.setTTLCreditEntryAmount(achFile.getFileControl().getTotalCreditAmount());
		info.setTTLDebitEntryAmount(achFile.getFileControl().getTotalDebitAmount());
		
		return info;
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