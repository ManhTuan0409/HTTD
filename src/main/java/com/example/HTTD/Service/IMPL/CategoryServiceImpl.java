package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.ExCategory;
import com.example.HTTD.Entity.Expense;
import com.example.HTTD.Repository.CategoryRepository;
import com.example.HTTD.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    @Override
    public ExCategory createCategory(ExCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public ExCategory getCategoryById(Long categoryId) {
        Optional<ExCategory> optional = categoryRepository.findById(categoryId);
        return optional.orElse(null);
    }

    @Override
    public List<ExCategory> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public ExCategory updateCategory(ExCategory category) {
        ExCategory existingcategory = categoryRepository.findById(category.getCategoryId()).get();
        existingcategory.setCategoryName(category.getCategoryName());
        ExCategory updatedcategory = categoryRepository.save(existingcategory);
        return updatedcategory;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
