package com.system.erp_system.convertor;

import com.system.erp_system.domain.Inventory;
import com.system.erp_system.domain.Product;
import com.system.erp_system.domain.ProductInInventory;
import com.system.erp_system.dto.product_in_inventory.ProductInInventoryResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ProductInInventoryConverter {
    public List<ProductInInventoryResponseDto> from(
            List<ProductInInventory> productInInventories,
            List<Inventory> inventories,
            List<Product> products
    ) {
        List<ProductInInventoryResponseDto> productInInventoryResponseDtos = new ArrayList<>();
        productInInventories.forEach(productInInventory ->
                inventories.forEach(inventory ->
                        products.forEach(product ->
                        {
                            if (
                                    productInInventory.getProductId().equals(product.getId()) &&
                                            productInInventory.getInventoryId().equals(inventory.getId())
                            ) {
                                productInInventoryResponseDtos.add(
                                        ProductInInventoryResponseDto.builder()
                                                .id(productInInventory.getId())
                                                .inventoryId(productInInventory.getInventoryId())
                                                .inventoryName(inventory.getName())
                                                .productId(productInInventory.getProductId())
                                                .productName(product.getName())
                                                .count(productInInventory.getCount())
                                                .createdAt(productInInventory.getCreatedAt())
                                                .updatedAt(productInInventory.getUpdatedAt())
                                                .build()
                                );
                            }
                        })));
        return productInInventoryResponseDtos;
    }
}
