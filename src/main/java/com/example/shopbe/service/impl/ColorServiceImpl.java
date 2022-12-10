package com.example.shopbe.service.impl;

import com.example.shopbe.dto.ColorDTO;
import com.example.shopbe.entity.Color;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.ColorRepository;
import com.example.shopbe.service.ColorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

  private final ColorRepository colorRepository;

  public ColorServiceImpl(ColorRepository colorRepository) {
    this.colorRepository = colorRepository;
  }

  @Override
  public Color getColor(Long id) {
    Color color = colorRepository.findColorById(id);
    return color;
  }

  @Override
  public Color createColor(ColorDTO colorDTO) {
    Color color = new Color();
    color.setName(colorDTO.getName());
    return colorRepository.save(color);
  }

  @Override
  public Color changeColor(Long id, ColorDTO colorDTO) {
    Color color = colorRepository.findColorById(id);
    color.setName(colorDTO.getName());
    return colorRepository.save(color);
  }

  @Override
  public TrueFalseResponse deleteColor(Long id) {
    Color color = colorRepository.findColorById(id);
    colorRepository.delete(color);
    return new TrueFalseResponse(true);
  }

  @Override
  public List<Color> getAllColor() {
    return colorRepository.findAll();
  }
}
