package com.jiraira.pruebaTec.infraestructure.adapter.web;

import com.jiraira.pruebaTec.application.dto.ApiResponse;
import com.jiraira.pruebaTec.domain.exception.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionAdvice {
    @ExceptionHandler(PriceNotFoundException.class)
    protected ResponseEntity<Object> handlePriceNotFound(PriceNotFoundException ex, WebRequest request) {
        /*String bodyOfResponse = "Price not found: " + ex.getMessage();
        new ApiResponse(404, "Price not found: " + ex.getMessage(), null)
        //ResponseEntity
        //                        .status(404)
        //                        .body(new ApiResponse(404, "Price not found", null)*/
        return new ResponseEntity<>( new ApiResponse(HttpStatus.NOT_FOUND.value(), "Price not found: " + ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        //String bodyOfResponse = "An unexpected error occurred: " + ex.getMessage();
        return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred: " + ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
