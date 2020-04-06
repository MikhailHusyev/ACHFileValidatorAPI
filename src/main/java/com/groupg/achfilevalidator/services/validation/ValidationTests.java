package com.groupg.achfilevalidator.services.validation;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ErrorResponse;

public class ValidationTests {
	//TODO fix to add up batch hashes similar to validFileTotals
	public ErrorResponse validFileHash(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int calcHash = 0;
		String realHash = "";
		String fileHash = file.getFileControl().getEntryHash();
		
		if(validBatchHash(file).equals(ErrorResponse.CLEAN_FILE_NO_ERROR)) {
			for(int i = 0; i < numBatches; i++) {
				calcHash += Integer.valueOf(file.getBatchDetail().get(i).getCompanyBatchControl().getEntryHash());
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
		return ErrorResponse.HASH_CODE_ERROR;
	}
	
	//Checks that the specified batch has the correct hash
	public ErrorResponse validBatchHash(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		
		for(int j = 0; j < numBatches; j++) {
			int numEntries = file.getBatchDetail().get(j).getEntryDetailList().size();
			int calcHash = 0;
			String realHash = "";
			String batchHash = file.getBatchDetail().get(j).getCompanyBatchControl().getEntryHash();
			
			for(int i = 0; i < numEntries; i++) {
				calcHash += Integer.valueOf(file.getBatchDetail().get(j).getEntryDetailList().get(i).getEntryDetail().getReceivingRoutingNum());
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
		}
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
	
	//Checks the Service Class Code against Entry Transaction codes per batch
	public ErrorResponse validServiceClass(ACHFile file, int batchNum) {
		String serviceCode = file.getBatchDetail().get(batchNum).getCompanyBatchHeader().getServiceClassCode();
		int entryCount = Integer.valueOf(file.getBatchDetail().get(batchNum).getEntryDetailList().size());
		ErrorResponse error = ErrorResponse.CLEAN_FILE_NO_ERROR;
		String controlServiceCode = file.getBatchDetail().get(batchNum).getCompanyBatchControl().getServiceClassCode();
		
		if(serviceCode.compareTo(controlServiceCode) != 0)
			return ErrorResponse.SERVICE_TRANSACTION_ERROR;
		
		switch (serviceCode) {
			case ("200"): {
				for(int i = 0; i < entryCount; i++) {
					String transactionCode = file.getBatchDetail().get(batchNum).getEntryDetailList().get(i).getEntryDetail().getTransactionCode();

					switch(transactionCode) {
						case("22"):
							break;
						case("23"):
							break;
						case("27"):
							break;
						case("28"):
							break;
						case("32"):
							break;
						case("33"):
							break;
						case("37"):
							break;
						case("38"):
							break;
						default:
							return ErrorResponse.SERVICE_TRANSACTION_ERROR;
					}
				}
				return error;
			}
			case ("220"): {
				for(int i = 0; i < entryCount; i++) {
					String transactionCode = file.getBatchDetail().get(batchNum).getEntryDetailList().get(i).getEntryDetail().getTransactionCode();

					switch(transactionCode) {
						case("22"):
							break;
						case("23"):
							break;
						case("32"):
							break;
						case("33"):
							break;
						default:
							return ErrorResponse.SERVICE_TRANSACTION_ERROR;
					}
				}
				return error;
			}
			case ("225"): {
				for(int i = 0; i < entryCount; i++) {
					String transactionCode = file.getBatchDetail().get(batchNum).getEntryDetailList().get(i).getEntryDetail().getTransactionCode();

					switch(transactionCode) {
						case("27"):
							break;
						case("28"):
							break;
						case("37"):
							break;
						case("38"):
							break;
						default:
							return ErrorResponse.SERVICE_TRANSACTION_ERROR;
					}
				}
				return error;
			}
			default: {
				return ErrorResponse.SERVICE_TRANSACTION_ERROR;
			}
		}
	}
	
	//Loops through batches
	public ErrorResponse serviceClassHelper(ACHFile file) {
		int batches = file.getBatchDetail().size();
		ErrorResponse error = new ErrorResponse();
		
		for(int i = 0; i < batches; i++) {
			error = this.validServiceClass(file, i);
			if(error.equals(ErrorResponse.SERVICE_TRANSACTION_ERROR))
				break;	
		}
		return error;
	}
	
	//Checks that debit and credit totals are correct per batch
	public ErrorResponse validBatchTotals(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		
		for(int j = 0; j < numBatches; j++) {
			int entryNum = file.getBatchDetail().get(j).getEntryDetailList().size();
			int debit = 0;
			int credit = 0;
			String batchDebit = file.getBatchDetail().get(j).getCompanyBatchControl().getTotalDebitAmount();
			String batchCredit = file.getBatchDetail().get(j).getCompanyBatchControl().getTotalCreditAmount();
			
			for(int i = 0; i < entryNum; i++) {
				String code = file.getBatchDetail().get(j).getEntryDetailList().get(i).getEntryDetail().getTransactionCode();
				int amount = Integer.valueOf(file.getBatchDetail().get(j).getEntryDetailList().get(i).getEntryDetail().getTransactionAmount());
				
				if(code.compareTo("22") == 0)
					credit += amount;
				if(code.compareTo("23") == 0)
					credit += amount;
				if(code.compareTo("27") == 0)
					debit += amount;
				if(code.compareTo("28") == 0)
					debit += amount;
				if(code.compareTo("32") == 0)
					credit += amount;
				if(code.compareTo("33") == 0)
					credit += amount;
				if(code.compareTo("37") == 0)
					debit += amount;
				if(code.compareTo("38") == 0)
					debit += amount;
			}
			
			if (credit != Integer.valueOf(batchCredit))
				return ErrorResponse.DOLLAR_AMOUNT_ERROR;
			if (debit != Integer.valueOf(batchDebit))
				return ErrorResponse.DOLLAR_AMOUNT_ERROR;
		}
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
	
	//Checks that debit and credit totals for the file are correct
	public ErrorResponse validFileTotals(ACHFile file) {
		int batches = file.getBatchDetail().size();
		String totalDebit = file.getFileControl().getTotalDebitAmount();
		String totalCredit = file.getFileControl().getTotalCreditAmount();
		int debit = 0;
		int credit = 0;

		if(validBatchTotals(file).equals(ErrorResponse.CLEAN_FILE_NO_ERROR)) {
			for(int i = 0; i < batches; i++) {
				debit += Integer.valueOf(file.getBatchDetail().get(i).getCompanyBatchControl().getTotalDebitAmount());
				credit += Integer.valueOf(file.getBatchDetail().get(i).getCompanyBatchControl().getTotalCreditAmount());
			}
			
			if(credit != Integer.valueOf(totalCredit))
				return ErrorResponse.DOLLAR_AMOUNT_ERROR;
			if(debit != Integer.valueOf(totalDebit))
				return ErrorResponse.DOLLAR_AMOUNT_ERROR;
		} else {
			return ErrorResponse.DOLLAR_AMOUNT_ERROR;
		}
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
	
	//Checks that the FileControl has the correct number of batches
	public ErrorResponse validBatchCount(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int fileNumBatches = Integer.valueOf(file.getFileControl().batchCount);
		
		if(numBatches != fileNumBatches)
			return ErrorResponse.BATCH_COUNT_ERROR;
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
	
	//Checks that each batch header matches the control
	public ErrorResponse validBatchNum(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		
		for(int i = 0; i < numBatches; i++) {
			String headerNum = file.getBatchDetail().get(i).getCompanyBatchHeader().getBatchNum();
			String controlNum = file.getBatchDetail().get(i).getCompanyBatchControl().getBatchNum();
			
			if(headerNum.compareTo(controlNum) != 0)
				return ErrorResponse.BATCH_NUMBER_ERROR;
		}
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
	
	//Checks the total entry count
	public ErrorResponse validEntryCount(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int totalEntries = 0;
		String numEntries = file.getFileControl().getEntryCount();
		
		for(int i = 0; i < numBatches; i++)
			totalEntries += file.getBatchDetail().get(i).getEntryDetailList().size();
		
		if(totalEntries != Integer.valueOf(numEntries))
			return ErrorResponse.ENTRY_COUNT_ERROR;
		return ErrorResponse.CLEAN_FILE_NO_ERROR;	
	}
	
	//Checks that the CompanyID is the same in the header and control per batch
	public ErrorResponse validCompanyID(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		
		for(int i = 0; i < numBatches; i++) {
			String headerID = file.getBatchDetail().get(i).getCompanyBatchHeader().getCompanyID();
			String controlID = file.getBatchDetail().get(i).getCompanyBatchControl().getCompanyID();
			
			if(headerID.compareTo(controlID) != 0)
				return ErrorResponse.COMPANY_ID_ERROR;
		}
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}

	//TODO fix ach.xml to accept the blocking lines at the bottom
	//TODO then check that the proper amount of blocking is placed
	//if(lines % 10 != 0 then add lines until true;
	//check that FileControl.blockCount % 10 == 0
}//class
