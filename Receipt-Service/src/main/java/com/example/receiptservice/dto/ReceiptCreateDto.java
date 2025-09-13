package com.example.receiptservice.dto;

import com.example.receiptservice.entity.FundingSource;
import com.example.receiptservice.entity.ReceiptStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptCreateDto {
    @NotBlank(message = "Serial number cannot be blank")
    @Size(max = 20, message = "Serial number cannot exceed 20 characters")
    private String serialNumber;

    @NotNull(message = "Citizen ID cannot be null")
    private Long citizenId;

    @NotNull(message = "Doctor ID cannot be null")
    private Long doctorId;

    @NotNull(message = "Hospital ID cannot be null")
    private Long hospitalId;

    @NotNull(message = "Status cannot be null")
    private ReceiptStatus status;

    @NotNull(message = "Funding source cannot be null")
    private FundingSource fundingSource;

    @NotBlank(message = "QR code payload cannot be blank")
    private String qrCodePayload;

    @NotNull(message = "Issue date cannot be null")
    private Instant issueDate;
}
