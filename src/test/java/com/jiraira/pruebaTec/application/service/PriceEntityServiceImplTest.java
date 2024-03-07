package com.jiraira.pruebaTec.application.service;

import com.jiraira.pruebaTec.adapter.out.model.PriceEntity;
import com.jiraira.pruebaTec.adapter.out.repostory.DbRepository;
import com.jiraira.pruebaTec.application.PriceService;
import com.jiraira.pruebaTec.domain.exception.PriceNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PriceEntityServiceImplTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private DbRepository priceRepository;

    @Test
    void whenFindApplicablePrice_thenReturnSingleApplicablePrice() {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        Integer productId = 1;
        Integer brandId = 2;
        PriceEntity expectedPriceEntity = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-13T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .priority(0)
                .price(new BigDecimal("30.50"))
                .curr("EUR")
                .build();
        when(priceRepository.findPriceByProductIdBrandIdAndApplicationDate(productId, brandId, applicationDate))
                .thenReturn(Collections.singletonList(expectedPriceEntity));

        Optional<PriceEntity> priceResponse = priceService.findApplicablePrice(productId, brandId, applicationDate);
        assertTrue(priceResponse.isPresent());
        assertEquals(expectedPriceEntity, priceResponse.get());
        assertEquals(Optional.of(expectedPriceEntity), priceResponse);
    }

    @Test
    void whenFindApplicablePrice_thenReturnMultipleApplicablePriceSelectingMaxPriority() {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        Integer productId = 1;
        Integer brandId = 2;
        List<PriceEntity> expectedPriceListEntity = new ArrayList<>();
        PriceEntity priceEntity1 = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-13T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .priority(0)
                .productId(1)
                .price(new BigDecimal("30.50"))
                .curr("EUR")
                .build();
        PriceEntity priceEntity2 = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-13T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .priority(1)
                .productId(1)
                .price(new BigDecimal("40.50"))
                .curr("EUR")
                .build();
        expectedPriceListEntity.add(priceEntity1);
        expectedPriceListEntity.add(priceEntity2);
        when(priceRepository.findPriceByProductIdBrandIdAndApplicationDate(productId, brandId, applicationDate))
                .thenReturn(expectedPriceListEntity);

        Optional<PriceEntity> priceResponse = priceService.findApplicablePrice(productId, brandId, applicationDate);
        assertTrue(priceResponse.isPresent());
        assertEquals(expectedPriceListEntity.get(1).getPriority(), priceResponse.get().getPriority());
        assertEquals(Optional.of(expectedPriceListEntity.get(1)), priceResponse);
    }

    @Test
    void whenFindApplicablePrice_thenReturnEmptyOptional() {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        Integer productId = 1;
        Integer brandId = 2;
        PriceEntity expectedPriceEntity = PriceEntity.builder().build();
        when(priceRepository.findPriceByProductIdBrandIdAndApplicationDate(productId, brandId, applicationDate))
                .thenReturn(Collections.singletonList(expectedPriceEntity));

        Optional<PriceEntity> priceResponse = priceService.findApplicablePrice(productId, brandId, applicationDate);
        assertEquals(priceResponse, Optional.of(expectedPriceEntity));
    }

    @Test
    void whenFindApplicablePrice_thenReturnEmptyOptionalWithThrow() throws PriceNotFoundException {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        Integer productId = 1;
        Integer brandId = 2;
        when(priceRepository.findPriceByProductIdBrandIdAndApplicationDate(productId, brandId, applicationDate))
                .thenReturn(List.of());

        assertThrows(PriceNotFoundException.class, () ->
                        priceService.findApplicablePrice(productId, brandId, applicationDate),
                "Expected PriceNotFoundException to be thrown when no price is found"
        );
    }
}
