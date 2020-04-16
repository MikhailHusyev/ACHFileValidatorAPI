package com.groupg.achfilevalidator.services.validation.standardverification;

import java.util.ArrayList;
import java.util.List;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ErrorResponse;
import com.groupg.achfilevalidator.models.ValidationResponse;

public interface VerificationService {
    ErrorResponse validFileHash(ACHFile file);
    ArrayList<ErrorResponse> validBatchHash(ACHFile file);
    ArrayList<ErrorResponse> validServiceClass(ACHFile file);
    ArrayList<ErrorResponse> validBatchTotals(ACHFile file);
    ErrorResponse validFileTotals(ACHFile file);
    ErrorResponse validBatchCount(ACHFile file);
    ErrorResponse validBatchNum(ACHFile file);
    ErrorResponse validEntryCount(ACHFile file);
    ArrayList<ErrorResponse> validBatchEntryCount(ACHFile file);
    ArrayList<ErrorResponse> validCompanyID(ACHFile file);
    ErrorResponse validBlockingCount(ACHFile file);
    ArrayList<ErrorResponse> validAddenda(ACHFile file);
    
}   