package com.system.erp_system.service;

import com.system.erp_system.domain.IncomingProduct;
import com.system.erp_system.domain.OutgoingProduct;
import com.system.erp_system.domain.ProductInInventory;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.ProductInInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductInInventoryService {
    private final ProductInInventoryRepository productInInventoryRepository;

    @Transactional
    public void add(IncomingProduct incomingProduct) {
        Optional<ProductInInventory> productInInventoryByProductIdAndInventoryId = productInInventoryRepository.findProductInInventoryByProductIdAndInventoryId(incomingProduct.getProductId(), incomingProduct.getInventoryId());

        ProductInInventory productInInventory;
        if(productInInventoryByProductIdAndInventoryId.isPresent()){
            productInInventory = productInInventoryByProductIdAndInventoryId.get();
            productInInventory.setCount(productInInventory.getCount() + incomingProduct.getCount());
            productInInventory.setUpdatedAt(Instant.now());
        }else{
            productInInventory = ProductInInventory.builder()
                    .id(UUID.randomUUID())
                    .productId(incomingProduct.getProductId())
                    .count(incomingProduct.getCount())
                    .inventoryId(incomingProduct.getInventoryId()).build();
            Instant now = Instant.now();
            productInInventory.setCreatedAt(now);
            productInInventory.setUpdatedAt(now);
        }
        productInInventoryRepository.save(productInInventory);
    }

    @Transactional
    public void update(IncomingProduct oldIncomingProduct, IncomingProduct newIncomingProduct) {
        ProductInInventory byProductIdAndInventoryId1 = getByProductIdAndInventoryId(oldIncomingProduct.getProductId(), oldIncomingProduct.getInventoryId());
        byProductIdAndInventoryId1.setInventoryId(newIncomingProduct.getInventoryId());
        byProductIdAndInventoryId1.setProductId(newIncomingProduct.getProductId());
        byProductIdAndInventoryId1.setCount(byProductIdAndInventoryId1.getCount() - oldIncomingProduct.getCount() + newIncomingProduct.getCount());
        byProductIdAndInventoryId1.setUpdatedAt(Instant.now());
        productInInventoryRepository.save(byProductIdAndInventoryId1);
    }

    @Transactional
    public void update(OutgoingProduct oldOutgoingProduct, OutgoingProduct newOutgoingProduct) {
        ProductInInventory byProductIdAndInventoryId1 = getByProductIdAndInventoryId(oldOutgoingProduct.getProductId(), oldOutgoingProduct.getInventoryId());
        byProductIdAndInventoryId1.setInventoryId(newOutgoingProduct.getInventoryId());
        byProductIdAndInventoryId1.setProductId(newOutgoingProduct.getProductId());
        byProductIdAndInventoryId1.setCount(byProductIdAndInventoryId1.getCount() + oldOutgoingProduct.getCount() - oldOutgoingProduct.getCount());
        byProductIdAndInventoryId1.setUpdatedAt(Instant.now());
        productInInventoryRepository.save(byProductIdAndInventoryId1);
    }

    public List<ProductInInventory> getAll() {
        return productInInventoryRepository.findAll();
    }

    public ProductInInventory getByProductIdAndInventoryId(UUID productId, UUID inventoryId) {
        return productInInventoryRepository.findProductInInventoryByProductIdAndInventoryId(productId, inventoryId)
                .orElseThrow(() -> new RecordNotFoundException(String.format("product to inventory not found with %s product id and %s inventory id", productId, inventoryId)));
    }

    protected void separate(OutgoingProduct outgoingProduct) {
        ProductInInventory byProductIdAndInventoryId = getByProductIdAndInventoryId(outgoingProduct.getProductId(), outgoingProduct.getInventoryId());
        byProductIdAndInventoryId.setCount(byProductIdAndInventoryId.getCount() - outgoingProduct.getCount());
        productInInventoryRepository.save(byProductIdAndInventoryId);
    }

}
