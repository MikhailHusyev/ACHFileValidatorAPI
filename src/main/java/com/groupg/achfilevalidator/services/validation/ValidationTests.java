package com.groupg.achfilevalidator.services.validation;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ErrorResponse;

public class ValidationTests {
	public ErrorResponse validFileHash(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int calcHash = 0;
		String realHash = "";
		String fileHash = file.getFileControl().getEntryHash();
		
		for(int i = 0; i < numBatches; i++) {
			int numEntries = file.getBatchDetail().get(i).getEntryDetailList().size();
			
			for(int j = 0; j < numEntries; j++) {
				calcHash += Integer.valueOf(file.getBatchDetail().get(i).getEntryDetailList().get(j).getEntryDetail().getReceivingRoutingNum());
			}
		}
		
		if(String.valueOf(calcHash).length() < 10) {
			int zeros = 10 - String.valueOf(calcHash).length();
			
			while (zeros > 0) {
				realHash += "0";
				zeros--;
			}
			realHash += "" + calcHash;
			
		} else if (String.valueOf(calcHash).length() > 10) {
			int start = String.valueOf(calcHash).length() - 10;
			
			for (int i = start; i < 11; i++)
				realHash += String.valueOf(calcHash).charAt(i);
		}
		
		if(fileHash.compareTo(realHash) != 0)
			return ErrorResponse.HASH_CODE_ERROR;
		
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
	
	public ErrorResponse validBatchHash(ACHFile file, int batchNum) {
		int numEntries = file.getBatchDetail().get(batchNum).getEntryDetailList().size();
		int calcHash = 0;
		String realHash = "";
		String batchHash = file.getBatchDetail().get(batchNum).getCompanyBatchControl().getEntryHash();
		
		for(int i = 0; i < numEntries; i++) {
			calcHash += Integer.valueOf(file.getBatchDetail().get(batchNum).getEntryDetailList().get(i).getEntryDetail().getReceivingRoutingNum());
		}
		
		if(String.valueOf(calcHash).length() < 10) {
			int zeros = 10 - String.valueOf(calcHash).length();
			
			while (zeros > 0) {
				realHash += "0";
				zeros--;
			}
			
			realHash += "" + calcHash;
			
		} else if (String.valueOf(calcHash).length() > 10) {
			int start = String.valueOf(calcHash).length() - 10;
			
			for (int i = start; i < 11; i++)
				realHash += String.valueOf(calcHash).charAt(i);
		}
		
		if(batchHash.compareTo(realHash) != 0)
			return ErrorResponse.HASH_CODE_ERROR;
		
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
}
