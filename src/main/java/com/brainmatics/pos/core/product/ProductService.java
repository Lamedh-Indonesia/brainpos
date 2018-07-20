package com.brainmatics.pos.core.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepoNonSpring repository;

    public ProductService(ProductRepoNonSpring repository) {
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
