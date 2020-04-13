package com.groupg.achfilevalidator.models;

import org.beanio.annotation.Segment;
import java.util.ArrayList;

public class ACHFile {
    @Segment(name="fileHeader")
    private FileHeader fileHeader;
    @Segment(name = "fileControl")
    private FileControl fileControl;
    @Segment(name="batchDetail")
    private ArrayList<BatchDetail> batchDetail;
    @Segment(name="blocking")
    private ArrayList<Blocking> blocking;
    
    public ArrayList<Blocking> getBlocking(){
    	return blocking;
    }
    
    public void setBlocking(ArrayList<Blocking> blocking) {
    	this.blocking = blocking;
    }
    
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