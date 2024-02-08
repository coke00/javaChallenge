package com.jiraira.pruebaTec.application.service;

import com.jiraira.pruebaTec.application.dto.Price;
import com.jiraira.pruebaTec.domain.exception.PriceNotFoundException;
import com.jiraira.pruebaTec.domain.service.PriceService;
import com.jiraira.pruebaTec.infraestructure.adapter.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> findApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) throws PriceNotFoundException {

        return Optional.ofNullable(priceRepository.findPriceByProductIdBrandIdAndApplicationDate(productId, brandId, applicationDate)
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new PriceNotFoundException("Applicable price not found for productId: " + productId + ", brandId: " + brandId + ", and date: " + applicationDate)));
    }
}
