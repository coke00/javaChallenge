package com.jiraira.pruebaTec.adapter.in.web;

import com.jiraira.pruebaTec.application.PriceService;
import com.jiraira.pruebaTec.application.dto.ApiResultResponse;
import com.jiraira.pruebaTec.application.utils.Constants;
import com.jiraira.pruebaTec.port.in.web.WebPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController implements WebPort {

    @Autowired
    private PriceService priceService;
    @Override
    public ResponseEntity<ApiResultResponse> getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceService.findApplicablePrice(productId, brandId, applicationDate)
                .map(price -> ResponseEntity.ok(new ApiResultResponse(Constants.HTTP_STATUS_OK, Constants.MESSAGE_OK, price)))
                .orElseGet(() -> ResponseEntity
                        .status(Constants.HTTP_STATUS_NOTFOUND)
                        .body(new ApiResultResponse(Constants.HTTP_STATUS_NOTFOUND, Constants.MESSAGE_NOTFOUND, null)));
    }
}
