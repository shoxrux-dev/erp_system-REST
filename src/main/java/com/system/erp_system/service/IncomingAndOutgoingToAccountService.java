package com.system.erp_system.service;

import com.system.erp_system.constant.AccountStatusEnum;
import com.system.erp_system.domain.IncomingAndOutgoingToAccount;
import com.system.erp_system.domain.IncomingProduct;
import com.system.erp_system.domain.OutgoingProduct;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.IncomingAndOutGoingToAccountRepository;
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
public class IncomingAndOutgoingToAccountService {
    private final IncomingAndOutGoingToAccountRepository incomingAndOutGoingToAccountRepository;

    @Transactional
    public void create(IncomingProduct incomingProduct) {
        IncomingAndOutgoingToAccount incomingAndOutgoingToAccount = IncomingAndOutgoingToAccount.builder()
                .id(UUID.randomUUID())
                .companyId(incomingProduct.getCompanyId())
                .price(incomingProduct.getPrice())
                .status(AccountStatusEnum.OUTED).build();
        Instant now = Instant.now();
        incomingAndOutgoingToAccount.setCreatedAt(now);
        incomingAndOutgoingToAccount.setUpdatedAt(now);
        incomingAndOutGoingToAccountRepository.save(incomingAndOutgoingToAccount);
    }

    @Transactional
    public void create(OutgoingProduct outgoingProduct) {
        IncomingAndOutgoingToAccount incomingAndOutgoingToAccount = IncomingAndOutgoingToAccount.builder()
                .id(UUID.randomUUID())
                .companyId(outgoingProduct.getCompanyId())
                .price(outgoingProduct.getPrice())
                .status(AccountStatusEnum.ENTERED).build();
        Instant now = Instant.now();
        incomingAndOutgoingToAccount.setCreatedAt(now);
        incomingAndOutgoingToAccount.setUpdatedAt(now);
        incomingAndOutGoingToAccountRepository.save(incomingAndOutgoingToAccount);
    }


    @Transactional
    public void update(IncomingProduct oldIncomingProduct, IncomingProduct newIncomingProduct) {
        Instant start = oldIncomingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = oldIncomingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        IncomingAndOutgoingToAccount byUpdatedAtBetween = getByUpdatedAtBetween(start, end);

        byUpdatedAtBetween.setCompanyId(newIncomingProduct.getCompanyId());
        byUpdatedAtBetween.setPrice(newIncomingProduct.getPrice());
        byUpdatedAtBetween.setStatus(AccountStatusEnum.OUTED);
        byUpdatedAtBetween.setUpdatedAt(Instant.now());
        incomingAndOutGoingToAccountRepository.save(byUpdatedAtBetween);
    }

    @Transactional
    public void update(OutgoingProduct oldOutgoingProduct, OutgoingProduct newOutgoingProduct) {
        Instant start = oldOutgoingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = oldOutgoingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        IncomingAndOutgoingToAccount byUpdatedAtBetween = incomingAndOutGoingToAccountRepository.findIncomingAndOutgoingToAccountByUpdatedAtBetween(start, end).orElse(null);

        if(byUpdatedAtBetween == null) {
            create(newOutgoingProduct);
            return;
        }

        byUpdatedAtBetween.setCompanyId(newOutgoingProduct.getCompanyId());
        byUpdatedAtBetween.setPrice(newOutgoingProduct.getPrice());
        byUpdatedAtBetween.setStatus(AccountStatusEnum.ENTERED);
        byUpdatedAtBetween.setUpdatedAt(Instant.now());
        incomingAndOutGoingToAccountRepository.save(byUpdatedAtBetween);
    }

    public List<IncomingAndOutgoingToAccount> getAll() {
        List<IncomingAndOutgoingToAccount> incomingAndOutgoingToAccounts = incomingAndOutGoingToAccountRepository.findAll();
        Collections.sort(incomingAndOutgoingToAccounts);
        return incomingAndOutgoingToAccounts;
    }

    public IncomingAndOutgoingToAccount getByUpdatedAtBetween(Instant start, Instant end) {
        return incomingAndOutGoingToAccountRepository.findIncomingAndOutgoingToAccountByUpdatedAtBetween(start, end)
                .orElseThrow(() -> new RecordNotFoundException(String.format("incoming and outgoing to account not found with %s start time and %s end time", start, end)));
    }

    @Transactional
    public void delete(OutgoingProduct outgoingProduct) {
        Instant start = outgoingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = outgoingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        IncomingAndOutgoingToAccount byUpdatedAtBetween = getByUpdatedAtBetween(start, end);
        incomingAndOutGoingToAccountRepository.delete(byUpdatedAtBetween);
    }

}
