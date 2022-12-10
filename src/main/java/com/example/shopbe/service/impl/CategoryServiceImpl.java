package com.example.shopbe.service.impl;

import com.example.shopbe.dto.CategoryDTO;
import com.example.shopbe.entity.Category;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.CategoryRepository;
import com.example.shopbe.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category getCategory(Long id) {
      return categoryRepository.findCategoryById(id);
  }

  @Override
  public Category createColor(CategoryDTO categoryDTO) {
    Category category = new Category();
    category.setName(categoryDTO.getName());
    return categoryRepository.save(category);
  }

  @Override
  public Category changeCategory(Long id, CategoryDTO categoryDTO) {
    Category category = categoryRepository.findCategoryById(id);
    category.setName(categoryDTO.getName());
    return categoryRepository.save(category);
  }

  @Override
  public TrueFalseResponse deleteCategory(Long id) {
    Category category = categoryRepository.findCategoryById(id);
    categoryRepository.delete(category);
    return new TrueFalseResponse(true);
  }

  @Override
  public List<Category> getAllCategory() {
    return categoryRepository.findAll();
  }
}
