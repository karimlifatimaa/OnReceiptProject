package com.example.receiptservice.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItemUpdateDto {
    private Long receiptId;
    private UUID drugId;
    private String dosage;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    private String instructions;
}
