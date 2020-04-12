package com.groupg.achfilevalidator.models;

public class ErrorResponse extends Object{
	public String type;
	public int section;
	public int line;
	public int location;
	public String description;
	
	//Constant Error Types
	public static ErrorResponse ADDENDA_ERROR = new ErrorResponse("AddendaError", 00, 00, 00, "An addenda either does not exists when it should or does exist when it should not check entryDetail boolean");
	public static ErrorResponse HASH_CODE_ERROR = new ErrorResponse("HashCodeError", 00, 00, 00, "The Hash Code is incorrect");
	public static ErrorResponse DOLLAR_AMOUNT_ERROR = new ErrorResponse("DollarAmountError", 00, 00, 00, "The dollar amount does not match the totals");
	public static ErrorResponse SERVICE_TRANSACTION_ERROR = new ErrorResponse("ServiceTransactionError", 00, 00, 00, "The Service Code does not match the Transaction Codes");
	public static ErrorResponse COMPANY_ID_ERROR = new ErrorResponse("CompanyIDError", 00, 00, 00, "The Company ID in the Batch Header and Batch Control do not match");
	public static ErrorResponse BATCH_NUMBER_ERROR = new ErrorResponse("BatchNumberError", 00, 00, 00, "The Batch Number does not match in the Header and Control");
	public static ErrorResponse BLOCK_NUMBER_ERROR = new ErrorResponse("BlockNumberError", 00, 00, 00, "The Total number of lines in the file is not divisible by 10 or the filler lines are not all 9's");
	public static ErrorResponse BATCH_COUNT_ERROR = new ErrorResponse("BatchCountError", 00, 00, 00, "The number of Batches does not match the number in the File Control");
	public static ErrorResponse ENTRY_COUNT_ERROR = new ErrorResponse("EntryCountError", 00, 00, 00, "The number of Entries does not match the number in the File Control");
	public static ErrorResponse CLEAN_FILE_NO_ERROR = new ErrorResponse("CleanFileNoError", 00, 00, 00, "There were no errors found within the file");
	
	public ErrorResponse (String type, int section, int line, int location, String description) {
		this.type = type;
		this.section = section;
		this.line = line;
		this.location = location;
		this.description = description;
	}
	
	public ErrorResponse() {
	}
	
	@Override
	public String toString() {
		String result = "";
		
		System.out.println();
		result += "ErrorType: " + this.type + "\n";
		result += "In Section: " + this.section + "\n";
		result += "On Line: " + this.line + "\n";
		result += "Location (At Character): " + this.location + "\n";
		result += "Description: " + this.description + "\n";
		System.out.println();
		
		return result;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}