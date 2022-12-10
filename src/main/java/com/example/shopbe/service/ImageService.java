package com.example.shopbe.service;

import com.example.shopbe.dto.ImageDTO;
import com.example.shopbe.entity.Image;
import com.example.shopbe.payload.TrueFalseResponse;
import org.hibernate.type.TrueFalseType;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image getImage(Long id);
    Image createImage(MultipartFile file);
    Image changeImage(Long id, ImageDTO imageDTO);
    TrueFalseResponse deleteImage(Long id);
}
