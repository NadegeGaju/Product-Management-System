package com.example.ecommerce.service;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.sorter.BinarySearchTree;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTreeService {

    private final BinarySearchTree binarySearchTree=
            new BinarySearchTree();

    public void addProduct(Product product) {
        binarySearchTree.addProduct(product);
    }

    public Product getProduct(Long id) {
        return binarySearchTree.searchProduct(id);
    }

    public void deleteProduct(Long id) {
        binarySearchTree.deleteProduct(id);
    }
}
