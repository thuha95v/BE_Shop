package com.example.shopbe.service.impl;

import com.example.shopbe.dto.ImageDTO;
import com.example.shopbe.entity.Image;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.ImageRepository;
import com.example.shopbe.service.ImageService;
import com.example.shopbe.util.UploadFileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

  private final ImageRepository imageRepository;
  private final UploadFileUtil uploadFileUtil;

  public ImageServiceImpl(ImageRepository imageRepository, UploadFileUtil uploadFileUtil) {
    this.imageRepository = imageRepository;
    this.uploadFileUtil = uploadFileUtil;
  }

  @Override
  public Image getImage(Long id) {
    Image image = imageRepository.findImageById(id);

    return image;
  }

  @Override
  public Image createImage(MultipartFile file) {
    Image image = new Image();
    image.setLink(uploadFileUtil.getUrlFromFile(file));
    return imageRepository.save(image);
  }

  @Override
  public Image changeImage(Long id, ImageDTO imageDTO) {
    Image image = imageRepository.findImageById(id);
    image.setLink(imageDTO.getLink());

    return imageRepository.save(image);
  }

  @Override
  public TrueFalseResponse deleteImage(Long id) {
    Image image = imageRepository.findImageById(id);
    imageRepository.delete(image);
    return new TrueFalseResponse(true);
  }

}
