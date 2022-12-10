package com.example.shopbe.service;

import com.example.shopbe.dto.ColorDTO;
import com.example.shopbe.entity.Color;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface ColorService {

  Color getColor(Long id);

  Color createColor(ColorDTO colorDTO);

  Color changeColor(Long id, ColorDTO colorDTO);

  TrueFalseResponse deleteColor(Long id);

  List<Color> getAllColor();

}
