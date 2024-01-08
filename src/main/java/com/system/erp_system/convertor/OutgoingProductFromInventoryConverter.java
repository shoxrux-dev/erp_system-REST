package com.system.erp_system.convertor;

import com.system.erp_system.domain.Inventory;
import com.system.erp_system.domain.OutgoingProductFromInventory;
import com.system.erp_system.domain.Product;
import com.system.erp_system.dto.outgoing_product_from_inventory.OutgoingProductFromInventoryResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OutgoingProductFromInventoryConverter {
    public List<OutgoingProductFromInventoryResponseDto> from(
            List<OutgoingProductFromInventory> outgoingProductFromInventories,
            List<Product> products,
            List<Inventory> inventories
    ) {
        List<OutgoingProductFromInventoryResponseDto> outgoingProductFromInventoryResponseDtos = new ArrayList<>();

        outgoingProductFromInventories.parallelStream().forEach(outgoingProductFromInventory ->
                inventories.forEach(inventory ->
                        products.parallelStream().forEach(product ->
                        {
                            if (
                                    outgoingProductFromInventory.getProductId().equals(product.getId()) &&
                                            outgoingProductFromInventory.getInventoryId().equals(inventory.getId())
                            ) {
                                outgoingProductFromInventoryResponseDtos.add(
                                        OutgoingProductFromInventoryResponseDto.builder()
                                                .id(outgoingProductFromInventory.getId())
                                                .productId(outgoingProductFromInventory.getProductId())
                                                .productName(product.getName())
                                                .inventoryId(outgoingProductFromInventory.getInventoryId())
                                                .inventoryName(inventory.getName())
                                                .count(outgoingProductFromInventory.getCount())
                                                .createdAt(outgoingProductFromInventory.getCreatedAt())
                                                .updatedAt(outgoingProductFromInventory.getUpdatedAt())
                                                .build()
                                );
                            }
                        }
                        )));
        return outgoingProductFromInventoryResponseDtos;
    }
}
