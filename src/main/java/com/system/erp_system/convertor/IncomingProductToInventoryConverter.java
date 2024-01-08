package com.system.erp_system.convertor;

import com.system.erp_system.domain.IncomingProductToInventory;
import com.system.erp_system.domain.Inventory;
import com.system.erp_system.domain.Product;
import com.system.erp_system.dto.incoming_product_to_inventory.IncomingProductToInventoryResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class IncomingProductToInventoryConverter {
    public List<IncomingProductToInventoryResponseDto> from(
            List<IncomingProductToInventory> incomingProductToInventories,
            List<Inventory> inventories,
            List<Product> products
    ) {
        List<IncomingProductToInventoryResponseDto> incomingProductToInventoryResponseDtos = new ArrayList<>();

        incomingProductToInventories.parallelStream().forEach(incomingProductToInventory -> inventories.forEach(inventory -> products.parallelStream().forEach(product -> {
            if (
                    incomingProductToInventory.getProductId().equals(product.getId()) &&
                    incomingProductToInventory.getInventoryId().equals(inventory.getId())
            ) {
                incomingProductToInventoryResponseDtos.add(
                        IncomingProductToInventoryResponseDto.builder()
                                .id(incomingProductToInventory.getId())
                                .productId(incomingProductToInventory.getProductId())
                                .productName(product.getName())
                                .inventoryId(incomingProductToInventory.getInventoryId())
                                .inventoryName(inventory.getName())
                                .count(incomingProductToInventory.getCount())
                                .createdAt(incomingProductToInventory.getCreatedAt())
                                .updatedAt(incomingProductToInventory.getUpdatedAt())
                                .build()
                );
            }
        })));
        return incomingProductToInventoryResponseDtos;
    }

}
