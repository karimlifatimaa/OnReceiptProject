package com.example.receiptservice.repository;

import com.example.receiptservice.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
    Optional<Receipt> findBySerialNumber(String serialNumber);
}
