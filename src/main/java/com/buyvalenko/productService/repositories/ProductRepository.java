package com.buyvalenko.productService.repositories;

import com.buyvalenko.productService.entities.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
