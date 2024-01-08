package com.system.erp_system.service;

import com.system.erp_system.domain.OutgoingProduct;
import com.system.erp_system.domain.OutgoingProductFromInventory;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.OutgoingProductFromInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutgoingProductFromInventoryService {
    private final OutgoingProductFromInventoryRepository outgoingProductFromInventoryRepository;

    @Transactional
    public void create(OutgoingProduct outgoingProduct) {
        OutgoingProductFromInventory outgoingProductFromInventory = OutgoingProductFromInventory.builder()
                .id(UUID.randomUUID())
                .productId(outgoingProduct.getProductId())
                .inventoryId(outgoingProduct.getInventoryId())
                .count(outgoingProduct.getCount()).build();

        Instant now = Instant.now();
        outgoingProductFromInventory.setCreatedAt(now);
        outgoingProductFromInventory.setUpdatedAt(now);
        outgoingProductFromInventoryRepository.save(outgoingProductFromInventory);
    }

    public List<OutgoingProductFromInventory> getAll() {
        List<OutgoingProductFromInventory> productFromInventories = outgoingProductFromInventoryRepository.findAll();
        Collections.sort(productFromInventories);
        return productFromInventories;
    }

    @Transactional
    public void update(OutgoingProduct oldOutgoingProduct,OutgoingProduct newOutgoingProduct) {
        Instant start = oldOutgoingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = oldOutgoingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        OutgoingProductFromInventory byUpdatedAtBetween = getByUpdatedAtBetween(start, end);
        byUpdatedAtBetween.setProductId(newOutgoingProduct.getProductId());
        byUpdatedAtBetween.setInventoryId(newOutgoingProduct.getInventoryId());
        byUpdatedAtBetween.setCount(newOutgoingProduct.getCount());
        byUpdatedAtBetween.setUpdatedAt(Instant.now());
        outgoingProductFromInventoryRepository.save(byUpdatedAtBetween);
    }


    public OutgoingProductFromInventory getByUpdatedAtBetween(Instant start, Instant end) {
        return outgoingProductFromInventoryRepository.findOutgoingProductFromInventoryByUpdatedAtBetween(start, end)
                .orElseThrow(() -> new RecordNotFoundException(String.format("outgoing product from inventory not found with %s start time and %s end time", start, end)));
    }


}
