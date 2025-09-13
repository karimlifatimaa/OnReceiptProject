package com.example.receiptservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptItemCreateDto {
    @NotNull(message = "Receipt ID cannot be null")
    private Long receiptId;

    @NotNull(message = "Drug ID cannot be null")
    private UUID drugId;

    @NotBlank(message = "Dosage cannot be blank")
    private String dosage;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Instructions cannot be blank")
    private String instructions;
}
