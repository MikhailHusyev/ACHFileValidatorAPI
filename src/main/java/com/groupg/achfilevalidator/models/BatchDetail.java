package com.groupg.achfilevalidator.models;

import java.util.ArrayList;

import org.beanio.annotation.Segment;

public class BatchDetail {
	@Segment(name="companyBatchHeader")
	private CompanyBatchHeader companyBatchHeader;
	@Segment(name="entryDetailList")
	private ArrayList<EntryDetailList> entryDetailList;
	@Segment(name="companyBatchControl")
	private CompanyBatchControl companyBatchControl;
	
	public CompanyBatchHeader getCompanyBatchHeader() {
		return companyBatchHeader;
	}
	public void setCompanyBatchHeader(CompanyBatchHeader companyBatchHeader) {
		this.companyBatchHeader = companyBatchHeader;
	}
	public CompanyBatchControl getCompanyBatchControl() {
		return companyBatchControl;
	}
	public void setCompanyBatchControl(CompanyBatchControl companyBatchControl) {
		this.companyBatchControl = companyBatchControl;
	}
	public ArrayList<EntryDetailList> getEntryDetailList() {
		return entryDetailList;
	}
	public void setEntryDetailList(ArrayList<EntryDetailList> entryDetailList) {
		this.entryDetailList = entryDetailList;
	}
	
	
}