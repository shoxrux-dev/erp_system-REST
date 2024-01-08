package com.system.erp_system.repository;

import com.system.erp_system.domain.OutgoingProduct;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutgoingProductRepository extends CassandraRepository<OutgoingProduct, UUID> {
}
