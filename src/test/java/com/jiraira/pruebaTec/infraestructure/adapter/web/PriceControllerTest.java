package com.jiraira.pruebaTec.infraestructure.adapter.web;

import com.jiraira.pruebaTec.application.dto.Price;
import com.jiraira.pruebaTec.domain.port.PriceService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @BeforeAll
    public static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    public void testGetPriceReturnsPrice() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Price price = Price.builder().price(new BigDecimal("35.50")).build();

        Mockito.when(priceService.findApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Optional.of(price));

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("35.50"));
    }

    @Test
    public void testGetPriceEmptyResponse() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Mockito.when(priceService.findApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isNotFound());
    }
}