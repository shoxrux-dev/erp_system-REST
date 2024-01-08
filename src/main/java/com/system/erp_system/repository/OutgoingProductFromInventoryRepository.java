package com.system.erp_system.repository;

import com.system.erp_system.domain.OutgoingProductFromInventory;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OutgoingProductFromInventoryRepository extends CassandraRepository<OutgoingProductFromInventory, UUID> {
    @Query(allowFiltering = true)
    Optional<OutgoingProductFromInventory> findOutgoingProductFromInventoryByUpdatedAtBetween(Instant start, Instant end);
}
