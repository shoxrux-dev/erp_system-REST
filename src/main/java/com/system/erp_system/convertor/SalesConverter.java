package com.system.erp_system.convertor;

import com.system.erp_system.domain.Company;
import com.system.erp_system.domain.Product;
import com.system.erp_system.domain.Sales;
import com.system.erp_system.dto.sales.SalesResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SalesConverter {
    public List<SalesResponseDto> from(
            List<Sales> salesList,
            List<Product> productList,
            List<Company> companyList
    ) {
        List<SalesResponseDto> salesResponseDtos = new ArrayList<>();

        salesList.parallelStream().forEach(sales ->
                productList.forEach(product ->
                        companyList.forEach(company ->
                        {
                            if (sales.getCompanyId().equals(company.getId()) && sales.getProductId().equals(product.getId())) {
                                salesResponseDtos.add(
                                        SalesResponseDto.builder()
                                                .id(sales.getId())
                                                .revenue(sales.getRevenue())
                                                .companyId(company.getId())
                                                .companyName(company.getName())
                                                .productId(product.getId())
                                                .productName(product.getName())
                                                .count(sales.getCount())
                                                .createdAt(sales.getCreatedAt())
                                                .updatedAt(sales.getUpdatedAt())
                                                .build()
                                );
                            }
                        })));

        return salesResponseDtos;
    }
}
