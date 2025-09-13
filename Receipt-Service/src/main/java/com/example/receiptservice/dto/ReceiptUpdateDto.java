package com.example.receiptservice.dto;

import com.example.receiptservice.entity.FundingSource;
import com.example.receiptservice.entity.ReceiptStatus;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptUpdateDto {
    @Size(max = 20, message = "Serial number cannot exceed 20 characters")
    private String serialNumber;
    private Long citizenId;
    private Long doctorId;
    private Long hospitalId;
    private ReceiptStatus status;
    private FundingSource fundingSource;
    private String qrCodePayload;
    private Instant issueDate;
    private Instant fulfilledAt;
    private Long fulfilledByPharmacyId;
}
