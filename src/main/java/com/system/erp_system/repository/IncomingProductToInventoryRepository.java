package com.system.erp_system.repository;

import com.system.erp_system.domain.IncomingProductToInventory;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IncomingProductToInventoryRepository extends CassandraRepository<IncomingProductToInventory, UUID> {
    @Query(allowFiltering = true)
    Optional<IncomingProductToInventory> findIncomingProductToInventoryByUpdatedAtBetween(Instant start, Instant end);

}
