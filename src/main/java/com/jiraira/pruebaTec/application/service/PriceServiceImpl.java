package com.jiraira.pruebaTec.application.service;

import com.jiraira.pruebaTec.application.dto.Price;
import com.jiraira.pruebaTec.domain.port.PriceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {
    @Override
    public Optional<Price> findApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return Optional.empty();
    }
}
