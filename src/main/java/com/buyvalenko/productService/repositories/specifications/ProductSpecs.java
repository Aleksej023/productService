package com.buyvalenko.productService.repositories.specifications;

import com.buyvalenko.productService.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecs {
    public static Specification<Product> titleContains(String title) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Product> priceGreaterThanOrEq(BigDecimal value) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Product> priceLesserThanOrEq(BigDecimal value) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
    }
}
