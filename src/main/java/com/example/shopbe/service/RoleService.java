package com.example.shopbe.service;

import com.example.shopbe.dto.RoleDTO;
import com.example.shopbe.entity.Role;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface RoleService {

  List<Role> getAllRole();

  Role getRoleById(Long id);

  Role getRoleByName(RoleDTO roleDTO);

  Role createRole(RoleDTO roleDTO);

  Role editRole(RoleDTO roleDTO, Long id);

  TrueFalseResponse deleteById(Long id);

}
