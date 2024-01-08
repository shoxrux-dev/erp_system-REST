package com.system.erp_system.repository;

import com.system.erp_system.domain.IncomingAndOutgoingToAccount;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.cassandra.repository.Query;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IncomingAndOutGoingToAccountRepository extends CassandraRepository<IncomingAndOutgoingToAccount, UUID> {
    @Query(allowFiltering = true)
    Optional<IncomingAndOutgoingToAccount> findIncomingAndOutgoingToAccountByUpdatedAtBetween(Instant start, Instant end);
}
