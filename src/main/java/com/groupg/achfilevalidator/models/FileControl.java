package com.groupg.achfilevalidator.models;

public class FileControl {
	public String recordCode;
	public String batchCount;
	public String blockCount;
	public String entryCount;
	public String entryHash;
	public String totalDebitAmount;
	public String totalCreditAmount;
	public String bankUse;
	
	public String getRecordCode() {
		return recordCode;
	}
	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	public String getBatchCount() {
		return batchCount;
	}
	public void setBatchCount(String batchCount) {
		this.batchCount = batchCount;
	}
	public String getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(String blockCount) {
		this.blockCount = blockCount;
	}
	public String getEntryCount() {
		return entryCount;
	}
	public void setEntryCount(String entryCount) {
		this.entryCount = entryCount;
	}
	public String getEntryHash() {
		return entryHash;
	}
	public void setEntryHash(String entryHash) {
		this.entryHash = entryHash;
	}
	public String getTotalDebitAmount() {
		return totalDebitAmount;
	}
	public void setTotalDebitAmount(String totalDebitAmount) {
		this.totalDebitAmount = totalDebitAmount;
	}
	public String getTotalCreditAmount() {
		return totalCreditAmount;
	}
	public void setTotalCreditAmount(String totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}
	public String getBankUse() {
		return bankUse;
	}
	public void setBankUse(String bankUse) {
		this.bankUse = bankUse;
	}
	
}
