package com.societyfy.notification.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Kushal.dalasaniya
 * This is custom exception for Api calls
 */
@AllArgsConstructor
@Getter
public class SocietyfyException extends RuntimeException{
    private final HttpStatus statusCode;
    private final String code;
    private final String message;
    private final String correlationId;
}
