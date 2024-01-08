package com.system.erp_system.repository;

import com.system.erp_system.domain.Company;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends CassandraRepository<Company, UUID> {
    @Query(allowFiltering = true)
    Optional<Company> findCompanyByName(String name);
}
