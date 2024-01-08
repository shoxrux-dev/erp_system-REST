package com.system.erp_system.repository;

import com.system.erp_system.domain.ProductInInventory;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductInInventoryRepository extends CassandraRepository<ProductInInventory, UUID> {
    @Query(allowFiltering = true)
    Optional<ProductInInventory> findProductInInventoryByProductIdAndInventoryId(UUID productId, UUID inventoryId);
}
