package com.example.status_tracking.repository;
import com.example.status_tracking.models.DefectResult;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DefectResultRepository extends JpaRepository<DefectResult, Long> {
    List<DefectResult> findByTrpNumber(String trpNumber);
}
