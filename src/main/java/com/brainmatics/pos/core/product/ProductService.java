package com.brainmatics.pos.core.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepo repository;

    public ProductService(ProductRepo repository) {
        this.repository = repository;
    }

    public String generateCode() {
        return "P" + (repository.getCount() + 1);
    }

    public Product newProduct() {
        Product result = new Product();
        result.setCode(generateCode());
        return result;
    }
}
