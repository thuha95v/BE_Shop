package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.RoleDTO;
import com.example.shopbe.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class RoleController {
  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping("/role")
  public ResponseEntity<?> getAllRole() {
    return VsResponseUtil.ok(roleService.getAllRole());
  }

  @GetMapping("/role/{id}")
  public ResponseEntity<?> getRoleById(@PathVariable Long id) {
    return VsResponseUtil.ok(roleService.getRoleById(id));
  }

  @PostMapping("/role")
  public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO) {
    return VsResponseUtil.ok(roleService.createRole(roleDTO));
  }

  @PatchMapping("/role/{id}")
  public ResponseEntity<?> changeSize(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
    return VsResponseUtil.ok(roleService.editRole(roleDTO, id));
  }

  @DeleteMapping("/role/{id}")
  public ResponseEntity<?> deleteSize(@PathVariable Long id) {
    return VsResponseUtil.ok(roleService.deleteById(id));
  }

}
