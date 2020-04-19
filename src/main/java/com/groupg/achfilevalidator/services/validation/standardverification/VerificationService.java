package com.groupg.achfilevalidator.services.validation.standardverification;

import java.util.ArrayList;
import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ValidationResponse;

public interface VerificationService {
    ValidationResponse validFileHash(ACHFile file);
    ArrayList<ValidationResponse> validBatchHash(ACHFile file);
    ArrayList<ValidationResponse> validServiceClass(ACHFile file);
    ArrayList<ValidationResponse> validBatchTotals(ACHFile file);
    ValidationResponse validFileTotals(ACHFile file);
    ValidationResponse validBatchCount(ACHFile file);
    ValidationResponse validBatchNum(ACHFile file);
    ValidationResponse validEntryCount(ACHFile file);
    ArrayList<ValidationResponse> validBatchEntryCount(ACHFile file);
    ArrayList<ValidationResponse> validCompanyID(ACHFile file);
    ValidationResponse validBlockingCount(ACHFile file);
    ArrayList<ValidationResponse> validAddenda(ACHFile file);
    
}   