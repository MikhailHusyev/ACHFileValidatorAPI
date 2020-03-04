package com.groupg.achfilevalidator.services.validation;
import java.io.InputStream;
import org.springframework.core.io.InputStreamSource;

/**
 * Validation Service
 */
public interface Validate{

    String validate(InputStreamSource file);
}