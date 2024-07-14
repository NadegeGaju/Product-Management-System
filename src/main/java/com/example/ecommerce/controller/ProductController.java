package com.example.ecommerce.controller;

import com.example.ecommerce.Repository.CategoriesRepository;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.entities.Categories;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.payLoads.ProductForm;
import com.example.ecommerce.service.ProductTreeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductTreeService productTreeService;
    private final CategoriesRepository categoriesRepository;
    private final ProductRepository productRepository;



    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("categories", categoriesRepository.findAll());
        return "add-product"; // Assuming "add-product.html" is your form view template
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("productForm") @Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoriesRepository.findAll());
            return "add-product"; // Return to form with validation errors
        }

        // Convert ProductForm to Product entity and save to database
        Product product = convertToProduct(productForm);
        productTreeService.addProduct(product);
        productRepository.save(product);

        return "redirect:/products/add"; // Redirect to clear form after successful submission
    }

    @GetMapping("/findall")
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product-list"; // Assuming "product-list.html" is your Thymeleaf template
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("productForm", product.get());
            return "update-product"; // Assuming "update-product.html" is your update form template
        } else {
            // Handle product not found
            return "redirect:/products/findall";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute("productForm") Product productForm,
                                Model model) {
        productForm.setId(id); // Ensure the ID is set from path variable
        productRepository.save(productForm);
        return "redirect:/products/findall"; // Redirect back to product list after update
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products/findall"; // Redirect back to product list after deletion
    }

    // Helper method to convert ProductForm to Product entity
    private Product convertToProduct(ProductForm productForm) {
        Categories category = categoriesRepository.findById(productForm.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return Product.builder()
                .name(productForm.getName())
                .price(productForm.getPrice())
                .quantity(productForm.getQuantity())
                .category(category)
                .build();
    }

}
