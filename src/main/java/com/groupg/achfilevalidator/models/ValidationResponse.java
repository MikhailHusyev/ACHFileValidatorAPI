package com.groupg.achfilevalidator.models;

public class ValidationResponse extends Object{
	public String type;
	public int section;
	public int line;
	public int location;
	public int length;
	public String description;
	
	public ValidationResponse (String type, int section, int line, int location, int length, String description) {
		this.type = type;
		this.section = section;
		this.line = line;
		this.location = location;
		this.length = length;
		this.description = description;
	}
	
	public ValidationResponse() {
	}
	
	@Override
	public String toString() {
		String result = "";
		
		System.out.println();
		result += "ErrorType: " + this.type + "\n";
		result += "In Section: " + this.section + "\n";
		result += "On Line: " + this.line + "\n";
		result += "Location (At Character): " + this.location + "\n";
		result += "With length: " + this.length + "\n";
		result += "Description: " + this.description + "\n";
		System.out.println();
		
		return result;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
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