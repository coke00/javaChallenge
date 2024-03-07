package com.jiraira.pruebaTec.adapter.out;


import com.jiraira.pruebaTec.adapter.out.repostory.DbRepository;
import com.jiraira.pruebaTec.adapter.out.model.PriceEntity;
import com.jiraira.pruebaTec.port.out.db.DbPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DbAdapter implements DbPort {

    @Autowired
    private DbRepository dbRepository;
    @Override
    public List<PriceEntity> findPriceApplicableByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return dbRepository.findPriceByProductIdBrandIdAndApplicationDate(productId,brandId, applicationDate);
    }
}
