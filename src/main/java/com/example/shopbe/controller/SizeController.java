package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.SizeDTO;
import com.example.shopbe.service.SizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class SizeController {
  private final SizeService sizeService;

  public SizeController(SizeService sizeService) {
    this.sizeService = sizeService;
  }

  @GetMapping("/sizes")
  public ResponseEntity<?> getAllSize() {
    return VsResponseUtil.ok(sizeService.getAllSize());
  }

  @GetMapping("/sizes/{id}")
  public ResponseEntity<?> getSize(@PathVariable Long id) {
    return VsResponseUtil.ok(sizeService.getSize(id));
  }

  @PostMapping("/sizes")
  public ResponseEntity<?> createSize(@RequestBody SizeDTO sizeDTO) {
    return VsResponseUtil.ok(sizeService.createSize(sizeDTO));
  }

  @PatchMapping("/sizes/{id}")
  public ResponseEntity<?> changeSize(@PathVariable Long id, @RequestBody SizeDTO sizeDTO) {
    return VsResponseUtil.ok(sizeService.changeSize(id, sizeDTO));
  }

  @DeleteMapping("/sizes/{id}")
  public ResponseEntity<?> deleteSize(@PathVariable Long id) {
    return VsResponseUtil.ok(sizeService.deleteSize(id));
  }

}
