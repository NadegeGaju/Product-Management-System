package com.example.ecommerce.controller;

import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.payLoads.ProductForm;
import com.example.ecommerce.payLoads.UpdateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/update")
@RequiredArgsConstructor
public class UpdateController {
    private final ProductRepository productRepository;

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            UpdateForm updateForm = convertToUpdateForm(new UpdateForm());
            model.addAttribute("productForm", updateForm);
            return "update-product"; // Assuming "update-product.html" is your update form template
        } else {
            // Handle product not found
            return "redirect:/products/findall";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute("productForm") @Valid UpdateForm updateForm,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            // If validation errors, return to the form with error messages
            return "update-product";
        }

        // Convert ProductForm back to Product entity and save to database
        Product product = convertToUpdate(updateForm);
        product.setId(id); // Ensure the ID is set from path variable
        productRepository.save(product);

        return "redirect:/products/findall"; // Redirect back to product list after update
    }

    // Helper method to convert Product to ProductForm
    private UpdateForm convertToUpdateForm(UpdateForm updateForm) {
        return UpdateForm.builder()
                .name(updateForm.getName())
                .price(updateForm.getPrice())
                .quantity(updateForm.getQuantity())
                .build();
    }

    // Helper method to convert ProductForm to Product
    private Product convertToUpdate(UpdateForm updateForm) {
        Product product = new Product();
        product.setName(updateForm.getName());
        product.setPrice(updateForm.getPrice());
        product.setQuantity(updateForm.getQuantity());
        // You may need to fetch category from database based on productForm.getCategory()
        // and set it to product.setCategory(category);
        return product;
    }
}
