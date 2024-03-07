package com.jiraira.pruebaTec.domain;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {
    private int priceList;
    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int productId;
    private int priority;
    private BigDecimal price;
    private Currency curr;

    public Price(int priceList, int brandId, LocalDateTime startDate, LocalDateTime endDate, int productId, int priority, BigDecimal price){
        this.priceList = priceList;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = Currency.EUR;


    }
}
