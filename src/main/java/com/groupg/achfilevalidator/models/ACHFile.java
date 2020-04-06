package com.groupg.achfilevalidator.models;

import java.util.Collection;
import java.util.List;

import org.beanio.annotation.Group;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ACHFile {
    @Segment(name="fileHeader")
    private FileHeader fileHeader;
    @Segment(name = "fileControl")
    private FileControl fileControl;
    @Segment(name="batchDetail")
    private ArrayList<BatchDetail> batchDetail;
    
	  public ArrayList<BatchDetail> getBatchDetail(){
    	return batchDetail;
    }
    public void setBatchDetail(ArrayList<BatchDetail> batchDetail) {
    	this.batchDetail = batchDetail;
    }

    public FileHeader getFileHeader() {
        return fileHeader;
    }

    public void setFileHeader(FileHeader fileHeader) {
        this.fileHeader = fileHeader;
    }

    public FileControl getFileControl() {
        return fileControl;
    }

    public void setFileControl(FileControl fileControl) {
        this.fileControl = fileControl;
    }

}