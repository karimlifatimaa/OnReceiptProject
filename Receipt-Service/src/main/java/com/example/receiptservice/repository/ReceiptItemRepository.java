package com.example.receiptservice.repository;

import com.example.receiptservice.entity.ReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReceiptItemRepository extends JpaRepository <ReceiptItem, UUID>{
}
