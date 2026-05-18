package com.ws101.Alastoy_Espano.EcommerceApi.config;

import com.ws101.Alastoy_Espano.EcommerceApi.model.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Global exception handler that catches application errors
 * and returns a standard {@link ErrorResponse}.
 *
 * @author Alastoy, Españo
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // This runs when @Valid fails (e.g. blank username, short password)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {

        // Collect all the error messages into a simple list
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add("Field '" + error.getField() + "': " + error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
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
        } else if(ex instanceof EntityNotFoundException){
            status = HttpStatus.NOT_FOUND;
        } else if(ex instanceof DataIntegrityViolationException){
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
