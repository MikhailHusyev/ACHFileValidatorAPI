package com.groupg.achfilevalidator.models;

import org.beanio.annotation.Segment;

public class ACHFile {
    
    @Segment(name = "fileHeader")
    private FileHeader fileHeader;
    @Segment(name = "companyBatchHeader")
    private CompanyBatchHeader companyBatchHeader;

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
}