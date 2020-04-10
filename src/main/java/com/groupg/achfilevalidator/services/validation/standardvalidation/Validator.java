package com.groupg.achfilevalidator.services.validation.standardvalidation;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ValidationResponse;

public interface Validator {
    ValidationResponse validFileHash(ACHFile file);
    ValidationResponse validBatchHash(ACHFile file);
    ValidationResponse validServiceClass(ACHFile file, int batchNum);
    ValidationResponse serviceClassHelper(ACHFile file);
    ValidationResponse validBatchTotals(ACHFile file);
    ValidationResponse validFileTotals(ACHFile file);
    ValidationResponse validBatchCount(ACHFile file);
    ValidationResponse validEntryCount(ACHFile file);
    ValidationResponse validCompanyID(ACHFile file);
}   