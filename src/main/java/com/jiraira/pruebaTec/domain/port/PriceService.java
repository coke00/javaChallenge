package com.jiraira.pruebaTec.domain.port;

import com.jiraira.pruebaTec.application.dto.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<Price> findApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
