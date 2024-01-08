package com.system.erp_system.repository;

import com.system.erp_system.domain.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
