package com.example.HTTD.Service;

import com.example.HTTD.Entity.ExCategory;

import java.util.List;

public interface CategoryService {
    ExCategory createCategory(ExCategory category);

    ExCategory getCategoryById(Long categoryId);

    List<ExCategory> getAllCategory();

    ExCategory updateCategory(ExCategory category);

    void deleteCategory(Long categoryId);
}
