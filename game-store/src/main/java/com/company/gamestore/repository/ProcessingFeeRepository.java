package com.company.gamestore.repository;

import com.company.gamestore.model.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessingFeeRepository extends JpaRepository<ProcessingFee, Integer> {
    Optional<ProcessingFee> findByProductType(String productType);

}
