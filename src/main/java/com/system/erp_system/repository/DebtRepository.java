package com.system.erp_system.repository;

import com.system.erp_system.domain.Debt;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DebtRepository extends CassandraRepository<Debt, UUID> {
    @Query(allowFiltering = true)
    Optional<Debt> findDebtByUpdatedAtBetween(Instant start, Instant end);
}
