package com.groupg.achfilevalidator.models;

public class FileControlInfo {
	public int recordCode = 9;
	private int entryAndAddendaCount;
	private String entryHash;
	private String TTLDebitEntryAmount;
	private String TTLCreditEntryAmount;
	public int getEntryAndAddendaCount() {
		return entryAndAddendaCount;
	}
	public void setEntryAndAddendaCount(String entryAndAddendaCount) {
		this.entryAndAddendaCount = Integer.valueOf(entryAndAddendaCount);
	}
	public String getEntryHash() {
		return entryHash;
	}
	public void setEntryHash(String entryHash) {
		this.entryHash = entryHash;
	}
	public String getTTLDebitEntryAmount() {
		return TTLDebitEntryAmount;
	}
	public void setTTLDebitEntryAmount(String tTLDebitEntryAmount) {
		TTLDebitEntryAmount = tTLDebitEntryAmount;
	}
	public String getTTLCreditEntryAmount() {
		return TTLCreditEntryAmount;
	}
	public void setTTLCreditEntryAmount(String tTLCreditEntryAmount) {
		TTLCreditEntryAmount = tTLCreditEntryAmount;
	}
}
