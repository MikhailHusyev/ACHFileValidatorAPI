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

	public ErrorResponse batchHashHelper(ACHFile file) {
		int batches = file.getBatchDetail().size();
		ErrorResponse error = new ErrorResponse();
		
		for(int i = 0; i < batches; i++) {
			error = this.validBatchHash(file, i);
			if(error.equals(ErrorResponse.HASH_CODE_ERROR))
				break;	
		}
		return error;
	}

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
	
	public ErrorResponse validBatchTotals(ACHFile file, int batchNum) {
		int entryNum = file.getBatchDetail().get(batchNum).getEntryDetailList().size();
		int debit = 0;
		int credit = 0;
		String batchDebit = file.getBatchDetail().get(batchNum).getCompanyBatchControl().getTotalDebitAmount();
		String batchCredit = file.getBatchDetail().get(batchNum).getCompanyBatchControl().getTotalCreditAmount();
		
		for(int i = 0; i < entryNum; i++) {
			String code = file.getBatchDetail().get(batchNum).getEntryDetailList().get(i).getEntryDetail().getTransactionCode();
			int amount = Integer.valueOf(file.getBatchDetail().get(batchNum).getEntryDetailList().get(i).getEntryDetail().getTransactionAmount());
			
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
		
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
	
	public ErrorResponse dollarTotalHelper(ACHFile file) {
		int batches = file.getBatchDetail().size();
		ErrorResponse error = new ErrorResponse();
		
		for(int i = 0; i < batches; i++) {
			error = validBatchTotals(file, i);
			if(error.equals(ErrorResponse.DOLLAR_AMOUNT_ERROR))
				break;
		}
		return error;
	}
	
	public ErrorResponse validFileTotals(ACHFile file) {
		int batches = file.getBatchDetail().size();
		String totalDebit = file.getFileControl().getTotalDebitAmount();
		String totalCredit = file.getFileControl().getTotalCreditAmount();
		int debit = 0;
		int credit = 0;

		if(dollarTotalHelper(file).equals(ErrorResponse.CLEAN_FILE_NO_ERROR)) {
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
}//class
