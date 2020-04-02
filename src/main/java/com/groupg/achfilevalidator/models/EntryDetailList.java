package com.groupg.achfilevalidator.models;

import org.beanio.annotation.Segment;

public class EntryDetailList {
	@Segment(name="entryDetail")
	private EntryDetail entryDetail;
	@Segment(name="entryDetailAddenda")
	private EntryDetailAddenda entryDetailAddenda;
	
	public EntryDetail getEntryDetail() {
		return entryDetail;
	}
	public void setEntryDetail(EntryDetail entryDetail) {
		this.entryDetail = entryDetail;
	}
	public EntryDetailAddenda getEntryDetailAddenda() {
		return entryDetailAddenda;
	}
	public void setEntryDetailAddenda(EntryDetailAddenda entryDetailAddenda) {
		this.entryDetailAddenda = entryDetailAddenda;
	}
	
	
}
