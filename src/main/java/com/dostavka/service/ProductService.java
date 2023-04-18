package com.dostavka.service;

import com.dostavka.domain.Product;
import com.dostavka.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(int id){
        return productRepository.findById(id).get();
    }

    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    public Product getProductByTitle(String title){
        return productRepository.findProductByTitle(title).get();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductById(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public void deleteProduct (Product product) {
        productRepository.delete(product);
    }
}
