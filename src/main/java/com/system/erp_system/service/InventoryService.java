package com.system.erp_system.service;

import com.system.erp_system.domain.Inventory;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.InventoryRepository;
import com.system.erp_system.validation.CommonSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final CommonSchemaValidator commonSchemaValidator;

    @Transactional
    public Inventory create(Inventory inventory) {
        commonSchemaValidator.inventoryNotExist(inventory);
        inventory.setId(UUID.randomUUID());
        Instant now = Instant.now();
        inventory.setCreatedAt(now);
        inventory.setUpdatedAt(now);
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    public Inventory get(UUID id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("inventory not found with %s id", id)));
    }

    @Transactional
    public Inventory update(UUID id, Inventory inventory) {
        Inventory inventory1 = get(id);
        inventory1.setName(inventory.getName());
        inventory1.setUpdatedAt(Instant.now());
        return inventoryRepository.save(inventory1);
    }

    @Transactional
    public void delete(UUID id) {
        Inventory inventory = get(id);
        inventoryRepository.delete(inventory);
    }

}
