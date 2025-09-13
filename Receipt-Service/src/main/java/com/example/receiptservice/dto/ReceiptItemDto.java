package com.example.receiptservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItemDto {
    private UUID id;
    private Long receiptId;
    private UUID drugId;
    private String dosage;
    private Integer quantity;
    private String instructions;
    private Instant createdAt;
    private Instant updatedAt;
}
