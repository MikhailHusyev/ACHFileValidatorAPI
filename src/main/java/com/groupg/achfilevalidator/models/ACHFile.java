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
    private int numOfLines;
    
    public int getNumOfLines() {
    	int numLines = 0;
    	
    	if(this.getFileHeader() != null)
    		numLines++;
    	if(this.getFileControl() != null)
    		numLines++;
    	numLines += this.getBlocking().size();
    	for(int i = 0; i < this.getBatchDetail().size(); i++) {
    		if(this.getBatchDetail().get(i).getCompanyBatchHeader() != null)
    			numLines++;
    		if(this.getBatchDetail().get(i).getCompanyBatchControl() != null)
    			numLines++;
    		for(int j = 0; j < this.getBatchDetail().get(i).getEntryDetailList().size(); j++) {
    			if(this.getBatchDetail().get(i).getEntryDetailList().get(j).getEntryDetail() != null)
    				numLines++;
    			if(this.getBatchDetail().get(i).getEntryDetailList().get(j).getEntryDetailAddenda() != null)
    				numLines++;
    		}
    	}
    	
    	return numLines;
    }
    
    public void setNumOfLines(int numOfLines) {
    	this.numOfLines = numOfLines;
    }
    
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