package com.jiraira.pruebaTec.infraestructure.adapter.web;

import com.jiraira.pruebaTec.application.dto.ApiResultResponse;
import com.jiraira.pruebaTec.application.utils.Constants;
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
    public ResponseEntity<ApiResultResponse> getPrice(@RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                                                      @RequestParam("productId") Integer productId,
                                                      @RequestParam("brandId") Integer brandId) {


        return priceService.findApplicablePrice(productId, brandId, applicationDate)
                .map(price -> ResponseEntity.ok(new ApiResultResponse(Constants.HTTP_STATUS_OK, Constants.MESSAGE_OK, price)))
                .orElseGet(() -> ResponseEntity
                        .status(Constants.HTTP_STATUS_NOTFOUND)
                        .body(new ApiResultResponse(Constants.HTTP_STATUS_NOTFOUND, Constants.MESSAGE_NOTFOUND, null)));
    }
}
