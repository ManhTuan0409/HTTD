package com.example.HTTD.Service;

import com.example.HTTD.Entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);

    Category getCategoryById(Long categoryId);

    List<Category> getAllCategory();

    Category updateCategory(Category category);

    boolean deleteCategory(Long categoryId);
}
