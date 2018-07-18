package com.brainmatics.pos.infra.data.mongodb;

import com.brainmatics.pos.core.Product;
import com.brainmatics.pos.core.ProductRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public class ProductMongoRepo implements ProductRepo {

    public int getCount() {
        System.out.println("from mongggodb");
        return 1;
    }

    public Product getById(int id) {
        System.out.println("from mongggodb");
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


