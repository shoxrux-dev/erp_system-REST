package com.system.erp_system.service;

import com.system.erp_system.domain.IncomingProduct;
import com.system.erp_system.domain.IncomingProductToInventory;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.IncomingProductToInventoryRepository;
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
public class IncomingProductToInventoryService {
    private final IncomingProductToInventoryRepository incomingProductToInventoryRepository;

    @Transactional
    public void create(IncomingProduct incomingProduct) {
        IncomingProductToInventory incomingProductToInventory = IncomingProductToInventory.builder()
                .id(UUID.randomUUID())
                .productId(incomingProduct.getProductId())
                .inventoryId(incomingProduct.getInventoryId())
                .count(incomingProduct.getCount()).build();
        Instant now = Instant.now();
        incomingProductToInventory.setCreatedAt(now);
        incomingProductToInventory.setUpdatedAt(now);
        incomingProductToInventoryRepository.save(incomingProductToInventory);
    }

    @Transactional
    public void update(IncomingProduct oldIncomingProduct, IncomingProduct newIncomingProduct) {
        Instant start = oldIncomingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = oldIncomingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        IncomingProductToInventory byUpdatedAtBetween = getByUpdatedAtBetween(start, end);

        byUpdatedAtBetween.setProductId(newIncomingProduct.getProductId());
        byUpdatedAtBetween.setInventoryId(newIncomingProduct.getInventoryId());
        byUpdatedAtBetween.setCount(newIncomingProduct.getCount());
        byUpdatedAtBetween.setUpdatedAt(Instant.now());
        incomingProductToInventoryRepository.save(byUpdatedAtBetween);
    }

    public List<IncomingProductToInventory> getAll() {
        List<IncomingProductToInventory> incomingProductToInventories = incomingProductToInventoryRepository.findAll();
        Collections.sort(incomingProductToInventories);
        return incomingProductToInventories;
    }

    public IncomingProductToInventory get(UUID id) {
        return incomingProductToInventoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("incoming product to inventory not found with %s id", id)));
    }

    public IncomingProductToInventory getByUpdatedAtBetween(Instant start, Instant end) {
        return incomingProductToInventoryRepository.findIncomingProductToInventoryByUpdatedAtBetween(start, end)
                .orElseThrow(() -> new RecordNotFoundException(String.format("incoming product to inventory not found with %s start time and %s end time", start, end)));
    }

}
