package com.groupg.achfilevalidator.models;

public class FileHeaderInfo {
	public int recordCode = 1;
	private String immediateDestination;
	private String immediateOrigin;
	private String immediateOriginName;
	public String getImmediateDestination() {
		return immediateDestination;
	}
	public void setImmediateDestination(String immediateDestination) {
		this.immediateDestination = immediateDestination;
	}
	public String getImmediateOrigin() {
		return immediateOrigin;
	}
	public void setImmediateOrigin(String immediateOrigin) {
		this.immediateOrigin = immediateOrigin;
	}
	public String getImmediateOriginName() {
		return immediateOriginName;
	}
	public void setImmediateOriginName(String immediateOriginName) {
		this.immediateOriginName = immediateOriginName;
	}
}
