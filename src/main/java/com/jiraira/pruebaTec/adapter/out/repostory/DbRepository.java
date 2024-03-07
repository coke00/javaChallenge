package com.jiraira.pruebaTec.adapter.out.repostory;

import com.jiraira.pruebaTec.adapter.out.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DbRepository extends JpaRepository<PriceEntity, Long> {
    @Query("SELECT p FROM PriceEntity p WHERE  p.productId = :productId AND p.brandId = :brandId AND p.startDate <= :applicationDate AND p.endDate >= :applicationDate")
    List<PriceEntity> findPriceByProductIdBrandIdAndApplicationDate(Integer productId, Integer brandId, LocalDateTime applicationDate);

}
