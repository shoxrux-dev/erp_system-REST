package com.system.erp_system.convertor;

import com.system.erp_system.domain.Company;
import com.system.erp_system.domain.IncomingProduct;
import com.system.erp_system.domain.Inventory;
import com.system.erp_system.domain.Product;
import com.system.erp_system.dto.incoming_product.BaseIncomingProductRequestDto;
import com.system.erp_system.dto.incoming_product.IncomingProductResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class IncomingProductConverter {
    public IncomingProduct convertToDomain(BaseIncomingProductRequestDto baseIncomingProductRequestDto) {
        IncomingProduct incomingProduct = new IncomingProduct();
        incomingProduct.setProductId(baseIncomingProductRequestDto.getProductId());
        incomingProduct.setCompanyId(baseIncomingProductRequestDto.getCompanyId());
        incomingProduct.setCount(baseIncomingProductRequestDto.getCount());
        incomingProduct.setPrice(baseIncomingProductRequestDto.getPrice());
        incomingProduct.setInventoryId(baseIncomingProductRequestDto.getInventoryId());
        return incomingProduct;
    }

    public IncomingProductResponseDto from(
            IncomingProduct incomingProduct,
            Product product,
            Company company,
            Inventory inventory
    ){
        return IncomingProductResponseDto.builder()
                .id(incomingProduct.getId())
                .productId(product.getId())
                .productName(product.getName())
                .companyId(company.getId())
                .companyName(company.getName())
                .count(incomingProduct.getCount())
                .price(incomingProduct.getPrice())
                .inventoryId(inventory.getId())
                .inventoryName(inventory.getName())
                .createdAt(incomingProduct.getCreatedAt())
                .updatedAt(incomingProduct.getUpdatedAt())
                .build();
    }

    public List<IncomingProductResponseDto> from(
            List<IncomingProduct> incomingProducts,
            List<Product> products,
            List<Company> companies,
            List<Inventory> inventories
    ) {
        List<IncomingProductResponseDto> incomingProductResponseDtos = new ArrayList<>();

        incomingProducts.parallelStream().forEach(incomingProduct -> products.parallelStream().forEach(product -> companies.forEach(company -> inventories.forEach(inventory -> {
            if(
                    incomingProduct.getProductId().equals(product.getId()) &&
                    incomingProduct.getCompanyId().equals(company.getId()) &&
                    incomingProduct.getInventoryId().equals(inventory.getId())
            ) {
                incomingProductResponseDtos.add(
                        IncomingProductResponseDto.builder()
                                .id(incomingProduct.getId())
                                .productId(incomingProduct.getProductId())
                                .productName(product.getName())
                                .companyId(incomingProduct.getCompanyId())
                                .companyName(company.getName())
                                .count(incomingProduct.getCount())
                                .price(incomingProduct.getPrice())
                                .inventoryId(incomingProduct.getInventoryId())
                                .inventoryName(inventory.getName())
                                .createdAt(incomingProduct.getCreatedAt())
                                .updatedAt(incomingProduct.getUpdatedAt())
                                .build()
                );
            }
        }))));
        return incomingProductResponseDtos;
    }

}
