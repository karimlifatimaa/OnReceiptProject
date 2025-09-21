package com.example.receiptservice.dto.receipt;

import com.example.receiptservice.dto.receiptItem.ReceiptItemCreateDto;
import com.example.receiptservice.entity.FundingSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;
import java.util.List;

@Data
public class ReceiptCreateDto {
    @NotBlank(message = "Serial number is required")
    @Size(min = 1, max = 20, message = "Serial number must be between 1 and 20 characters")
    private String serialNumber;

    @NotNull(message = "Citizen ID is required")
    private UUID citizenId;

    @NotNull(message = "Doctor ID is required")
    private UUID doctorId;

    @NotNull(message = "Hospital ID is required")
    private UUID hospitalId;

    @NotNull(message = "Funding source is required")
    private FundingSource fundingSource;

    @NotBlank(message = "QR code payload is required")
    private String qrCodePayload;

    @Valid
    private List<ReceiptItemCreateDto> items;
}