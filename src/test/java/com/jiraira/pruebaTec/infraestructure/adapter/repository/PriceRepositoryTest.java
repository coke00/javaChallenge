package com.jiraira.pruebaTec.infraestructure.adapter.repository;

import com.jiraira.pruebaTec.application.dto.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PriceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceRepository priceRepository;


    @Test
    void findPriceByProductIdBrandIdAndApplicationDate() {
        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setStartDate(LocalDateTime.parse("2020-06-13T00:00:00"));
        price.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price.setPriceList(1);
        price.setPriority(0);
        price.setPrice(new BigDecimal("35.50"));
        price.setCurr("EUR");
        entityManager.persist(price);
        entityManager.flush();

        List<Price> prices = priceRepository.findPriceByProductIdBrandIdAndApplicationDate(35455, 1, LocalDateTime.parse("2020-06-14T00:00:00"));

        assertFalse(prices.isEmpty(), "La lista de precios no debería estar vacía");
        assertEquals(1, prices.size(), "Debería haber un precio devuelto");
        assertEquals(price.getPrice(), prices.get(0).getPrice(), "El precio devuelto debería coincidir con el precio esperado");
    }
    @Test
    void findPriceByProductIdBrandIdAndApplicationDate_ReturnEmpty() {
        List<Price> prices = priceRepository.findPriceByProductIdBrandIdAndApplicationDate(35455, 1, LocalDateTime.parse("2020-06-14T00:00:00"));

        assertTrue(prices.isEmpty(), "La lista de precios está vacía");
        assertEquals(0, prices.size(), "Debería haber un precio devuelto");
    }
}
