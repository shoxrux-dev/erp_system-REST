package com.system.erp_system.repository;

import com.system.erp_system.domain.Category;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends CassandraRepository<Category, UUID> {
    @Query(allowFiltering = true)
    Optional<Category> findCategoryByName(String name);
}
