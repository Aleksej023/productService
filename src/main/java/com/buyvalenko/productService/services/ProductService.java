package com.buyvalenko.productService.services;

import com.buyvalenko.productService.entities.Product;
import com.buyvalenko.productService.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProductWithPagingAndFiltering(Specification<Product> specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable);
    }

    public Product getProductByID(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProductByID(Long id) {
        productRepository.deleteById(id);
    }

    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }
}
