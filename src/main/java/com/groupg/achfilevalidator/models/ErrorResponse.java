package com.groupg.achfilevalidator.models;

public class ErrorResponse {
	public String type;
	public int section;
	public int line;
	public int location;
	public String description;
	
	//Constant Error Types
	public static ErrorResponse HASH_CODE_ERROR = new ErrorResponse("HashCodeError", 00, 00, 00, "The Hash Code is incorrect");
	public static ErrorResponse DOLLAR_AMOUNT_ERROR = new ErrorResponse("DollarAmountError", 00, 00, 00, "The dollar amount does not match the totals");
	
	
	
	public ErrorResponse (String type, int section, int line, int location, String description) {
		this.type = type;
		this.section = section;
		this.line = line;
		this.location = location;
		this.description = description;
	}
	
	public ErrorResponse() {
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