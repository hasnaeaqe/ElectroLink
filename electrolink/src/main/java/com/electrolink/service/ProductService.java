package com.electrolink.service;


import com.electrolink.model.Product;
import com.electrolink.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("product not found with id:" + id));
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        Product existeProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("product not found with id:" + id));
        existeProduct.setName(product.getName());
        existeProduct.setBrand(product.getBrand());
        existeProduct.setDescription(product.getDescription());
        existeProduct.setPrice(product.getPrice());
        existeProduct.setStockQuantity(product.getStockQuantity());
        existeProduct.setSpecifications(product.getSpecifications());
        existeProduct.setImageUrl(product.getImageUrl());
        existeProduct.setCategory(product.getCategory());
        return productRepository.save(existeProduct);
    }

    public void deleteProduct(Long id){
        if (!productRepository.existsById(id)){
            throw new RuntimeException("product not found with id:"+id);
        }
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String name, Long categoryId){
        boolean hasName = name != null && !name.isBlank();
        if (hasName && categoryId != null){
            return productRepository
                    .findByNameContainingIgnoreCaseAndCategoryId(
                            name.trim(), categoryId);
        }

        if (hasName){
            return productRepository.findByNameContainingIgnoreCase(name.trim());
        }

        if (categoryId != null){
            return productRepository.findByCategoryId(categoryId);
        }

        return productRepository.findAll();
    }
}
