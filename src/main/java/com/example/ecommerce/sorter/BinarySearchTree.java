package com.example.ecommerce.sorter;

import com.example.ecommerce.entities.Product;

public class BinarySearchTree {
    private TreeNode root;

    // Add product to the binary tree
    public void addProduct(Product product) {
        root = addRecursive(root, product);
    }

    private TreeNode addRecursive(TreeNode current, Product product) {
        if (current == null) {
            return new TreeNode(product);
        }

        if (product.getId() < current.getProduct().getId()) {
            current.setLeft(addRecursive(current.getLeft(), product));
        } else if (product.getId() > current.getProduct().getId()) {
            current.setRight(addRecursive(current.getRight(), product));
        } else {
            // ID already exists
            return current;
        }

        return current;
    }

    // Search for a product in the binary tree
    public Product searchProduct(Long id) {
        return searchRecursive(root, id);
    }

    private Product searchRecursive(TreeNode current, Long id) {
        if (current == null) {
            return null;
        }

        if (id.equals(current.getProduct().getId())) {
            return current.getProduct();
        }

        return id < current.getProduct().getId()
                ? searchRecursive(current.getLeft(), id)
                : searchRecursive(current.getRight(), id);
    }

    // Delete product from the binary tree
    public void deleteProduct(Long id) {
        root = deleteRecursive(root, id);
    }

    private TreeNode deleteRecursive(TreeNode current, Long id) {
        if (current == null) {
            return null;
        }

        if (id.equals(current.getProduct().getId())) {
            // Node to delete found
            // Nodes with only one child or no child
            if (current.getLeft() == null) {
                return current.getRight();
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            Product smallestProduct = findSmallestProduct(current.getRight());
            current.setProduct(smallestProduct);
            current.setRight(deleteRecursive(current.getRight(), smallestProduct.getId()));
            return current;

        }

        if (id < current.getProduct().getId()) {
            current.setLeft(deleteRecursive(current.getLeft(), id));
            return current;
        }

        current.setRight(deleteRecursive(current.getRight(), id));
        return current;
    }

    private Product findSmallestProduct(TreeNode root) {
        return root.getLeft() == null ? root.getProduct() : findSmallestProduct(root.getLeft());
    }
}

