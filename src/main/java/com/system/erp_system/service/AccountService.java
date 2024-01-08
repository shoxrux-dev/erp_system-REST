package com.system.erp_system.service;

import com.system.erp_system.domain.Account;
import com.system.erp_system.domain.IncomingProduct;
import com.system.erp_system.domain.OutgoingProduct;
import com.system.erp_system.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Account create(Account account){
        account.setId(UUID.randomUUID());
        Instant now = Instant.now();
        account.setCreatedAt(now);
        account.setUpdatedAt(now);
        return accountRepository.save(account);
    }

    @Transactional
    public void subtraction(IncomingProduct incomingProduct) {
        Account account = get();
        account.setBalance(account.getBalance().subtract(incomingProduct.getPrice()));
        account.setUpdatedAt(Instant.now());
        accountRepository.save(account);
    }

    @Transactional
    public void update(IncomingProduct oldIncomingProduct, IncomingProduct newIncomingProduct) {
        Account account = get();
        account.setBalance(account.getBalance().add(oldIncomingProduct.getPrice().subtract(newIncomingProduct.getPrice())));
        account.setUpdatedAt(Instant.now());
        accountRepository.save(account);
    }

    @Transactional
    public void update(OutgoingProduct oldOutgoingProduct, OutgoingProduct newOutgoingProduct) {
        Instant start = oldOutgoingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = oldOutgoingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        Account account = accountRepository.findAccountByUpdatedAtBetween(start, end).orElse(null);

        if(account == null) {
            add(newOutgoingProduct);
            return;
        }

        account.setBalance(account.getBalance().subtract(oldOutgoingProduct.getPrice()).add(newOutgoingProduct.getPrice()));
        account.setUpdatedAt(Instant.now());
        accountRepository.save(account);
    }

    @Transactional
    public void update(OutgoingProduct outgoingProduct) {
        Instant start = outgoingProduct.getUpdatedAt().minus(1, ChronoUnit.SECONDS);
        Instant end = outgoingProduct.getUpdatedAt().plus(1, ChronoUnit.SECONDS);

        Account account = accountRepository.findAccountByUpdatedAtBetween(start, end).orElse(null);

        if(account != null) {
            account.setBalance(account.getBalance().subtract(outgoingProduct.getPrice()));
            account.setUpdatedAt(Instant.now());
            accountRepository.save(account);
        }

    }

    public Account get(){
        return accountRepository.findAll().stream().findFirst().orElse(null);
    }

    @Transactional
    public void add(OutgoingProduct outgoingProduct) {
        Account account = get();
        account.setBalance(account.getBalance().add(outgoingProduct.getPrice()));
        account.setUpdatedAt(Instant.now());
        accountRepository.save(account);
    }

}
