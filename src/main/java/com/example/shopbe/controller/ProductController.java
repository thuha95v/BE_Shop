package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.ProductDTO;
import com.example.shopbe.dto.ProductItemDto;
import com.example.shopbe.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestApiV1
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/product")
  public ResponseEntity<?> getAllProduct() {
    return VsResponseUtil.ok(productService.getAllProduct());
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
    return VsResponseUtil.ok(productService.getProductById(id));
  }

  @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createProduct(HttpServletResponse response, @RequestPart("data") String data,
                                         @RequestPart(value = "file1", required = false) MultipartFile file1,
                                         @RequestPart(value = "file2", required = false) MultipartFile file2,
                                         @RequestPart(value = "file3", required = false) MultipartFile file3,
                                         @RequestPart(value = "file4", required = false) MultipartFile file4,
                                         @RequestPart(value = "file5", required = false) MultipartFile file5
  ) throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    ProductItemDto input = om.readValue(data, ProductItemDto.class);
    List<MultipartFile> files = new ArrayList<>();
    if (file1 != null) {
      files.add(file1);
    }
    if (file2 != null) {
      files.add(file2);
    }
    if (file3 != null) {
      files.add(file3);
    }
    if (file4 != null) {
      files.add(file4);
    }
    if (file5 != null) {
      files.add(file5);
    }
    ProductDTO dto = new ProductDTO(null, input.getName(), input.getDescription(), input.getPrice(),
        input.getDiscount(), input.isNew(), input.getWeight(), input.getDimension(), input.getMaterial(),
        input.getOtherInfo(), input.getCategoryId(), input.getTags(), input.getSizes(), input.getColors(),
        files);
    response.setStatus(200);
    response.addHeader("Content-Type", "application/json");
    return VsResponseUtil.ok(productService.createProduct(dto));
  }

  @PatchMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> editProduct(HttpServletResponse response, @RequestPart("data") String data,
                                       @RequestPart(value = "file1", required = false) MultipartFile file1,
                                       @RequestPart(value = "file2", required = false) MultipartFile file2,
                                       @RequestPart(value = "file3", required = false) MultipartFile file3,
                                       @RequestPart(value = "file4", required = false) MultipartFile file4,
                                       @RequestPart(value = "file5", required = false) MultipartFile file5) throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    ProductItemDto input = om.readValue(data, ProductItemDto.class);
    List<MultipartFile> files = new ArrayList<>();
    if (file1 != null) {
      files.add(file1);
    }
    if (file2 != null) {
      files.add(file2);
    }
    if (file3 != null) {
      files.add(file3);
    }
    if (file4 != null) {
      files.add(file4);
    }
    if (file5 != null) {
      files.add(file5);
    }
    ProductDTO dto = new ProductDTO(input.getId(), input.getName(), input.getDescription(), input.getPrice(),
        input.getDiscount(), input.isNew(), input.getWeight(), input.getDimension(), input.getMaterial(),
        input.getOtherInfo(), input.getCategoryId(), input.getTags(), input.getSizes(), input.getColors(),
        files);
    response.setStatus(200);
    response.addHeader("Content-Type", "application/json");

    return VsResponseUtil.ok(productService.editProductById(dto));
  }

  @DeleteMapping("/product/{id}")
  public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
    return VsResponseUtil.ok(productService.deleteProductById(id));
  }

}
