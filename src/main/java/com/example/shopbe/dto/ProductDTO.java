package com.example.shopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  private Long id;
  private String name;
  private String description;
  private Long price;
  private Integer discount;
  private boolean isNew;
  private Long weight;
  private String dimension;
  private String material;
  private String otherInfo;
  private Long categoryId;
  private List<Long> tags;
  private List<Long> sizes;
  private List<Long> colors;
  private List<MultipartFile> images;

}
