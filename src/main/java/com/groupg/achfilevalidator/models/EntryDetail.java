package com.groupg.achfilevalidator.models;

public class EntryDetail {
	public String recordCode;
	public String transactionCode;
	public String receivingRoutingNum;
	public String receivingRoutingCheckDigit;
	public String receivingAccountNum;
	public String transactionAmount;
	public String receiverID;
	public String receiverName;
	public String discretionaryData;
	public String addendaBool;
	public String traceNum;
	
	public String getRecordCode() {
		return recordCode;
	}
	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getReceivingRoutingNum() {
		return receivingRoutingNum;
	}
	public void setReceivingRoutingNum(String receivingRoutingNum) {
		this.receivingRoutingNum = receivingRoutingNum;
	}
	public String getReceivingRoutingCheckDigit() {
		return receivingRoutingCheckDigit;
	}
	public void setReceivingRoutingCheckDigit(String receivingRoutingCheckDigit) {
		this.receivingRoutingCheckDigit = receivingRoutingCheckDigit;
	}
	public String getreceivingAccountNum() {
		return receivingAccountNum;
	}
	public void setreceivingAccountNum(String receivingAccountNum) {
		this.receivingAccountNum = receivingAccountNum;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getDiscretionaryData() {
		return discretionaryData;
	}
	public void setDiscretionaryData(String discretionaryData) {
		this.discretionaryData = discretionaryData;
	}
	public String getAddendaBool() {
		return addendaBool;
	}
	public void setAddendaBool(String addendaBool) {
		this.addendaBool = addendaBool;
	}
	public String getTraceNum() {
		return traceNum;
	}
	public void setTraceNum(String traceNum) {
		this.traceNum = traceNum;
	}
}
