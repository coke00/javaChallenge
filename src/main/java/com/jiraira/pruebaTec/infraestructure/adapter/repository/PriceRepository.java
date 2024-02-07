package com.jiraira.pruebaTec.infraestructure.adapter.repository;

import com.jiraira.pruebaTec.application.dto.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE  p.productId = :productId AND p.brandId = :brandId AND p.startDate <= :applicationDate AND p.endDate >= :applicationDate")
    List<Price> findPriceByProductIdBrandIdAndApplicationDate(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
