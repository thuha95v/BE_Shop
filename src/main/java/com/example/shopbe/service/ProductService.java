package com.example.shopbe.service;

import com.example.shopbe.dto.ProductDTO;
import com.example.shopbe.entity.Product;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface ProductService {

  List<Product> getAllProduct();

  Product getProductById(Long id);

  Product createProduct(ProductDTO productDTO);

  Product editProductById(ProductDTO productDTO);

  TrueFalseResponse deleteProductById(Long id);

}
