package com.ws101.Alastoy_Espano.EcommerceApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a standard error response returned by the API.
 *
 * @author Alastoy, Españo
 */
@Getter
@Setter
@AllArgsConstructor

public class ErrorResponse {

    /**
     * The time the error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * The HTTP status code (e.g., 400, 404, 500).
     */
    private int errorCode;

    /**
     * The error message.
     */
    private String message;
}
