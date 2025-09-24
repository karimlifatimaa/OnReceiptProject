package com.example.receiptservice.repository;

import com.example.receiptservice.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrugRepository extends JpaRepository<Drug, UUID> {
}
