package com.groupg.achfilevalidator.models;

import java.util.Collection;
import org.beanio.annotation.Segment;

public class CompanyBatchHeader {
	private String recordCode;
	private String serviceClassCode;
	private String companyName;
	private String companyData;
	private String companyID;
	private String SECCode;
	private String entryDescription;
	private String descriptiveDate;
	private String entryDate;
	private String settlementDate;
	private String statusCode;
	private String DFIID;
	private String batchNum;


	public String getrecordCode() {
		return recordCode;
	}

	public void setrecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	public String getServiceClassCode() {
		return serviceClassCode;
	}
	public void setServiceClassCode(String serviceClassCode) {
		this.serviceClassCode = serviceClassCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyData() {
		return companyData;
	}
	public void setCompanyData(String companyData) {
		this.companyData = companyData;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getSECCode() {
		return SECCode;
	}
	public void setSECCode(String sECCode) {
		SECCode = sECCode;
	}
	public String getEntryDescription() {
		return entryDescription;
	}
	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}
	public String getDescriptiveDate() {
		return descriptiveDate;
	}
	public void setDescriptiveDate(String descriptiveDate) {
		this.descriptiveDate = descriptiveDate;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getDFIID() {
		return DFIID;
	}
	public void setDFIID(String dFIID) {
		DFIID = dFIID;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
}
