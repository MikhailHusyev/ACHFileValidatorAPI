package com.groupg.achfilevalidator.models;

public class ErrorResponse {
	public String type;
	public String location;
	public String description;
	
	public ErrorResponse (String type, String location, String description) {
		this.type = type;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}