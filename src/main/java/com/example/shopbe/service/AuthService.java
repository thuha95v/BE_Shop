package com.example.shopbe.service;

import com.example.shopbe.dto.SignupDTO;
import com.example.shopbe.payload.AuthenticationRequest;
import com.example.shopbe.payload.AuthenticationResponse;

public interface AuthService {

  AuthenticationResponse login(AuthenticationRequest input);

  AuthenticationResponse signup(SignupDTO input);

}
