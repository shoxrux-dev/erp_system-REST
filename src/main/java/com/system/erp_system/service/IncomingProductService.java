package com.system.erp_system.service;

import com.system.erp_system.domain.*;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.*;
import com.system.erp_system.validation.CommonSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class IncomingProductService {
    private final IncomingProductRepository incomingProductRepository;
    private final ProductInInventoryService productInInventoryService;
    private final AccountService accountService;
    private final IncomingAndOutgoingToAccountService incomingAndOutgoingToAccountService;
    private final IncomingProductToInventoryService incomingProductToInventoryService;
    private final CommonSchemaValidator commonSchemaValidator;

    @Transactional
    public IncomingProduct create(IncomingProduct incomingProduct) {
        commonSchemaValidator.validateIncomingProduct(incomingProduct);

        incomingProduct.setId(UUID.randomUUID());
        Instant now = Instant.now();
        incomingProduct.setCreatedAt(now);
        incomingProduct.setUpdatedAt(now);

        accountService.subtraction(incomingProduct);
        incomingProductToInventoryService.create(incomingProduct);
        productInInventoryService.add(incomingProduct);
        incomingAndOutgoingToAccountService.create(incomingProduct);

        return incomingProductRepository.save(incomingProduct);
    }

    public List<IncomingProduct> getAll() {
        return incomingProductRepository.findAll();
    }

    public IncomingProduct get(UUID id) {
        return incomingProductRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("incoming product not found with %s id", id)));
    }

    @Transactional
    public IncomingProduct update(UUID id, IncomingProduct newIncomingProduct) {
        commonSchemaValidator.validateIncomingProduct(newIncomingProduct);

        IncomingProduct oldIncomingProduct = get(id);

        productInInventoryService.update(oldIncomingProduct, newIncomingProduct);
        incomingProductToInventoryService.update(oldIncomingProduct, newIncomingProduct);
        accountService.update(oldIncomingProduct, newIncomingProduct);
        incomingAndOutgoingToAccountService.update(oldIncomingProduct, newIncomingProduct);

        oldIncomingProduct.setProductId(newIncomingProduct.getProductId());
        oldIncomingProduct.setCompanyId(newIncomingProduct.getCompanyId());
        oldIncomingProduct.setInventoryId(newIncomingProduct.getInventoryId());
        oldIncomingProduct.setCount(newIncomingProduct.getCount());
        oldIncomingProduct.setPrice(newIncomingProduct.getPrice());
        oldIncomingProduct.setUpdatedAt(Instant.now());

        return incomingProductRepository.save(oldIncomingProduct);
    }

    public IncomingProduct getLastByProductId(UUID productId) {
        Optional<List<IncomingProduct>> incomingProductsByProductId = incomingProductRepository.findIncomingProductsByProductId(productId);
        if(incomingProductsByProductId.isEmpty()) {
            throw new RecordNotFoundException("no incoming product list found");
        }
        incomingProductsByProductId.ifPresent(Collections::sort);
        return incomingProductsByProductId.get().get(0);
    }

}
