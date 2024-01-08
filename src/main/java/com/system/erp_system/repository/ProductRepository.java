package com.system.erp_system.repository;

import com.system.erp_system.domain.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends CassandraRepository<Product, UUID> {
    @Query(allowFiltering = true)
    Optional<Product> findProductByName(String name);
}
