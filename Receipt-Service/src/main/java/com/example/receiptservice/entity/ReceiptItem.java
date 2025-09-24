package com.example.receiptservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "receipt_items")
@Data
public class ReceiptItem {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    // Çox-Birə Əlaqəsi: Çoxlu ReceiptItem bir Receiptə aiddir.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;

    @Column(name = "drug_id", nullable = false)
    private UUID drugId;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "instructions", nullable = false, columnDefinition = "TEXT")
    private String instructions;
}