package com.jiraira.pruebaTec.infraestructure.adapter.web;

import com.jiraira.pruebaTec.adapter.in.web.PriceController;
import com.jiraira.pruebaTec.adapter.out.model.PriceEntity;
import com.jiraira.pruebaTec.application.PriceService;
import com.jiraira.pruebaTec.application.dto.ApiResultResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PriceEntityControllerTest {

    @InjectMocks
    private PriceController priceController;
    @Mock
    private PriceService priceService;


    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);

    }

    @Test
    public void testGetPriceReturnsPrice() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        PriceEntity mockPriceEntity = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();
        ApiResultResponse apiResultResponseMock = ApiResultResponse.builder().status(200).message("success").data(mockPriceEntity).build();

        when(priceService.findApplicablePrice(productId, brandId, applicationDate)).thenReturn(Optional.of(mockPriceEntity));

        ResponseEntity<?> priceResponse = priceController.getPrice(applicationDate, productId, brandId);

        assertEquals(HttpStatus.OK, priceResponse.getStatusCode(), "Debería haber una respuesta de estado");
        assertEquals(apiResultResponseMock, priceResponse.getBody(), "Debería haber un precio devuelto");

    }

    @Test
    public void testGetPriceReturnsEmpty() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        ApiResultResponse apiResultResponseMock = ApiResultResponse.builder().status(404).message("Price not found").data(null).build();

        when(priceService.findApplicablePrice(productId, brandId, applicationDate)).thenReturn(Optional.empty());

        ResponseEntity<?> priceResponse = priceController.getPrice(applicationDate, productId, brandId);

        assertEquals(HttpStatus.NOT_FOUND, priceResponse.getStatusCode(), "Debería haber una respuesta de estado");
        assertEquals(apiResultResponseMock, priceResponse.getBody(), "No debería tener data en el body");

    }


}
