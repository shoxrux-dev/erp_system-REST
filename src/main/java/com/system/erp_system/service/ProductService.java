package com.system.erp_system.service;

import com.system.erp_system.constant.FilePathConstant;
import com.system.erp_system.domain.Product;
import com.system.erp_system.exception.FileDeletionException;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.ProductRepository;
import com.system.erp_system.util.FileUtil;
import com.system.erp_system.validation.CommonSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CommonSchemaValidator commonSchemaValidator;

    @Transactional
    public Product create(Product product, MultipartFile file) {
        commonSchemaValidator.productNotExist(product);
        commonSchemaValidator.categoryExist(product.getCategoryId());
        if(file != null && !file.isEmpty()){
            String fileName = FileUtil.uploadImage(file);
            product.setImage(fileName);
        }
        product.setId(UUID.randomUUID());
        Instant now = Instant.now();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("product not found with %s id", id)));
    }

    @Transactional
    public Product update(UUID id, Product product, MultipartFile file) {
        commonSchemaValidator.categoryExist(product.getCategoryId());
        Product product1 = get(id);

        if(!file.isEmpty()){
            deleteFileByProduct(product1);
            String fileName = FileUtil.uploadImage(file);
            product1.setImage(fileName);
        }

        product1.setName(product.getName());
        product1.setCategoryId(product.getCategoryId());
        product1.setUpdatedAt(Instant.now());
        return productRepository.save(product1);
    }

    @Transactional
    public void delete(UUID id) {
        Product product = get(id);
        deleteFileByProduct(product);
        productRepository.delete(product);
    }

    @Transactional
    public void deleteFileByProduct(Product product) {
        String imagePath = FilePathConstant.FILE_UPLOAD_PATH + product.getImage();
        Path filePath = Paths.get(imagePath);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            throw new FileDeletionException("Could not delete");
        }
    }

}