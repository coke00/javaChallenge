package com.jiraira.pruebaTec.application;

import com.jiraira.pruebaTec.adapter.out.model.PriceEntity;
import com.jiraira.pruebaTec.application.utils.Constants;
import com.jiraira.pruebaTec.domain.exception.PriceNotFoundException;
import com.jiraira.pruebaTec.port.out.db.DbPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private DbPort dbPort;


    public Optional<PriceEntity> findApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) throws PriceNotFoundException {

        return Optional.ofNullable(dbPort.findPriceApplicableByProductIdBrandIdAndDate(productId, brandId, applicationDate)
                .stream()
                .max(Comparator.comparing(PriceEntity::getPriority))
                .orElseThrow(() -> new PriceNotFoundException(String.format(Constants.PRICE_NOT_FOUND_FORMAT, productId, brandId, applicationDate))));
    }
}
