package com.system.erp_system.service;

import com.system.erp_system.domain.Category;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.CategoryRepository;
import com.system.erp_system.validation.CommonSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CommonSchemaValidator commonSchemaValidator;

    @Transactional
    public Category create(Category category) {
        commonSchemaValidator.categoryNotExist(category);
        category.setId(UUID.randomUUID());
        Instant now = Instant.now();
        category.setCreatedAt(now);
        category.setUpdatedAt(now);
        return categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category get(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("category not found with %s id", id)));
    }

    @Transactional
    public Category update(UUID id, Category category) {
        Category category1 = get(id);
        category1.setName(category.getName());
        category1.setUpdatedAt(Instant.now());
        return categoryRepository.save(category1);
    }

    @Transactional
    public void delete(UUID id) {
        Category category = get(id);
        categoryRepository.delete(category);
    }

}
