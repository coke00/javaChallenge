package com.jiraira.pruebaTec.domain.service;

import com.jiraira.pruebaTec.application.dto.Price;
import com.jiraira.pruebaTec.domain.exception.PriceNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<Price> findApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) throws PriceNotFoundException;
}
