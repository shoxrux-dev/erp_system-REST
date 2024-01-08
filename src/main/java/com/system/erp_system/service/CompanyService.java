package com.system.erp_system.service;

import com.system.erp_system.domain.Company;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.CompanyRepository;
import com.system.erp_system.validation.CommonSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CommonSchemaValidator commonSchemaValidator;

    @Transactional
    public Company create(Company company) {
        commonSchemaValidator.companyNotExist(company);
        company.setId(UUID.randomUUID());
        Instant now = Instant.now();
        company.setCreatedAt(now);
        company.setUpdatedAt(now);
        return companyRepository.save(company);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company get(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("company not found with %s id", id)));
    }

    @Transactional
    public Company update(UUID id, Company company) {
        Company company1 = get(id);
        company1.setName(company.getName());
        company1.setUpdatedAt(Instant.now());
        return companyRepository.save(company1);
    }

    @Transactional
    public void delete(UUID id) {
        Company company = get(id);
        companyRepository.delete(company);
    }

}
