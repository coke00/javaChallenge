package com.jiraira.pruebaTec.port.out.db;

import com.jiraira.pruebaTec.adapter.out.model.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface DbPort {
    List<PriceEntity> findPriceApplicableByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
