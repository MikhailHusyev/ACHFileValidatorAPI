package com.groupg.achfilevalidator.services.validation.standardverification;

import java.util.ArrayList;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ValidationResponse;
import org.springframework.stereotype.Component;

@Component("standardVerification")
public class StandardVerification implements VerificationService{
//Checks that file has correct hash based on batch hashes
public ValidationResponse validFileHash(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int calcHash = 0;
		String realHash = "";
		String fileHash = file.getFileControl().getEntryHash();
		
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
			return ValidationResponse.HASH_CODE_ERROR;
		return null;
	}

	//Checks that the specified batch has the correct hash
	public ArrayList<ValidationResponse> validBatchHash(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>();
		
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
				errors.add(ValidationResponse.HASH_CODE_ERROR);
		}
		return errors;
	}

	//Checks the Service Class Code against Entry Transaction codes per batch
	public ArrayList<ValidationResponse> validServiceClass(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>();
		for(int j = 0; j < numBatches; j++) {
			String serviceCode = file.getBatchDetail().get(j).getCompanyBatchHeader().getServiceClassCode();
			int entryCount = Integer.valueOf(file.getBatchDetail().get(j).getEntryDetailList().size());
			String controlServiceCode = file.getBatchDetail().get(j).getCompanyBatchControl().getServiceClassCode();
			
			if(serviceCode.compareTo(controlServiceCode) != 0)
				errors.add(ValidationResponse.SERVICE_TRANSACTION_ERROR);
			
			switch (serviceCode) {
				case ("200"): {
					for(int i = 0; i < entryCount; i++) {
						String transactionCode = file.getBatchDetail().get(j).getEntryDetailList().get(i).getEntryDetail().getTransactionCode();

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
								errors.add(ValidationResponse.SERVICE_TRANSACTION_ERROR);
						}
					}
					break;
				}
				case ("220"): {
					for(int i = 0; i < entryCount; i++) {
						String transactionCode = file.getBatchDetail().get(j).getEntryDetailList().get(i).getEntryDetail().getTransactionCode();

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
								errors.add(ValidationResponse.SERVICE_TRANSACTION_ERROR);
						}
					}
					break;
				}
				case ("225"): {
					for(int i = 0; i < entryCount; i++) {
						String transactionCode = file.getBatchDetail().get(j).getEntryDetailList().get(i).getEntryDetail().getTransactionCode();

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
								errors.add(ValidationResponse.SERVICE_TRANSACTION_ERROR);
						}
					}
					break;
				}
				default: {
					errors.add(ValidationResponse.SERVICE_TRANSACTION_ERROR);
				}
			}
		}
		return errors;
	}

	//Checks that debit and credit totals are correct per batch
	public ArrayList<ValidationResponse> validBatchTotals(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>();
		
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
				errors.add(ValidationResponse.DOLLAR_AMOUNT_ERROR);
			if (debit != Integer.valueOf(batchDebit))
				errors.add(ValidationResponse.DOLLAR_AMOUNT_ERROR);
		}
		return errors;
	}

	//Checks that debit and credit totals for the file are correct
	public ValidationResponse validFileTotals(ACHFile file) {
		int batches = file.getBatchDetail().size();
		String totalDebit = file.getFileControl().getTotalDebitAmount();
		String totalCredit = file.getFileControl().getTotalCreditAmount();
		int debit = 0;
		int credit = 0;

		for(int i = 0; i < batches; i++) {
			debit += Integer.valueOf(file.getBatchDetail().get(i).getCompanyBatchControl().getTotalDebitAmount());
			credit += Integer.valueOf(file.getBatchDetail().get(i).getCompanyBatchControl().getTotalCreditAmount());
		}
			
			if(credit != Integer.valueOf(totalCredit))
				return ValidationResponse.DOLLAR_AMOUNT_ERROR;
			if(debit != Integer.valueOf(totalDebit))
				return ValidationResponse.DOLLAR_AMOUNT_ERROR;
		return null;
	}

	//Checks that the FileControl has the correct number of batches
	public ValidationResponse validBatchCount(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int fileNumBatches = Integer.valueOf(file.getFileControl().batchCount);
		
		if(numBatches != fileNumBatches)
			return ValidationResponse.BATCH_COUNT_ERROR;
		return null;
	}

	//Checks that each batch header matches the control
	public ValidationResponse validBatchNum(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		
		for(int i = 0; i < numBatches; i++) {
			String headerNum = file.getBatchDetail().get(i).getCompanyBatchHeader().getBatchNum();
			String controlNum = file.getBatchDetail().get(i).getCompanyBatchControl().getBatchNum();
			
			if(headerNum.compareTo(controlNum) != 0)
				return ValidationResponse.BATCH_NUMBER_ERROR;
		}
		return null;
	}

	//Checks the total entry count
	public ValidationResponse validEntryCount(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int totalEntries = 0;
		String numEntries = file.getFileControl().getEntryCount();
		
		for(int i = 0; i < numBatches; i++)
			totalEntries += file.getBatchDetail().get(i).getEntryDetailList().size();
		
		if(totalEntries != Integer.valueOf(numEntries))
			return ValidationResponse.ENTRY_COUNT_ERROR;
		return null;
	}

	public ArrayList<ValidationResponse> validBatchEntryCount(ACHFile file){
		int numBatches = file.getBatchDetail().size();
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>();
		for(int i = 0; i < numBatches; i++) {
			int totalEntries = file.getBatchDetail().get(i).getEntryDetailList().size();
			String numEntries = file.getBatchDetail().get(i).getCompanyBatchControl().getEntryCount();
			
			if(totalEntries != Integer.valueOf(numEntries))
				errors.add(ValidationResponse.ENTRY_COUNT_ERROR);
		}
		return errors;
	}

	//Checks that the CompanyID is the same in the header and control per batch
	public ArrayList<ValidationResponse> validCompanyID(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>();
		for(int i = 0; i < numBatches; i++) {
			String headerID = file.getBatchDetail().get(i).getCompanyBatchHeader().getCompanyID();
			String controlID = file.getBatchDetail().get(i).getCompanyBatchControl().getCompanyID();
			
			if(headerID.compareTo(controlID) != 0)
				errors.add(ValidationResponse.COMPANY_ID_ERROR);
		}
		return errors;
	}

	//Checks that the blockCount is correct and that the blocking lines are less than 10
	public ValidationResponse validBlockingCount(ACHFile file) {
		int blockingLines = file.getBlocking().size();
		String blockCount = file.getFileControl().getBlockCount();
		int numOfLines = file.getNumOfLines();
		if((numOfLines / 10) != Integer.valueOf(blockCount))
			return ValidationResponse.BLOCK_NUMBER_ERROR;
		if(numOfLines % 10 != 0)
			return ValidationResponse.BLOCK_NUMBER_ERROR;
		if(blockingLines > 9)
			return ValidationResponse.BLOCK_NUMBER_ERROR;
		return null;
	}

	//Checks that the addendaBool and Addenda existance are in agreement
	public ArrayList<ValidationResponse> validAddenda(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>(); 
		for(int i = 0; i < numBatches; i++) {
			int numEntries = file.getBatchDetail().get(i).getEntryDetailList().size();
			for(int j = 0; j < numEntries; j++) {
				boolean addendaExists = file.getBatchDetail().get(i).getEntryDetailList().get(j).getEntryDetailAddenda() != null;
				boolean entryHasAddenda = Boolean.valueOf(file.getBatchDetail().get(i).getEntryDetailList().get(j).getEntryDetail().getAddendaBool());
				if (addendaExists && entryHasAddenda)
					break;
				if(!addendaExists && !entryHasAddenda)
					break;
				errors.add(ValidationResponse.ADDENDA_ERROR);
			}
		}
		return errors;
	}
		
}//class
