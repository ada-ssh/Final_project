package com.dostavka.service;

import com.dostavka.domain.Product;
import com.dostavka.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    private Product product;

    private List<Product> products;

    @BeforeEach
    void setProduct() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
        product = new Product(1, "Margarita", 23.43, new ArrayList<>());
        products = new ArrayList<>();
        products.add(product);
        productRepository.save(product);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(products);
        Optional<List<Product>> optionalBooks = Optional.ofNullable(productService.getAllProducts());
        assertTrue(optionalBooks.isPresent());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void createProduct() {
        Product newProduct = new Product(1, "Margarita", 23.43, new ArrayList<>());
        productService.createProduct(newProduct);
        verify(productRepository, times(2)).save(ArgumentMatchers.any(Product.class));
    }
}