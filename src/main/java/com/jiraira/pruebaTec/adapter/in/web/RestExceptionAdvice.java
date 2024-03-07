package com.jiraira.pruebaTec.adapter.in.web;

import com.jiraira.pruebaTec.application.dto.ApiResultResponse;
import com.jiraira.pruebaTec.application.utils.Constants;
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
        return new ResponseEntity<>(new ApiResultResponse(HttpStatus.NOT_FOUND.value(), String.format(Constants.MESSAGE_ERROR_NOTFOUND, ex.getMessage()), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ApiResultResponse(HttpStatus.NOT_FOUND.value(), String.format(Constants.MESSAGE_ERROR_NOTFOUND, ex.getMessage()), null), HttpStatus.NOT_FOUND);
    }
}
