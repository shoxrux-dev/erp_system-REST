package com.system.erp_system.repository;

import com.system.erp_system.domain.IncomingProduct;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IncomingProductRepository extends CassandraRepository<IncomingProduct, UUID> {
    @Query(allowFiltering = true)
    Optional<List<IncomingProduct>> findIncomingProductsByProductId(UUID productID);
}
