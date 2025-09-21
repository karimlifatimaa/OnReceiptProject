package com.example.receiptservice.dto.receiptItem;

import lombok.Data;
import java.util.UUID;

@Data
public class ReceiptItemResponseDto {
    private UUID id;
    private UUID drugId;
    private String dosage;
    private Integer quantity;
    private String instructions;
}
