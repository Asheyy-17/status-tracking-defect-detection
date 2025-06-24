package com.example.status_tracking.repository;

import com.example.status_tracking.models.ProductionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductionStatusRepository extends JpaRepository<ProductionStatus, Long> {
    List<ProductionStatus> findByTrpNumber(String trpNumber);
}
