package com.jiraira.pruebaTec.infraestructure.adapter.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class PriceEntityControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
    }

    @Test
    public void testGetPriceReturnsPrice() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 1;

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.price").value("35.50"));
    }


    @Test
    public void testGetPriceEmptyResponse() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 2;
        String formattedDate = applicationDate.format(DateTimeFormatter.ISO_DATE_TIME);

        mockMvc.perform(get("/prices")
                        .param("applicationDate", formattedDate)
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void test1() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 1;
        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.priceList").value(1))
                .andExpect(jsonPath("$.data.priority").value(0))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.startDate").value("2020-06-14 00:00:00"))
                .andExpect(jsonPath("$.data.endDate").value("2020-12-31 23:59:59"))
                .andExpect(jsonPath("$.data.price").value("35.50"));
    }

    @Test
    public void test2() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        int productId = 35455;
        int brandId = 1;

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.priceList").value(2))
                .andExpect(jsonPath("$.data.priority").value(1))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.startDate").value("2020-06-14 15:00:00"))
                .andExpect(jsonPath("$.data.endDate").value("2020-06-14 18:30:00"))
                .andExpect(jsonPath("$.data.price").value("25.45"));
    }

    @Test
    public void test3() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        int productId = 35455;
        int brandId = 1;


        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.priceList").value(1))
                .andExpect(jsonPath("$.data.priority").value(0))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.startDate").value("2020-06-14 00:00:00"))
                .andExpect(jsonPath("$.data.endDate").value("2020-12-31 23:59:59"))
                .andExpect(jsonPath("$.data.price").value("35.50"));
    }

    @Test
    public void test4() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        int productId = 35455;
        int brandId = 1;

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.priceList").value(3))
                .andExpect(jsonPath("$.data.priority").value(1))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.startDate").value("2020-06-15 00:00:00"))
                .andExpect(jsonPath("$.data.endDate").value("2020-06-15 11:00:00"))
                .andExpect(jsonPath("$.data.price").value("30.50"));
    }

    @Test
    public void test5() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 21, 0);
        int productId = 35455;
        int brandId = 1;

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.priceList").value(4))
                .andExpect(jsonPath("$.data.priority").value(1))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.startDate").value("2020-06-15 16:00:00"))
                .andExpect(jsonPath("$.data.endDate").value("2020-12-31 23:59:59"))
                .andExpect(jsonPath("$.data.price").value("38.95"));
    }

    @Test
    void whenFindApplicablePrice_thenReturnThrowPriceNotFound() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        int productId = 1;
        int brandId = 2;
        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.message").value("Price not found: Applicable price not found for productId: 1, brandId: 2, and date: 2024-02-06T10:00."));

    }

    @Test
    void whenFindApplicablePrice_thenReturnThrowExceptionWhenBrandIdIsMissing() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        int productId = 1;

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.message").value("Price not found: Required request parameter 'brandId' for method parameter type Integer is not present"));
    }

    @Test
    void whenFindApplicablePrice_thenReturnThrowExceptionWhenProductIdIsMissing() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        int brandId = 2;

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.message").value("Price not found: Required request parameter 'productId' for method parameter type Integer is not present"));
    }

    @Test
    void whenFindApplicablePrice_thenReturnThrowExceptionWhenApplicationDateIsMissing() throws Exception {
        int productId = 1;
        int brandId = 2;

        mockMvc.perform(get("/prices")
                        .param("productId", Integer.toString(productId))
                        .param("brandId", Integer.toString(brandId)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.message").value("Price not found: Required request parameter 'applicationDate' for method parameter type LocalDateTime is not present"));
    }

    @Test
    void whenFindApplicablePrice_thenReturnThrowOtherException() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 2, 6, 10, 0);
        int productId = 1;
        String brandId = "a";

        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", Integer.toString(productId))
                        .param("brandId", brandId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.message").value("Price not found: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \"a\""));
    }

}
