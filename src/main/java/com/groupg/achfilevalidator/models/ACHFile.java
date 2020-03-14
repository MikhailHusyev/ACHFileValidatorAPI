package com.groupg.achfilevalidator.models;

import java.util.Collection;
import java.util.List;

import org.beanio.annotation.Segment;

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
    private Collection<EntryDetail> entryDetail;
    @Segment(name = "entryDetailAddenda")
    private Collection<EntryDetailAddenda> entryDetailAddenda;

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