package com.system.erp_system.service;

import com.system.erp_system.domain.Debt;
import com.system.erp_system.domain.OutgoingProduct;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.DebtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DebtService {
    private final DebtRepository debtRepository;

    @Transactional
    public void create(OutgoingProduct outgoingProduct) {
        Debt debt = Debt.builder()
                .id(UUID.randomUUID())
                .companyId(outgoingProduct.getCompanyId())
                .status(outgoingProduct.getStatus())
                .amount(outgoingProduct.getPrice()).build();
        Instant now = Instant.now();
        debt.setCreatedAt(now);
        debt.setUpdatedAt(now);
        debtRepository.save(debt);
    }

    public List<Debt> getAll() {
        List<Debt> debtList = debtRepository.findAll();
        Collections.sort(debtList);
        return debtList;
    }

    @Transactional
    public void update(OutgoingProduct oldOutgoingProduct, OutgoingProduct newOutgoingProduct) {
        Instant start = oldOutgoingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = oldOutgoingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        Debt byUpdatedAtBetween = getByUpdatedAtBetween(start, end);

        byUpdatedAtBetween.setAmount(newOutgoingProduct.getPrice());
        byUpdatedAtBetween.setStatus(newOutgoingProduct.getStatus());
        byUpdatedAtBetween.setCompanyId(newOutgoingProduct.getCompanyId());
        byUpdatedAtBetween.setUpdatedAt(Instant.now());
        debtRepository.save(byUpdatedAtBetween);
    }

    public Debt getByUpdatedAtBetween(Instant start, Instant end) {
        return debtRepository.findDebtByUpdatedAtBetween(start, end)
                .orElseThrow(() -> new RecordNotFoundException(String.format("debt not found with %s start time and %s end time", start, end)));
    }

}
