package com.example.ecommerce.sorter;

import com.example.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
    private Product product;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Product product) {
        this.product = product;
    }
}
