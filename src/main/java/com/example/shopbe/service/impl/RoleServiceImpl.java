package com.example.shopbe.service.impl;

import com.example.shopbe.config.exception.VsException;
import com.example.shopbe.dto.RoleDTO;
import com.example.shopbe.entity.Role;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.RoleRepository;
import com.example.shopbe.service.RoleService;
import org.hibernate.type.TrueFalseType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public List<Role> getAllRole() {
    return roleRepository.findAll();
  }

  @Override
  public Role getRoleById(Long id) {
    Optional<Role> role = roleRepository.findById(id);
    if (role.isEmpty()) {
      throw new VsException("Không tồn tại id");
    }

    return role.get();
  }

  @Override
  public Role getRoleByName(RoleDTO roleDTO) {
    return roleRepository.findByName(roleDTO.getName());
  }

  @Override
  public Role createRole(RoleDTO roleDTO) {
    if (roleRepository.findByName(roleDTO.getName()) == null) {
      return roleRepository.save(new Role(roleDTO.getName()));
    }
    throw new VsException("Trùng tên role, không thể add");
  }

  @Override
  public Role editRole(RoleDTO roleDTO, Long id) {
    Optional<Role> role = roleRepository.findById(id);
    if (role.isEmpty()) {
      throw new VsException("Không tồn tại id");
    }

    Role newRole = role.get();
    newRole.setName(roleDTO.getName());
    return roleRepository.save(newRole);
  }

  @Override
  public TrueFalseResponse deleteById(Long id) {
    Optional<Role> role = roleRepository.findById(id);
    if (role.isEmpty()) {
      throw new VsException("Không tồn tại id");
    }
    roleRepository.deleteById(id);
    return new TrueFalseResponse(true);
  }

}
