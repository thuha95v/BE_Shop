package com.example.shopbe.service;

import com.example.shopbe.dto.SizeDTO;
import com.example.shopbe.entity.Size;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface SizeService {

  Size getSize(Long id);

  Size createSize(SizeDTO sizeDTO);

  Size changeSize(Long id, SizeDTO sizeDTO);

  TrueFalseResponse deleteSize(Long id);

  List<Size> getAllSize();

}
