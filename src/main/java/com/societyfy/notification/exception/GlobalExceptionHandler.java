package com.societyfy.notification.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 ** @author Kushal.dalasaniya
 *  @Description This is globe exception handler for the rest API calls
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * This is Grocery store Exception handler
     */
    @ExceptionHandler(SocietyfyException.class)
    public ResponseEntity<ErrorResponse> handleSudokuGameException(SocietyfyException ex){
        return null;
    }
}
