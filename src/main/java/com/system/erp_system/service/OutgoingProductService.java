package com.system.erp_system.service;

import com.system.erp_system.constant.DebtStatusEnum;
import com.system.erp_system.domain.OutgoingProduct;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.OutgoingProductRepository;
import com.system.erp_system.validation.CommonSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutgoingProductService {
    private final OutgoingProductRepository outgoingProductRepository;
    private final ProductInInventoryService productInInventoryService;
    private final DebtService debtService;
    private final SalesService salesService;
    private final AccountService accountService;
    private final IncomingAndOutgoingToAccountService incomingAndOutgoingToAccountService;
    private final OutgoingProductFromInventoryService outgoingProductFromInventoryService;
    private final CommonSchemaValidator commonSchemaValidator;

    @Transactional
    public OutgoingProduct create(OutgoingProduct outgoingProduct) {
        commonSchemaValidator.validateOutgoingProduct(outgoingProduct);

        outgoingProduct.setId(UUID.randomUUID());
        Instant now = Instant.now();
        outgoingProduct.setCreatedAt(now);
        outgoingProduct.setUpdatedAt(now);

        outgoingProductFromInventoryService.create(outgoingProduct);
        productInInventoryService.separate(outgoingProduct);
        debtService.create(outgoingProduct);

        if(outgoingProduct.getStatus().equals(DebtStatusEnum.NOT_DEBT)) {
            salesService.create(outgoingProduct);
            accountService.add(outgoingProduct);
            incomingAndOutgoingToAccountService.create(outgoingProduct);
        }

        return outgoingProductRepository.save(outgoingProduct);
    }

    public List<OutgoingProduct> getAll() {
        List<OutgoingProduct> outgoingProductList = outgoingProductRepository.findAll();
        Collections.sort(outgoingProductList);
        return outgoingProductList;
    }

    @Transactional
    public OutgoingProduct update(UUID id, OutgoingProduct newOutgoingProduct) {
        commonSchemaValidator.validateOutgoingProduct(newOutgoingProduct);

        OutgoingProduct oldOutgoingProduct = getById(id);

        outgoingProductFromInventoryService.update(oldOutgoingProduct, newOutgoingProduct);
        productInInventoryService.update(oldOutgoingProduct, newOutgoingProduct);
        debtService.update(oldOutgoingProduct, newOutgoingProduct);

        if(newOutgoingProduct.getStatus().equals(DebtStatusEnum.NOT_DEBT)) {
            salesService.update(oldOutgoingProduct, newOutgoingProduct);
            accountService.update(oldOutgoingProduct,newOutgoingProduct);
            incomingAndOutgoingToAccountService.update(oldOutgoingProduct, newOutgoingProduct);
        }else{
            salesService.delete(oldOutgoingProduct);
            accountService.update(oldOutgoingProduct);
            incomingAndOutgoingToAccountService.delete(oldOutgoingProduct);
        }

        oldOutgoingProduct.setProductId(newOutgoingProduct.getProductId());
        oldOutgoingProduct.setInventoryId(newOutgoingProduct.getInventoryId());
        oldOutgoingProduct.setStatus(newOutgoingProduct.getStatus());
        oldOutgoingProduct.setCompanyId(newOutgoingProduct.getCompanyId());
        oldOutgoingProduct.setCount(newOutgoingProduct.getCount());
        oldOutgoingProduct.setPrice(newOutgoingProduct.getPrice());
        oldOutgoingProduct.setUpdatedAt(Instant.now());

        return outgoingProductRepository.save(oldOutgoingProduct);
    }

    public OutgoingProduct getById(UUID id) {
        return outgoingProductRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("outgoing product not found with %s id", id)));
    }

}
