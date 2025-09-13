package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.ReceiptItemCreateDto;
import com.example.receiptservice.dto.ReceiptItemDto;
import com.example.receiptservice.dto.ReceiptItemUpdateDto;
import com.example.receiptservice.entity.Drug;
import com.example.receiptservice.entity.Receipt;
import com.example.receiptservice.entity.ReceiptItem;
import com.example.receiptservice.repository.DrugRepository;
import com.example.receiptservice.repository.ReceiptItemRepository;
import com.example.receiptservice.repository.ReceiptRepository;
import com.example.receiptservice.service.ReceiptItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptItemServiceImpl implements ReceiptItemService {

    private final ReceiptItemRepository receiptItemRepository;
    private final ReceiptRepository receiptRepository;
    private final DrugRepository drugRepository;

    @Override
    public ReceiptItemDto createReceiptItem(ReceiptItemCreateDto createDto) {
        log.info("Creating new receipt item with data: {}", createDto);
        Receipt receipt = receiptRepository.findById(createDto.getReceiptId())
                .orElseThrow(() -> new RuntimeException("Receipt not found with id: " + createDto.getReceiptId()));
        Drug drug = drugRepository.findById(createDto.getDrugId())
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + createDto.getDrugId()));

        ReceiptItem receiptItem = new ReceiptItem();
        receiptItem.setReceipt(receipt);
        receiptItem.setDrug(drug);
        receiptItem.setQuantity(createDto.getQuantity());
        receiptItem.setDosage(createDto.getDosage());
        receiptItem.setInstructions(createDto.getInstructions());

        ReceiptItem savedItem = receiptItemRepository.save(receiptItem);
        log.info("Successfully created receipt item with id: {}", savedItem.getId());
        return convertToDto(savedItem);
    }

    @Override
    public ReceiptItemDto getReceiptItemById(Long id) {
        log.info("Fetching receipt item with id: {}", id);
        ReceiptItem item = receiptItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Receipt item not found with id: {}", id);
                    return new RuntimeException("Receipt item not found with id: " + id);
                });
        log.info("Successfully fetched receipt item with id: {}", id);
        return convertToDto(item);
    }

    @Override
    public List<ReceiptItemDto> getAllReceiptItems() {
        log.info("Fetching all receipt items");
        List<ReceiptItem> items = receiptItemRepository.findAll();
        log.info("Found {} receipt items", items.size());
        return items.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptItemDto updateReceiptItem(Long id, ReceiptItemUpdateDto receiptItemUpdateDto) {
        log.info("Updating receipt item with id: {} with data: {}", id, receiptItemUpdateDto);
        ReceiptItem item = receiptItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Receipt item not found with id: {}", id);
                    return new RuntimeException("Receipt item not found with id: " + id);
                });

        if (receiptItemUpdateDto.getReceiptId() != null) {
            Receipt receipt = receiptRepository.findById(receiptItemUpdateDto.getReceiptId())
                    .orElseThrow(() -> new RuntimeException("Receipt not found with id: " + receiptItemUpdateDto.getReceiptId()));
            item.setReceipt(receipt);
        }
        if (receiptItemUpdateDto.getDrugId() != null) {
            Drug drug = drugRepository.findById(receiptItemUpdateDto.getDrugId())
                    .orElseThrow(() -> new RuntimeException("Drug not found with id: " + receiptItemUpdateDto.getDrugId()));
            item.setDrug(drug);
        }
        if (receiptItemUpdateDto.getQuantity() != null) {
            item.setQuantity(receiptItemUpdateDto.getQuantity());
        }
        if (receiptItemUpdateDto.getDosage() != null) {
            item.setDosage(receiptItemUpdateDto.getDosage());
        }
        if (receiptItemUpdateDto.getInstructions() != null) {
            item.setInstructions(receiptItemUpdateDto.getInstructions());
        }

        ReceiptItem updatedItem = receiptItemRepository.save(item);
        log.info("Successfully updated receipt item with id: {}", updatedItem.getId());
        return convertToDto(updatedItem);
    }

    @Override
    public void deleteReceiptItem(Long id) {
        log.info("Deleting receipt item with id: {}", id);
        if (!receiptItemRepository.existsById(id)) {
            log.error("Receipt item with id: {} not found for deletion", id);
            throw new RuntimeException("Receipt item not found with id: " + id);
        }
        receiptItemRepository.deleteById(id);
        log.info("Successfully deleted receipt item with id: {}", id);
    }

    private ReceiptItemDto convertToDto(ReceiptItem item) {
        ReceiptItemDto dto = new ReceiptItemDto();
        dto.setId(item.getId());
        dto.setReceiptId(item.getReceipt().getId());
        dto.setDrugId(item.getDrug().getId());
        dto.setQuantity(item.getQuantity());
        dto.setDosage(item.getDosage());
        dto.setInstructions(item.getInstructions());
        dto.setCreatedAt(item.getCreatedAt());
        dto.setUpdatedAt(item.getUpdatedAt());
        return dto;
    }
}
