package com.example.shopbe.service.impl;

import com.example.shopbe.dto.SizeDTO;
import com.example.shopbe.entity.Size;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.SizeRepository;
import com.example.shopbe.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
  private final SizeRepository sizeRepository;

  public SizeServiceImpl(SizeRepository sizeRepository) {
    this.sizeRepository = sizeRepository;
  }

  @Override
  public Size getSize(Long id) {
      return sizeRepository.findSizeById(id);
  }

  @Override
  public Size createSize(SizeDTO sizeDTO) {
    Size size = new Size();
    size.setName(sizeDTO.getName());
    return sizeRepository.save(size);
  }

  @Override
  public Size changeSize(Long id, SizeDTO sizeDTO) {
    Size size = sizeRepository.findSizeById(id);
    size.setName(sizeDTO.getName());
    return sizeRepository.save(size);
  }

  @Override
  public TrueFalseResponse deleteSize(Long id) {
    Size size = sizeRepository.findSizeById(id);
    sizeRepository.delete(size);
    return new TrueFalseResponse(true);
  }

  @Override
  public List<Size> getAllSize() {
    return sizeRepository.findAll();
  }

}
