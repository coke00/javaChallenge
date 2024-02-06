package com.jiraira.pruebaTec.application.service;

import com.jiraira.pruebaTec.application.dto.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void whenFindApplicablePrice_thenReturnEmptyOptional() {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        Integer productId = 1;
        Integer brandId = 2;
        Optional<Price> priceResponse = priceService.findApplicablePrice(productId, brandId, applicationDate);
        assertEquals(priceResponse, Optional.empty());
    }
}
