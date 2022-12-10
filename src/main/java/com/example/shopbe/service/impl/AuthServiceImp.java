package com.example.shopbe.service.impl;

import com.example.shopbe.config.exception.VsException;
import com.example.shopbe.dto.SignupDTO;
import com.example.shopbe.entity.Role;
import com.example.shopbe.entity.User;
import com.example.shopbe.payload.AuthenticationRequest;
import com.example.shopbe.payload.AuthenticationResponse;
import com.example.shopbe.repository.RoleRepository;
import com.example.shopbe.repository.UserRepository;
import com.example.shopbe.service.AuthService;
import com.example.shopbe.service.MyUserDetailService;
import com.example.shopbe.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthServiceImp implements AuthService {
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final MyUserDetailService myUserDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;
  private final AuthenticationManager authenticationManager;

  public AuthServiceImp(UserRepository userRepository, JwtUtil jwtUtil, MyUserDetailService myUserDetailsService,
                        PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                        AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.jwtUtil = jwtUtil;
    this.myUserDetailsService = myUserDetailsService;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public AuthenticationResponse login(AuthenticationRequest request) throws VsException {
    User user = userRepository.findByUsername(request.getUsername());
    try {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          request.getUsername(), request.getPassword()
      ));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (BadCredentialsException e) {
      if (ObjectUtils.isEmpty(user)) {
        throw new VsException("Incorrect username");
      }
      throw new VsException("Wrong password");
    }

    final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);

    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(request.getUsername(),
        request.getPassword()));

    return new AuthenticationResponse(jwt, user.getId(), user.getUsername(), user.getRole().getName());
  }

  @Override
  public AuthenticationResponse signup(SignupDTO input) {
    User oldUser = userRepository.findByUsername(input.getUsername());
    if (oldUser != null) {
      throw new VsException("Username has already exists");
    }
    User user = new User(input.getName(), input.getUsername(), passwordEncoder.encode(input.getPassword()),
        input.getEmail());

    Role role = roleRepository.findByName("CUSTOMER");
    user.setRole(role);

    User newUser = userRepository.save(user);

    final UserDetails userDetails = myUserDetailsService.loadUserByUsername(newUser.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);

    return new AuthenticationResponse(jwt, newUser.getId(), newUser.getUsername(), role.getName());
  }
}
