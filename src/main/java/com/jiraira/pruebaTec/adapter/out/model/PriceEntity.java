package com.jiraira.pruebaTec.adapter.out.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntity {
    @Id
    @Column(name="PRICE_LIST")
    private Integer priceList;
    @Column(name="BRAND_ID")
    private Integer brandId;
    @Column(name="START_DATE")
    private LocalDateTime startDate;
    @Column(name="END_DATE")
    private LocalDateTime endDate;
    @Column(name="PRODUCT_ID")
    private Integer productId;
    @Column(name="PRIORITY")
    private Integer priority;
    @Column(name="PRICE")
    private BigDecimal price;
    @Column(name="CURR")
    private String curr;
}
