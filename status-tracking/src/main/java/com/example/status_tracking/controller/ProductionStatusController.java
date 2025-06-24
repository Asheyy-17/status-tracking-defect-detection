package com.example.status_tracking.controller;

import com.example.status_tracking.models.ProductionStatus;
import com.example.status_tracking.service.ProductionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production")
@CrossOrigin(origins = "*")
public class ProductionStatusController {

    @Autowired
    private ProductionStatusService service;

    @PostMapping("/save")
    public ProductionStatus save(@RequestBody ProductionStatus status) {
        status.setDateCreated(java.time.LocalDate.now());
        return service.saveStatus(status);
    }

    @GetMapping("/trp/{trpNumber}")
    public List<ProductionStatus> getByTrp(@PathVariable String trpNumber) {
        return service.getByTrp(trpNumber);
    }
}
