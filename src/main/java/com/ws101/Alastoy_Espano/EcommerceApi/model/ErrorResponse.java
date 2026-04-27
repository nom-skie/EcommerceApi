package com.ws101.Alastoy_Espano.EcommerceApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int errorCode;
    private String message;
}
