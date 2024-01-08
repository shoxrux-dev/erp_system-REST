package com.system.erp_system.repository;

import com.system.erp_system.domain.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends CassandraRepository<Account, UUID> {
    @Query(allowFiltering = true)
    Optional<Account> findAccountByUpdatedAtBetween(Instant start, Instant end);
}
