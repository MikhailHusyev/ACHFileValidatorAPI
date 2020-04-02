package com.groupg.achfilevalidator.services.validation;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ErrorResponse;

public class ValidationTests {
	public ErrorResponse validHash(ACHFile file) {
		
		int entryNum = file.getBatchDetail().get(0).getEntryDetailList().size();
		String fileHash = file.getFileControl().getEntryHash();
		String batchHash = file.getBatchDetail().get(0).getCompanyBatchControl().getEntryHash();
		String realHash = "";
		int hashCalc = 0;
		
		for(int i = 0; i < entryNum; i++) {
			hashCalc += Integer.valueOf(file.getBatchDetail().get(0).getEntryDetailList().get(i).getEntryDetail().getReceivingRoutingNum());
		}
		
		if(String.valueOf(hashCalc).length() < 10) {
			int zeros = 10 - String.valueOf(hashCalc).length();
			while (zeros > 0) {
				realHash += "0";
				zeros--;
			}
			realHash += "" + hashCalc;
		} else if(String.valueOf(hashCalc).length() > 10) {
			int start = String.valueOf(hashCalc).length() - 10;
			
			for(int i = start; i < 11; i++) {
				realHash += String.valueOf(hashCalc).charAt(i);
			}
		}
		
		if(fileHash.compareTo(realHash) != 0)
			return ErrorResponse.HASH_CODE_ERROR;
		if(batchHash.compareTo(realHash) != 0)
			return ErrorResponse.HASH_CODE_ERROR;
		
		
		
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
}
