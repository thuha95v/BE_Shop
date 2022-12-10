package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.ColorDTO;
import com.example.shopbe.service.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class ColorController {
  private final ColorService colorService;

  public ColorController(ColorService colorService) {
    this.colorService = colorService;
  }

  @GetMapping("/colors")
  public ResponseEntity<?> getAllColor() {
    return VsResponseUtil.ok(colorService.getAllColor());
  }

  @GetMapping("/colors/{id}")
  public ResponseEntity<?> getColor(@PathVariable Long id) {
    return VsResponseUtil.ok(colorService.getColor(id));
  }

  @PostMapping("/colors")
  public ResponseEntity<?> createColor(@RequestBody ColorDTO colorDTO) {
    return VsResponseUtil.ok(colorService.createColor(colorDTO));
  }

  @PatchMapping("/colors/{id}")
  public ResponseEntity<?> changeColor(@PathVariable Long id, @RequestBody ColorDTO colorDTO) {
    return VsResponseUtil.ok(colorService.changeColor(id, colorDTO));
  }

  @DeleteMapping("/colors/{id}")
  public ResponseEntity<?> deleteColor(@PathVariable Long id) {
    return VsResponseUtil.ok(colorService.deleteColor(id));
  }
}
