package com.example.shopbe.service.impl;

import com.example.shopbe.config.exception.VsException;
import com.example.shopbe.dto.ProductDTO;
import com.example.shopbe.entity.*;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.*;
import com.example.shopbe.service.ProductService;
import com.example.shopbe.util.UploadFileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final TagRepository tagRepository;
  private final SizeRepository sizeRepository;
  private final ColorRepository colorRepository;
  private final ImageRepository imageRepository;
  private final ProductSizeRepository productSizeRepository;
  private final ProductTagRepository productTagRepository;
  private final ProductColorRepository productColorRepository;
  private final UploadFileUtil uploadFileUtil;

  public ProductServiceImp(ProductRepository productRepository, CategoryRepository categoryRepository,
                           TagRepository tagRepository, SizeRepository sizeRepository,
                           ColorRepository colorRepository, ImageRepository imageRepository,
                           ProductSizeRepository productSizeRepository, ProductTagRepository productTagRepository,
                           ProductColorRepository productColorRepository, UploadFileUtil uploadFileUtil) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    this.tagRepository = tagRepository;
    this.sizeRepository = sizeRepository;
    this.colorRepository = colorRepository;
    this.imageRepository = imageRepository;
    this.productSizeRepository = productSizeRepository;
    this.productTagRepository = productTagRepository;
    this.productColorRepository = productColorRepository;
    this.uploadFileUtil = uploadFileUtil;
  }

  @Override
  public List<Product> getAllProduct() {
    List<Product> list = productRepository.findAll();
    for (Product product : list) {
      product.getProductColors().removeIf(ProductColor::isDeleteFlag);
      product.getProductSizes().removeIf(ProductSize::isDeleteFlag);
      product.getTags().removeIf(ProductTag::isDeleteFlag);
    }
    return list;
  }

  @Override
  public Product getProductById(Long id) {
    return getById(id);
  }

  @Override
  public Product createProduct(ProductDTO productDTO) {
    Product objProduct = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(),
        productDTO.getDiscount(), productDTO.isNew(), productDTO.getWeight(), productDTO.getDimension(),
        productDTO.getMaterial(), productDTO.getOtherInfo());

    Category category = categoryRepository.findCategoryById(productDTO.getCategoryId());
    if (category == null) {
      throw new VsException("Không có category id: " + productDTO.getCategoryId());
    }
    objProduct.setCategory(category);

    Product product = productRepository.save(objProduct);

    for (Long tag : productDTO.getTags()) {
      Tag obj = tagRepository.findTagById(tag);
      if (obj == null) {
        throw new VsException("Không có tag id: " + tag);
      }
      productTagRepository.save(new ProductTag(product, obj));
    }

    for (Long id : productDTO.getTags()) {
      Size obj = sizeRepository.findSizeById(id);
      if (obj == null) {
        throw new VsException("Không có size id: " + id);
      }
      productSizeRepository.save(new ProductSize(product, obj));
    }

    for (Long color : productDTO.getColors()) {
      Color obj = colorRepository.findColorById(color);
      if (obj == null) {
        throw new VsException("Không có color id: " + color);
      }
      productColorRepository.save(new ProductColor(product, obj));
    }

    for (MultipartFile file : productDTO.getImages()) {
      if (file != null) {
        imageRepository.save(new Image(uploadFileUtil.getUrlFromFile(file), product));
      }
    }

    return productRepository.save(product);
  }

  @Override
  public Product editProductById(ProductDTO productDTO) {
    Product oldProduct = getById(productDTO.getId());

    Product objProduct = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(),
        productDTO.getDiscount(), productDTO.isNew(), productDTO.getWeight(), productDTO.getDimension(),
        productDTO.getMaterial(), productDTO.getOtherInfo());
    objProduct.setId(oldProduct.getId());

    Category category = categoryRepository.findCategoryById(productDTO.getCategoryId());
    if (category == null) {
      throw new VsException("Không có category id: " + productDTO.getCategoryId());
    }
    objProduct.setCategory(category);

    //
    List<ProductTag> productTags = oldProduct.getTags();
    for (int i = 0; i < productTags.size(); i++) {
      productTags.get(i).setDeleteFlag(true);
      productTagRepository.save(productTags.get(i));
    }
    List<ProductColor> productColors = oldProduct.getProductColors();
    for (int i = 0; i < productColors.size(); i++) {
      productColors.get(i).setDeleteFlag(true);
      productColorRepository.save(productColors.get(i));

    }
    List<ProductSize> productSizes = oldProduct.getProductSizes();
    for (int i = 0; i < productSizes.size(); i++) {
      productSizes.get(i).setDeleteFlag(true);
      productSizeRepository.save(productSizes.get(i));

    }
    for (Image image : oldProduct.getImages()) {
      imageRepository.delete(image);
    }

    Product product = productRepository.save(objProduct);

    for (Long tag : productDTO.getTags()) {
      Tag obj = tagRepository.findTagById(tag);
      if (obj == null) {
        throw new VsException("Không có tag id: " + tag);
      }
      productTagRepository.save(new ProductTag(product, obj));
    }

    for (Long id : productDTO.getSizes()) {
      Size obj = sizeRepository.findSizeById(id);
      if (obj == null) {
        throw new VsException("Không có size id: " + id);
      }
      productSizeRepository.save(new ProductSize(product, obj));
    }

    for (Long color : productDTO.getColors()) {
      Color obj = colorRepository.findColorById(color);
      if (obj == null) {
        throw new VsException("Không có color id: " + color);
      }
      productColorRepository.save(new ProductColor(product, obj));
    }

    for (MultipartFile file : productDTO.getImages()) {
      imageRepository.save(new Image(uploadFileUtil.getUrlFromFile(file), product));
    }

    return productRepository.save(product);
  }

  @Override
  public TrueFalseResponse deleteProductById(Long id) {
    Product product = getById(id);
    productRepository.delete(product);
    return new TrueFalseResponse(true);
  }

  private Product getById(Long id) {
    Optional<Product> optionalProduct = productRepository.findById(id);
    if (optionalProduct.isEmpty()) {
      throw new VsException("Can not find by id " + id);
    }
    Product product = optionalProduct.get();
    product.getProductColors().removeIf(ProductColor::isDeleteFlag);
    product.getProductSizes().removeIf(ProductSize::isDeleteFlag);
    product.getTags().removeIf(ProductTag::isDeleteFlag);
    return product;
  }

}
