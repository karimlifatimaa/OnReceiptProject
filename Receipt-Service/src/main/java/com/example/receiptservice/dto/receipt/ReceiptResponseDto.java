package com.example.receiptservice.dto.receipt;

import com.example.receiptservice.dto.receiptItem.ReceiptItemResponseDto;
import com.example.receiptservice.entity.FundingSource;
import com.example.receiptservice.entity.ReceiptStatus;
import lombok.Data;
import java.util.UUID;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ReceiptResponseDto {
    private UUID id;
    private String serialNumber;
    private UUID citizenId;
    private UUID doctorId;
    private UUID hospitalId;
    private ReceiptStatus status;
    private FundingSource fundingSource;
    private String qrCodePayload;
    private OffsetDateTime issueDate;
    private OffsetDateTime fulfilledAt;
    private UUID fulfilledByPharmacyId;
    private List<ReceiptItemResponseDto> items;
}
