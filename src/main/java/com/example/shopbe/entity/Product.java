package com.example.shopbe.entity;

import com.example.shopbe.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends AbstractAuditingEntity {

  @Nationalized
  private String name;

  @Column(length = 10000)
  @Nationalized
  private String description;

  private Long price;

  private Integer discount;

  private boolean isNew;

  private Long weight;

  @Nationalized
  private String dimension;

  @Nationalized
  private String material;

  @Nationalized
  private String otherInfo;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
  private List<ProductTag> tags;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
  private List<ProductSize> productSizes;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
  private List<ProductColor> productColors;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
  private List<Image> images;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
  private List<Comment> comments;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
  @JsonIgnore
  private List<OrderProduct> orderProducts;

  public Product(String name, String description, Long price, Integer discount, boolean isNew, Long weight,
                 String dimension, String material, String otherInfo) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.discount = discount;
    this.isNew = isNew;
    this.weight = weight;
    this.dimension = dimension;
    this.material = material;
    this.otherInfo = otherInfo;
  }

}
