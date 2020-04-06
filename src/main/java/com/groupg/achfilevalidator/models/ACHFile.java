package com.groupg.achfilevalidator.models;

import java.util.Collection;
import java.util.List;

import org.beanio.annotation.Group;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

public class ACHFile {

    private FileHeader fileHeader;
    private CompanyBatchHeader companyBatchHeader;
    private Collection<EntryDetail> entryDetail;
    private Collection<EntryDetailAddenda> entryDetailAddenda;
    private CompanyBatchControl companyBatchControl;
    private FileControl fileControl;

    public FileHeader getFileHeader() {
        return fileHeader;
    }

    public Collection<EntryDetailAddenda> getEntryDetailAddenda() {
        return entryDetailAddenda;
    }

    public void setEntryDetailAddenda(Collection<EntryDetailAddenda> entryDetailAddenda) {
        this.entryDetailAddenda = entryDetailAddenda;
    }

    public Collection<EntryDetail> getEntryDetail() {
        return entryDetail;
    }

    public void setEntryDetail(Collection<EntryDetail> entryDetail) {
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

    public void setFileHeader(FileHeader fileHeader) {
        this.fileHeader = fileHeader;
    }

    public CompanyBatchHeader getCompanyBatchHeader() {
        return companyBatchHeader;
    }

    public void setCompanyBatchHeader(CompanyBatchHeader companyBatchHeader) {
        this.companyBatchHeader = companyBatchHeader;
    }
}