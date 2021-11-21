package com.buyvalenko.productService.controllers;

import com.buyvalenko.productService.entities.Product;
import com.buyvalenko.productService.repositories.specifications.ProductSpecs;
import com.buyvalenko.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showProducts(Model model,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "title", required = false) String title,
                               @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                               @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice) {
        if (page == null) {
            page = 1;
        }

        Specification<Product> specification = Specification.where(null);

        if (title != null) {
            specification = specification.and(ProductSpecs.titleContains(title));
        }

        if (minPrice != null) {
            specification = specification.and(ProductSpecs.priceGreaterThanOrEq(minPrice));
        }

        if (maxPrice != null) {
            specification = specification.and(ProductSpecs.priceLesserThanOrEq(maxPrice));
        }

        model.addAttribute("products", productService.getProductWithPagingAndFiltering(specification,
                PageRequest.of(page - 1, 5)).getContent());
        model.addAttribute("title", title);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-edit";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getProductByID(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getProductByID(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProductByID(id);
        return "redirect:/products";
    }

    @PostMapping("/edit")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }
}
