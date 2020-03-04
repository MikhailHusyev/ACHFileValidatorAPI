package com.groupg.achfilevalidator.models;

import org.beanio.annotation.Record;

public class FileHeader {
    private String recordCode;
    private String priorityCode;
    private String immediateDestination;
    private String immediateOrigin;
    private String fileCreationDate;
    private String fileCreationTime;
    private String fileIdModifier;
    private String recordSize;
    private String blockingFactor;
    private String formatCode;
    private String immediateDestinationName;
    private String immediateOriginName;
    private String referenceCode;

    public String getRecordCode() {
        return recordCode;
    }
    public void setRecordCode(final String recordCode) {
        this.recordCode = recordCode;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(final String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getImmediateOriginName() {
        return immediateOriginName;
    }

    public void setImmediateOriginName(final String immediateDestinationName) {
        this.immediateOriginName = immediateDestinationName;
    }

    public String getimmediateDestinationName() {
        return immediateDestinationName;
    }

    public void setimmediateDestinationName(final String immediateDestinationName) {
        this.immediateDestinationName = immediateDestinationName;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(final String formatCode) {
        this.formatCode = formatCode;
    }

    public String getBlockingFactor() {
        return blockingFactor;
    }

    public void setBlockingFactor(final String blockingFactor) {
        this.blockingFactor = blockingFactor;
    }

    public String getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(final String recordSize) {
        this.recordSize = recordSize;
    }

    public String getFileIdModifier() {
        return fileIdModifier;
    }

    public void setFileIdModifier(final String fileIdModifier) {
        this.fileIdModifier = fileIdModifier;
    }

    public String getFileCreationTime() {
        return fileCreationTime;
    }

    public void setFileCreationTime(final String fileCreationTime) {
        this.fileCreationTime = fileCreationTime;
    }

    public String getFileCreationDate() {
        return fileCreationDate;
    }

    public void setFileCreationDate(final String fileCreationDate) {
        this.fileCreationDate = fileCreationDate;
    }

    public String getImmediateOrigin() {
        return immediateOrigin;
    }

    public void setImmediateOrigin(final String immediateOrigin) {
        this.immediateOrigin = immediateOrigin;
    }

    public String getImmediateDestination() {
        return immediateDestination;
    }

    public void setImmediateDestination(final String immediateDestination) {
        this.immediateDestination = immediateDestination;
    }

    public String getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(final String priorityCode) {
        this.priorityCode = priorityCode;
    }


}
