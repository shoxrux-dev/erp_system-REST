package com.system.erp_system.repository;

import com.system.erp_system.domain.Inventory;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends CassandraRepository<Inventory, UUID> {
    @Query(allowFiltering = true)
    Optional<Inventory> findInventoryByName(String name);
}

