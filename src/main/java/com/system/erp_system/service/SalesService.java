package com.system.erp_system.service;

import com.system.erp_system.domain.IncomingProduct;
import com.system.erp_system.domain.OutgoingProduct;
import com.system.erp_system.domain.Sales;
import com.system.erp_system.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;
    private final IncomingProductService incomingProductService;

    @Transactional
    public void create(OutgoingProduct outgoingProduct) {

        Sales sales = Sales.builder()
                .id(UUID.randomUUID())
                .count(outgoingProduct.getCount())
                .companyId(outgoingProduct.getCompanyId())
                .productId(outgoingProduct.getProductId())
                .revenue(
                        getRevenueOfNewAndLastProductPrice(outgoingProduct)
                ).build();

        Instant now = Instant.now();
        sales.setCreatedAt(now);
        sales.setUpdatedAt(now);
        salesRepository.save(sales);
    }

    public List<Sales> getAll() {
        List<Sales> salesList = salesRepository.findAll();
        Collections.sort(salesList);
        return salesList;
    }

    @Transactional
    public void update(OutgoingProduct oldOutgoingProduct, OutgoingProduct newOutgoingProduct) {
        Instant start = oldOutgoingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = oldOutgoingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        Sales byUpdatedAtBetween = salesRepository.findByUpdatedAtBetween(start, end).orElse(null);

        if(byUpdatedAtBetween == null) {
            create(newOutgoingProduct);
            return;
        }

        byUpdatedAtBetween.setCount(newOutgoingProduct.getCount());
        byUpdatedAtBetween.setProductId(newOutgoingProduct.getProductId());
        byUpdatedAtBetween.setCompanyId(newOutgoingProduct.getCompanyId());
        byUpdatedAtBetween.setRevenue(
                getRevenueOfNewAndLastProductPrice(newOutgoingProduct)
        );
        byUpdatedAtBetween.setUpdatedAt(Instant.now());
        salesRepository.save(byUpdatedAtBetween);
    }

    public BigDecimal getRevenueOfNewAndLastProductPrice(OutgoingProduct outgoingProduct) {
        IncomingProduct lastIncomingProduct = incomingProductService.getLastByProductId(outgoingProduct.getProductId());

        BigDecimal priceOfOneIncomingProduct = lastIncomingProduct.getPrice().divide(BigDecimal.valueOf(lastIncomingProduct.getCount()), RoundingMode.HALF_DOWN);
        BigDecimal priceOfOneOutgoingProduct = outgoingProduct.getPrice().divide(BigDecimal.valueOf(outgoingProduct.getCount()), RoundingMode.HALF_DOWN);

        return priceOfOneOutgoingProduct.subtract(priceOfOneIncomingProduct).multiply(BigDecimal.valueOf(outgoingProduct.getCount()));
    }

    @Transactional
    public void delete(OutgoingProduct outgoingProduct) {
        Instant start = outgoingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = outgoingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        salesRepository.findByUpdatedAtBetween(start, end).ifPresent(salesRepository::delete);

    }

}
