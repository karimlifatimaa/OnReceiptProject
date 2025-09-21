package com.example.receiptservice.dto.receiptItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class ReceiptItemCreateDto {
    @NotNull(message = "Drug ID is required")
    private UUID drugId;

    @NotBlank(message = "Dosage cannot be blank")
    @Size(max = 255, message = "Dosage must be less than 255 characters")
    private String dosage;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Instructions cannot be blank")
    private String instructions;
}
