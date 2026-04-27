package com.ws101.Alastoy_Espano.EcommerceApi.config;

import com.ws101.Alastoy_Espano.EcommerceApi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

/**
 * Global exception handler that catches application errors
 * and returns a standard {@link ErrorResponse}.
 *
 * @author Alastoy, Españo
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions and maps them to the appropriate HTTP status code.
     *
     * @param ex the thrown exception
     * @return the error response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if(ex instanceof IllegalArgumentException){
            status = HttpStatus.BAD_REQUEST;
        } else if(ex instanceof NullPointerException){
            status = HttpStatus.BAD_REQUEST;
        } else if(ex instanceof MethodArgumentTypeMismatchException){
            status = HttpStatus.BAD_REQUEST;
        }

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(status).body(error);
    }

}
