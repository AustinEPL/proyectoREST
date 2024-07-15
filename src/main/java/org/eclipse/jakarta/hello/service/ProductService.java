package org.eclipse.jakarta.hello.service;

import org.eclipse.jakarta.hello.model.Product;

import java.util.List;

public interface ProductService {


    Product save(Product product);
    Product update(Product product);
    List<Product> findAll();
    Product findById(int id);
    void delete(int id);

}
