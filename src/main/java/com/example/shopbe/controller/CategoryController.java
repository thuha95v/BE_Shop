package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.CategoryDTO;
import com.example.shopbe.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/categories")
  public ResponseEntity<?> getAllCategory() {
    return VsResponseUtil.ok(categoryService.getAllCategory());
  }

  @GetMapping("/categories/{id}")
  public ResponseEntity<?> getCategory(@PathVariable Long id) {
    return VsResponseUtil.ok(categoryService.getCategory(id));
  }

  @PostMapping("/categories")
  public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
    return VsResponseUtil.ok(categoryService.createColor(categoryDTO));
  }

  @PatchMapping("/categories/{id}")
  public ResponseEntity<?> changeCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
    return VsResponseUtil.ok(categoryService.changeCategory(id, categoryDTO));
  }

  @DeleteMapping("/categories/{id}")
  public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
    return VsResponseUtil.ok(categoryService.deleteCategory(id));
  }
}
