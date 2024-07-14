package com.example.ecommerce.controller;

import com.example.ecommerce.Repository.CategoriesRepository;
import com.example.ecommerce.entities.Categories;
import com.example.ecommerce.payLoads.CategoryForm;
import com.example.ecommerce.payLoads.ProductForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoriesRepository categoriesRepository;
    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("categoryForm", new ProductForm());
        return "category"; // Assuming "add-product.html" is your form view template
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("CategoryForm") @Valid CategoryForm categoryForm,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            // If validation errors, return to the form with error messages
            return "category";
        }

        // Convert categoryForm to category entity and save to database (sample logic)
        Categories category = convertToCategory(categoryForm);
        categoriesRepository.save(category); // Assuming this saves the product

        return "redirect:/categories/add"; // Redirect to clear form after successful submission
    }

    // Helper method to convert ProductForm to Product entity
    private Categories convertToCategory(CategoryForm categoryForm) {
        return Categories.builder()
                .name(categoryForm.getName())
                .build();
    }

}
