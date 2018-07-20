package com.brainmatics.pos.infra.data.mongodb;

import com.brainmatics.pos.core.product.Product;
import com.brainmatics.pos.core.product.ProductRepoNonSpring;

import java.util.ArrayList;

public class ProductMongoRepo implements ProductRepoNonSpring {

    public int getCount() {
        System.out.println("from mongodb");
        return 1;
    }

    public Product getById(int id) {
        System.out.println("from mongodb");
        return new Product();
    }

    public ArrayList<Product> getAll() {
        return new ArrayList<>();
    }

    public void save(Product product) {
    }

    public void remove(int id) {
    }
}
