package com.example.status_tracking.service;

import com.example.status_tracking.models.ProductionStatus;
import com.example.status_tracking.repository.ProductionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionStatusService {

    @Autowired
    private ProductionStatusRepository repository;

    public ProductionStatus saveStatus(ProductionStatus status) {
        return repository.save(status);
    }

    public List<ProductionStatus> getByTrp(String trpNumber) {
        return repository.findByTrpNumber(trpNumber);
    }
}