package org.eclipse.jakarta.hello.service;

import jakarta.inject.Named;
import org.eclipse.jakarta.hello.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ProductServiceImp implements ProductService {


    private static List<Product> products = new ArrayList<>();

    public ProductServiceImp() {
        if(products.isEmpty()){
            initializeProducts();

        }
    }
    private void initializeProducts() {
        products.add(new Product(1, "Laptop"));
        products.add(new Product(2, "Mouse"));
        products.add(new Product(3, "Keyboard"));
        products.add(new Product(4, "Monitor"));
        products.add(new Product(5, "Printer"));
        products.add(new Product(6, "External Hard Drive"));
        products.add(new Product(7, "USB Flash Drive"));
        products.add(new Product(8, "Webcam"));
        products.add(new Product(9, "Router"));
        products.add(new Product(10, "Headphones"));
    }

    @Override
    public Product save(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        products = products.stream().map(e -> (e.getId()==product.getId()) ? product : e).collect(Collectors.toList());
        return product;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        return products.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(int id) {
        products.removeIf(e -> e.getId() == id);

    }
}
