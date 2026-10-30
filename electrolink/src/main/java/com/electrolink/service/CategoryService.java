package com.electrolink.service;

import com.electrolink.model.Category;
import com.electrolink.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found with id:" + id));
    }
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    public Category updateCategory(Long id, Category category){
        Category existeCategory = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found with id:" + id));
        existeCategory.setName(category.getName());
        existeCategory.setDescription(category.getDescription());

        return categoryRepository.save(existeCategory);
    }
    public void deleteCategory(Long id){
        if (!categoryRepository.existsById(id)){
            throw new RuntimeException("Category not found with id:"+ id);
        }
        categoryRepository.deleteById(id);
    }

    public List<Category> searchCategories(String name){
        if (name != null && !name.isBlank()){
            return categoryRepository.findByNameContainingIgnoreCase(name.trim());
        }
        return categoryRepository.findAll();
    }
}
