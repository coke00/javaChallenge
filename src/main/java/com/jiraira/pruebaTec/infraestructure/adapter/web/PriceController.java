package com.jiraira.pruebaTec.infraestructure.adapter.web;

import com.jiraira.pruebaTec.application.dto.ApiResponse;
import com.jiraira.pruebaTec.domain.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getPrice(@RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                                                @RequestParam("productId") Integer productId,
                                                @RequestParam("brandId") Integer brandId) {


        return priceService.findApplicablePrice(productId, brandId, applicationDate)
                .map(price -> ResponseEntity.ok(new ApiResponse(200, "success", price)))
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body(new ApiResponse(404, "Price not found", null)));
    }
}
