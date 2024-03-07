package com.jiraira.pruebaTec.port.in.web;

import com.jiraira.pruebaTec.application.dto.ApiResultResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RequestMapping("/prices")
public interface WebPort {

    @GetMapping
    ResponseEntity<ApiResultResponse> getPrice(@RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                                               @RequestParam("productId") Integer productId,
                                               @RequestParam("brandId") Integer brandId);
}
