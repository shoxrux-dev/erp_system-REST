package com.system.erp_system.convertor;

import com.system.erp_system.domain.*;
import com.system.erp_system.dto.outgoing_product.BaseOutgoingProductRequestDto;
import com.system.erp_system.dto.outgoing_product.OutgoingProductResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OutgoingProductConverter {
    public OutgoingProduct convertToDomain(BaseOutgoingProductRequestDto baseOutgoingProductRequestDto) {
        OutgoingProduct outgoingProduct = new OutgoingProduct();
        outgoingProduct.setProductId(baseOutgoingProductRequestDto.getProductId());
        outgoingProduct.setCompanyId(baseOutgoingProductRequestDto.getCompanyId());
        outgoingProduct.setCount(baseOutgoingProductRequestDto.getCount());
        outgoingProduct.setPrice(baseOutgoingProductRequestDto.getPrice());
        outgoingProduct.setInventoryId(baseOutgoingProductRequestDto.getInventoryId());
        outgoingProduct.setStatus(baseOutgoingProductRequestDto.getStatus());
        return outgoingProduct;
    }

    public OutgoingProductResponseDto from(
            OutgoingProduct outgoingProduct,
            Product product,
            Company company,
            Inventory inventory
    ) {
        return OutgoingProductResponseDto.builder()
                .id(outgoingProduct.getId())
                .productId(product.getId())
                .productName(product.getName())
                .price(outgoingProduct.getPrice())
                .count(outgoingProduct.getCount())
                .inventoryId(inventory.getId())
                .inventoryName(inventory.getName())
                .companyId(company.getId())
                .companyName(company.getName())
                .createdAt(outgoingProduct.getCreatedAt())
                .updatedAt(outgoingProduct.getUpdatedAt())
                .build();
    }

    public List<OutgoingProductResponseDto> from(
            List<OutgoingProduct> outgoingProducts,
            List<Product> products,
            List<Company> companies,
            List<Inventory> inventories
    ) {
        List<OutgoingProductResponseDto> outgoingProductResponseDtos = new ArrayList<>();

        outgoingProducts.parallelStream().forEach(outgoingProduct ->
                products.parallelStream().forEach(product ->
                        companies.forEach(company ->
                                inventories.forEach(inventory ->
                                {
                                    if (
                                            outgoingProduct.getProductId().equals(product.getId()) &&
                                                    outgoingProduct.getCompanyId().equals(company.getId()) &&
                                                    outgoingProduct.getInventoryId().equals(inventory.getId())
                                    ) {
                                        outgoingProductResponseDtos.add(
                                                OutgoingProductResponseDto.builder()
                                                        .id(outgoingProduct.getId())
                                                        .productId(outgoingProduct.getProductId())
                                                        .productName(product.getName())
                                                        .companyId(outgoingProduct.getCompanyId())
                                                        .companyName(company.getName())
                                                        .count(outgoingProduct.getCount())
                                                        .price(outgoingProduct.getPrice())
                                                        .inventoryId(outgoingProduct.getInventoryId())
                                                        .inventoryName(inventory.getName())
                                                        .createdAt(outgoingProduct.getCreatedAt())
                                                        .updatedAt(outgoingProduct.getUpdatedAt())
                                                        .build()
                                        );
                                    }
                                }))));
        return outgoingProductResponseDtos;
    }

}
