package com.societyfy.notification.exception;

import com.societyfy.notification.model.ErrorResponse;
import com.societyfy.notification.utils.HeaderMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
        ErrorResponse err = new com.societyfy.notification.model.ErrorResponse();
        err.setCode(ex.getCode());
        err.setStatus(ex.getStatusCode().value());
        err.setMessage(ex.getMessage());

        HttpHeaders header = HeaderMapper.getHttpHeaders(ex.getCorrelationId());
        return ResponseEntity.status(ex.getStatusCode()).headers(header).body(err);
    }
}
