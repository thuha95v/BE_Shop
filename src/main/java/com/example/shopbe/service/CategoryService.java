package com.example.shopbe.service;

import com.example.shopbe.dto.CategoryDTO;
import com.example.shopbe.entity.Category;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface CategoryService {

  Category getCategory(Long id);

  Category createColor(CategoryDTO categoryDTO);

  Category changeCategory(Long id, CategoryDTO categoryDTO);

  TrueFalseResponse deleteCategory(Long id);

  List<Category> getAllCategory();

}
