package com.groupg.achfilevalidator.services.validation;

import com.groupg.achfilevalidator.models.ACHFile;
import com.groupg.achfilevalidator.models.ErrorResponse;

public class ValidationTests {
	public ErrorResponse validHash(ACHFile file) {
		return ErrorResponse.CLEAN_FILE_NO_ERROR;
	}
}
