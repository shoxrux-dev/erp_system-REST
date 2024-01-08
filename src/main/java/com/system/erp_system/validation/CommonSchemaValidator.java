package com.system.erp_system.validation;

import com.system.erp_system.domain.*;
import com.system.erp_system.exception.AlreadyExistsException;
import com.system.erp_system.exception.AuthenticationException;
import com.system.erp_system.exception.OutOfStockException;
import com.system.erp_system.exception.RecordNotFoundException;
import com.system.erp_system.repository.*;
import com.system.erp_system.service.ProductInInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class CommonSchemaValidator {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final ProductInInventoryService productInInventoryService;

    public void validatePassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("username or password is incorrect");
        }
    }

    public void userNotExist(String username) {
        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        if(userByUsername.isPresent()){
            throw new AlreadyExistsException(String.format("user already exists with username %s", username));
        }
    }

    public void categoryNotExist(Category category) {
        Optional<Category> categoryByName = categoryRepository.findCategoryByName(category.getName());
        if(categoryByName.isPresent()){
            throw new AlreadyExistsException(String.format("category already exists with name %s", category.getName()));
        }
    }

    public void categoryExist(UUID categoryId) {
        Optional<Category> categoryByName = categoryRepository.findById(categoryId);
        if(categoryByName.isEmpty()){
            throw new RecordNotFoundException(String.format("category not found with id %s", categoryId));
        }
    }

    public void companyNotExist(Company company) {
        Optional<Company> companyByName = companyRepository.findCompanyByName(company.getName());
        if(companyByName.isPresent()){
            throw new AlreadyExistsException(String.format("company already exists with name %s", company.getName()));
        }
    }

    public void inventoryNotExist(Inventory inventory) {
        Optional<Inventory> inventoryByName = inventoryRepository.findInventoryByName(inventory.getName());
        if(inventoryByName.isPresent()){
            throw new AlreadyExistsException(String.format("inventory already exists with name %s", inventory.getName()));
        }
    }

    public void productNotExist(Product product) {
        Optional<Product> productByName = productRepository.findProductByName(product.getName());
        if(productByName.isPresent()){
            throw new AlreadyExistsException(String.format("product already exists with name %s", product.getName()));
        }
    }

    public void productExist(UUID id) {
        Optional<Product> productByName = productRepository.findById(id);
        if(productByName.isEmpty()){
            throw new RecordNotFoundException(String.format("product not found with id %s", id));
        }
    }

    public void companyExist(UUID id) {
        Optional<Company> companyById = companyRepository.findById(id);
        if(companyById.isEmpty()){
            throw new RecordNotFoundException(String.format("company not found with id %s", id));
        }
    }

    public void inventoryExist(UUID id) {
        Optional<Inventory> inventoryById = inventoryRepository.findById(id);
        if(inventoryById.isEmpty()){
            throw new RecordNotFoundException(String.format("inventory not found with id %s", id));
        }
    }

    public void validateIncomingProduct(IncomingProduct newIncomingProduct) {
        productExist(newIncomingProduct.getProductId());
        companyExist(newIncomingProduct.getCompanyId());
        inventoryExist(newIncomingProduct.getInventoryId());
    }

    public void validateOutgoingProduct(OutgoingProduct outgoingProduct) {
        productExist(outgoingProduct.getProductId());
        companyExist(outgoingProduct.getCompanyId());
        inventoryExist(outgoingProduct.getInventoryId());
        validateCountOfProduct(outgoingProduct);
    }

    public void validateCountOfProduct(OutgoingProduct outgoingProduct) {
        ProductInInventory byProductIdAndInventoryId = productInInventoryService.getByProductIdAndInventoryId(outgoingProduct.getProductId(), outgoingProduct.getInventoryId());
        if(byProductIdAndInventoryId.getCount() < outgoingProduct.getCount()) {
            throw new OutOfStockException("Not enough products in the inventory.");
        }
    }

}
