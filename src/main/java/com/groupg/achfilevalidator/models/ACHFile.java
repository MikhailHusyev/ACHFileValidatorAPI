package com.groupg.achfilevalidator.models;

import org.beanio.annotation.Segment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ACHFile {
    
    @Segment(name = "fileHeader")
    private FileHeader fileHeader;
    @Segment(name = "companyBatchHeader")
    private CompanyBatchHeader companyBatchHeader;
    @Segment(name = "companyBatchControl")
    private CompanyBatchControl companyBatchControl;
    @Segment(name = "fileControl")
    private FileControl fileControl;
    @Segment(name = "entryDetail")
    private ArrayList<EntryDetail> entryDetail;
    @Segment(name = "entryDetailAddenda")
    private ArrayList<EntryDetailAddenda> entryDetailAddenda;

    public FileHeader getFileHeader() {
        return fileHeader;
    }
    
    public void setFileHeader(FileHeader fileHeader) {
        this.fileHeader = fileHeader;
    }

    public CompanyBatchHeader getCompanyBatchHeader() {
        return companyBatchHeader;
    }

    public void setCompanyBatchHeader(CompanyBatchHeader companyBatchHeader) {
        this.companyBatchHeader = companyBatchHeader;
    }
    public ArrayList<EntryDetailAddenda> getEntryDetailAddenda() {
        return entryDetailAddenda;
    }

    public void setEntryDetailAddenda(ArrayList<EntryDetailAddenda> entryDetailAddenda) {
        this.entryDetailAddenda = entryDetailAddenda;
    }

    public ArrayList<EntryDetail> getEntryDetail() {
        return entryDetail;
    }

    public void setEntryDetail(ArrayList<EntryDetail> entryDetail) {
        this.entryDetail = entryDetail;
    }

    public FileControl getFileControl() {
        return fileControl;
    }

    public void setFileControl(FileControl fileControl) {
        this.fileControl = fileControl;
    }

    public CompanyBatchControl getCompanyBatchControl() {
        return companyBatchControl;
    }

    public void setCompanyBatchControl(CompanyBatchControl companyBatchControl) {
        this.companyBatchControl = companyBatchControl;
    }
}