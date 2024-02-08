package com.jiraira.pruebaTec.application.service;

import com.jiraira.pruebaTec.application.dto.Price;
import com.jiraira.pruebaTec.infraestructure.adapter.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    void whenFindApplicablePrice_thenReturnSingleApplicablePrice() {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        Integer productId = 1;
        Integer brandId = 2;
        Price expectedPrice = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-13T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .priority(0)
                .price(new BigDecimal("30.50"))
                .curr("EUR")
                .build();
        when(priceRepository.findPriceByProductIdBrandIdAndApplicationDate(productId, brandId, applicationDate))
                .thenReturn(Collections.singletonList(expectedPrice));

        Optional<Price> priceResponse = priceService.findApplicablePrice(productId, brandId, applicationDate);
        assertTrue(priceResponse.isPresent());
        assertEquals(expectedPrice, priceResponse.get());
        assertEquals(Optional.of(expectedPrice), priceResponse);
    }

    @Test
    void whenFindApplicablePrice_thenReturnMultipleApplicablePriceSelectingMaxPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        Integer productId = 1;
        Integer brandId = 2;
        List<Price> expectedPriceList = new ArrayList<>();
        Price price1 = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-13T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .priority(0)
                .productId(1)
                .price(new BigDecimal("30.50"))
                .curr("EUR")
                .build();
        Price price2 = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-13T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .priority(1)
                .productId(1)
                .price(new BigDecimal("40.50"))
                .curr("EUR")
                .build();
        expectedPriceList.add(price1);
        expectedPriceList.add(price2);
        when(priceRepository.findPriceByProductIdBrandIdAndApplicationDate(productId, brandId, applicationDate))
                .thenReturn(expectedPriceList);

        Optional<Price> priceResponse = priceService.findApplicablePrice(productId, brandId, applicationDate);
        assertTrue(priceResponse.isPresent());
        assertEquals(expectedPriceList.get(1).getPriority(), priceResponse.get().getPriority());
        assertEquals(Optional.of(expectedPriceList.get(1)), priceResponse);
    }

    @Test
    void whenFindApplicablePrice_thenReturnEmptyOptional() {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        Integer productId = 1;
        Integer brandId = 2;
        Price expectedPrice = Price.builder().build();
        when(priceRepository.findPriceByProductIdBrandIdAndApplicationDate(productId, brandId, applicationDate))
                .thenReturn(Collections.singletonList(expectedPrice));

        Optional<Price> priceResponse = priceService.findApplicablePrice(productId, brandId, applicationDate);
        assertEquals(priceResponse, Optional.of(expectedPrice));
    }
}
