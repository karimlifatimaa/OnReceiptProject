package com.example.receiptservice.dto.receipt;

import com.example.receiptservice.dto.receiptItem.ReceiptItemUpdateDto;
import com.example.receiptservice.entity.ReceiptStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import lombok.Data;
import java.util.UUID;
import java.util.List;

@Data
public class ReceiptUpdateDto {
    @NotNull(message = "Status is required")
    private ReceiptStatus status;
    private UUID fulfilledByPharmacyId;

    @Valid
    private List<ReceiptItemUpdateDto> items;
}