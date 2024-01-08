package com.system.erp_system.convertor;

import com.system.erp_system.domain.Inventory;
import com.system.erp_system.dto.inventory.BaseInventoryRequestDto;
import com.system.erp_system.dto.inventory.InventoryResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class InventoryConverter {
    public <T extends BaseInventoryRequestDto> Inventory convertToDomain(T request) {
        Inventory inventory = new Inventory();
        inventory.setName(request.getName());
        return inventory;
    }

    public InventoryResponseDto from(Inventory inventory){
        return InventoryResponseDto.builder()
                .id(inventory.getId())
                .name(inventory.getName())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }

    public List<InventoryResponseDto> from(List<Inventory> inventories) {
        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();

        inventories.forEach((inventory -> inventoryResponseDtos.add(
                InventoryResponseDto.builder()
                        .id(inventory.getId())
                        .name(inventory.getName())
                        .createdAt(inventory.getCreatedAt())
                        .updatedAt(inventory.getUpdatedAt())
                        .build()
        )));

        return inventoryResponseDtos;
    }

}
