package com.groupg.achfilevalidator.models;

import java.util.List;

public class APIResponse {

	private FileHeaderInfo fileHeader;
	private List<BatchHeaderInfo> batchHeaders;
	private List<BatchControlInfo> batchControls;
	private FileControlInfo fileControl;
    private List<ValidationResponse> errors;

    public List<ValidationResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationResponse> errors) {
        this.errors = errors;
    }

	public FileHeaderInfo getFileHeader() {
		return fileHeader;
	}

	public void setFileHeader(FileHeaderInfo fileHeader) {
		this.fileHeader = fileHeader;
	}

	public List<BatchHeaderInfo> getBatchHeaders() {
		return batchHeaders;
	}

	public void setBatchHeaders(List<BatchHeaderInfo> batchHeaders) {
		this.batchHeaders = batchHeaders;
	}

	public List<BatchControlInfo> getBatchControls() {
		return batchControls;
	}

	public void setBatchControls(List<BatchControlInfo> batchControls) {
		this.batchControls = batchControls;
	}

	public FileControlInfo getFileControl() {
		return fileControl;
	}

	public void setFileControl(FileControlInfo fileControl) {
		this.fileControl = fileControl;
	}
}