package com.groupg.achfilevalidator.services.validation.standardverification;

import java.util.ArrayList;
import java.util.Calendar;

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
		if(fileHash.compareTo(realHash) != 0) {
			ValidationResponse error = new ValidationResponse("HashCodeError", 9, lineNum(9, 0, 0, file), 22, 10, "The Hash Code is incorrect, double check the Batch Hash(es)");
			return error;
		}
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
			if(batchHash.compareTo(realHash) != 0) {
				ValidationResponse error = new ValidationResponse();
				error.setType("HashCodeError");
				error.setLine(lineNum(8, j+1, 0, file));
				error.setSection(8);
				error.setLocation(11);
				error.setLength(10);
				error.setDescription("The Hash code is incorrect in Batch " + (j+1));
				errors.add(error);
			}
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
			ValidationResponse error = new ValidationResponse();
			if(serviceCode.compareTo(controlServiceCode) != 0) {
				error.setType("ServiceCodeError");
				error.setSection(8);
				error.setLine(lineNum(8, j+1, 0, file));
				error.setLocation(2);
				error.setLength(3);
				error.setDescription("ServiceCodes do not match in Batch " + (j+1) + " Header and Control");
				errors.add(error);
			}
			
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
							default: {
								error.setType("TransactionCodeError");
								error.setSection(6);
								error.setLine(lineNum(6, j+1, i+1, file));
								error.setLocation(2);
								error.setLength(2);
								error.setDescription("Unknown transaction code");
								errors.add(error);
							}
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
							default:{
								error.setType("ServiceCodeError");
								error.setSection(6);
								error.setLine(lineNum(6, j+1, i+1, file));
								error.setLocation(2);
								error.setLength(2);
								error.setDescription(error.getDescription() + " check that the proper ServiceCode is in the Batch " + (j+1) + " Header");
								errors.add(error);
							}
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
							default:{
								error.setType("ServiceCodeError");
								error.setSection(6);
								error.setLine(lineNum(6, j+1, i+1, file));
								error.setLocation(2);
								error.setLength(2);
								error.setDescription(error.getDescription() + " check that the proper ServiceCode is in the Batch " + (j+1) + " Header");
								errors.add(error);
							}
						}
					}
					break;
				}
				default: {
					error.setType("ServiceCodeError");
					error.setSection(5);
					error.setLine(lineNum(5, j+1, 0, file));
					error.setLocation(2);
					error.setLength(3);
					error.setDescription("Batch " + (j+1) + " Header and Control matched, but ServiceCode is unknown");
					errors.add(error);
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
			
			
			if (credit != Integer.valueOf(batchCredit)) {
				ValidationResponse error = new ValidationResponse();
				error.setType("DollarAmountError");
				error.setSection(8);
				error.setLine(lineNum(8, j+1, 0, file));
				error.setLocation(33);
				error.setLength(12);
				error.setDescription("(Credit) The Dollar Amount Error is incorrect in Batch " + (j+1));
				errors.add(error);
			}
			
			if (debit != Integer.valueOf(batchDebit)) {
				ValidationResponse error = new ValidationResponse();
				error.setType("DollarAmountError");
				error.setSection(8);
				error.setLine(lineNum(8, j+1, 0, file));
				error.setLocation(21);
				error.setLength(12);
				error.setDescription("(Debit) The Dollar Amount Error is incorrect in Batch " + (j+1));
				errors.add(error);
			}
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
		
			if(credit != Integer.valueOf(totalCredit)) {
				ValidationResponse error = new ValidationResponse();
				error.setType("DollarAmountError");
				error.setSection(9);
				error.setLine(lineNum(9, 0, 0, file));
				error.setLocation(44);
				error.setLength(12);
				error.setDescription("(Credit) The Dollar Amount in the File Control is incorrect, double check Batches");
				return error;
			}
			if(debit != Integer.valueOf(totalDebit)) {
				ValidationResponse error = new ValidationResponse();
				error.setType("DollarAmountError");
				error.setSection(9);
				error.setLine(lineNum(9, 0, 0, file));
				error.setLocation(32);
				error.setLength(12);
				error.setDescription("(Debit) The Dollar Amount in the File Control is incorrect, double check Batches");
				return error;
			}
		return null;
	}

	//Checks that the FileControl has the correct number of batches
	public ValidationResponse validBatchCount(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int fileNumBatches = Integer.valueOf(file.getFileControl().batchCount);
		
		if(numBatches != fileNumBatches) {
			ValidationResponse error = new ValidationResponse();
			error.setType("BatchCountError");
			error.setSection(9);
			error.setLine(lineNum(9, 0, 0, file));
			error.setLocation(2);
			error.setLength(6);
			error.setDescription("The Batch Count in the File Control is incorrect");
			return error;
		}
		return null;
	}

	//Checks that each batch header matches the control
	public ArrayList<ValidationResponse> validBatchNum(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>();
		for(int i = 0; i < numBatches; i++) {
			String headerNum = file.getBatchDetail().get(i).getCompanyBatchHeader().getBatchNum();
			String controlNum = file.getBatchDetail().get(i).getCompanyBatchControl().getBatchNum();
			
			if(headerNum.compareTo(controlNum) != 0) {
				ValidationResponse error = new ValidationResponse();
				error.setType("BatchNumberError");
				error.setSection(8);
				error.setLine(lineNum(8, i+1, 0, file));
				error.setLocation(88);
				error.setLength(7);
				error.setDescription("The Batch Number does not match in the Header and Control of Batch" + (i+1));
				errors.add(error);
			}
		}
		return errors;
	}

	//Checks the total entry count
	public ValidationResponse validEntryCount(ACHFile file) {
		int numBatches = file.getBatchDetail().size();
		int totalEntries = 0;
		String numEntries = file.getFileControl().getEntryCount();
		
		for(int i = 0; i < numBatches; i++)
			totalEntries += file.getBatchDetail().get(i).getEntryDetailList().size();
		
		if(totalEntries != Integer.valueOf(numEntries)) {
			ValidationResponse error = new ValidationResponse();
			error.setType("EntryCountError");
			error.setSection(9);
			error.setLine(lineNum(9, 0, 0, file));
			error.setLocation(14);
			error.setLength(8);
			error.setDescription("The Entry Count is incorrect in the File Control");
			return error;
		}
		return null;
	}

	//Checks the entry count per batch
	public ArrayList<ValidationResponse> validBatchEntryCount(ACHFile file){
		int numBatches = file.getBatchDetail().size();
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>();
		for(int i = 0; i < numBatches; i++) {
			int totalEntries = file.getBatchDetail().get(i).getEntryDetailList().size();
			String numEntries = file.getBatchDetail().get(i).getCompanyBatchControl().getEntryCount();
			
			if(totalEntries != Integer.valueOf(numEntries)) {
				ValidationResponse error = new ValidationResponse("BatchEntryCountError", 8, lineNum(8, i+1, 0, file), 5, 6, "The Entry count is not correct in Batch" + (i+1));
				errors.add(error);
			}
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
			
			if(headerID.compareTo(controlID) != 0) {
				ValidationResponse error = new ValidationResponse();
				error.setType("CompanyIDError");
				error.setSection(8);
				error.setLine(lineNum(8, i+1, 0, file));
				error.setLocation(45);
				error.setLength(10);
				error.setDescription("The Company ID does not match in the Header and Control of Batch " + (i+1));
				errors.add(error);
			}
		}
		return errors;
	}

	//Checks that the blockCount is correct and that the blocking lines are less than 10
	public ValidationResponse validBlockingCount(ACHFile file) {
		int blockingLines = file.getBlocking().size();
		String blockCount = file.getFileControl().getBlockCount();
		int numOfLines = file.getNumOfLines();
		ValidationResponse error = new ValidationResponse();
		if((numOfLines / 10) != Integer.valueOf(blockCount)) {
			error.setType("BlockingError");
			error.setSection(9);
			error.setLine(lineNum(9, 0, 0, file));
			error.setLocation(8);
			error.setLength(6);
			error.setDescription("The block count is not correct in the File Control");
			return error;
		}
		if(numOfLines % 10 != 0) {
			error.setType("BlockingError");
			error.setSection(9);
			error.setLine(lineNum(9, 0, 0, file));
			error.setLocation(8);
			error.setLength(6);
			error.setDescription("The number of lines is not divisible by 10, add lines of 9's until it is");
			return error;
		}
		if(blockingLines > 9) {
			error.setType("BlockingError");
			error.setSection(9);
			error.setLine(lineNum(9, 0, 0, file));
			error.setLocation(8);
			error.setLength(6);
			error.setDescription("There are too many blocking lines of 9's, reduce the redundant lines");
			return error;
		}
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
				ValidationResponse error = new ValidationResponse();
				error.setType("AddendaError");
				error.setSection(6);
				error.setLine(lineNum(6, i+1, j+1, file));
				error.setLocation(79);
				error.setLength(1);
				error.setDescription("The Entry either indicates an Addenda exists and it does not, or the opposite");
				errors.add(error);
			}
		}
		return errors;
	}
		
	//Checks the effective date is a valid business day
	public ArrayList<ValidationResponse> validEffectiveDate(ACHFile file){
		ArrayList<ValidationResponse> errors = new ArrayList<ValidationResponse>();
		
		for(int i = 0; i < file.getBatchDetail().size(); i++) {
			String effectiveDate = file.getBatchDetail().get(i).getCompanyBatchHeader().getEntryDate();
			int year = Integer.valueOf("20" + effectiveDate.charAt(0) + effectiveDate.charAt(1));
			int month = Integer.valueOf("" + effectiveDate.charAt(2) + effectiveDate.charAt(3)) - 1;
			int day = Integer.valueOf("" + effectiveDate.charAt(4) + effectiveDate.charAt(5));
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day);
			if(!isBusinessDay(cal)) {
				ValidationResponse error = new ValidationResponse();
				error.setType("EffectiveDateError");
				error.setSection(5);
				error.setLine(lineNum(5, i+1, 0, file));
				error.setLocation(70);
				error.setLength(6);
				errors.add(error);
			}
		}
		return errors;
	}
	
	//helper function
	public boolean isBusinessDay(Calendar cal) {
		// check if sunday
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return false;
		}
		
		// check if New Year's Day
		if (cal.get(Calendar.MONTH) == Calendar.JANUARY
			&& cal.get(Calendar.DAY_OF_MONTH) == 1) {
			return false;
		}
		
		// check if Christmas
		if (cal.get(Calendar.MONTH) == Calendar.DECEMBER
			&& cal.get(Calendar.DAY_OF_MONTH) == 25) {
			return false;
		}
		
		// check if 4th of July
		if (cal.get(Calendar.MONTH) == Calendar.JULY
			&& cal.get(Calendar.DAY_OF_MONTH) == 4) {
			return false;
		}
		
		// check Thanksgiving (4th Thursday of November)
		if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
			&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 4
			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			return false;
		}
		
		// check Memorial Day (last Monday of May)
		if (cal.get(Calendar.MONTH) == Calendar.MAY
			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
			&& cal.get(Calendar.DAY_OF_MONTH) > (31 - 7) ) {
			return false;
		}
		
		// check Labor Day (1st Monday of September)
		if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER
			&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1
			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			return false;
		}
		
		// check President's Day (3rd Monday of February)
		if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY
		&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
		&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			return false;
		}
		
		// check Veterans Day (November 11)
		if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
		&& cal.get(Calendar.DAY_OF_MONTH) == 11) {
			return false;
		}
		
		// check MLK Day (3rd Monday of January)
		if (cal.get(Calendar.MONTH) == Calendar.JANUARY
		&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
		&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			return false;
		}
		
		//check Columbus Day (2nd Mon in Oct)
		if(cal.get(Calendar.MONTH) == Calendar.OCTOBER
		&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 2
		&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			return false;
		}
		
		// IF NOTHING ELSE, IT'S A BUSINESS DAY
		return true;
	}
	
	//location helper function
	public int lineNum(int section, int batch, int entry, ACHFile file) {
		switch(section) {
			case (1):
				return 1;//1
			case(5):{
				if(batch == 1)//2
					return 2;
				return lineNum(8, batch-1, 0, file) + 1;
			}
			case(6):{
				return lineNum(5, batch, 0, file) + entry;
			}
			case(8):{
				return lineNum(5, batch, 0, file) + Integer.valueOf(file.getBatchDetail().get(batch-1).getCompanyBatchControl().getEntryCount()) + 1;
			}
			case(9):
				return file.getNumOfLines()-file.getBlocking().size();//22
			default:
				return 0;
		}
	}
}//class
