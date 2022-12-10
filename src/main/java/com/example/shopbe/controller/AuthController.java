package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.SignupDTO;
import com.example.shopbe.payload.AuthenticationRequest;
import com.example.shopbe.service.AuthService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestApiV1
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/auth/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody SignupDTO parameter) {
    return VsResponseUtil.ok(authService.signup(parameter));
  }

  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
    return VsResponseUtil.ok(authService.login(authenticationRequest));
  }

  @PostMapping("/demo-file")
  public ResponseEntity<?> login2(@RequestPart("abc") MultipartFile file) {
    return VsResponseUtil.ok(uploadFile(file));
  }

  @SneakyThrows
  public String uploadFile(MultipartFile multipartFile) {
    java.nio.file.Path staticPath = Paths.get("static");
    java.nio.file.Path imagePath = Paths.get("images");
    if (!Files.exists(Paths.get(System.getProperty("user.dir")).resolve(staticPath).resolve(imagePath))) {
      Files.createDirectories(Paths.get(System.getProperty("user.dir")).resolve(staticPath).resolve(imagePath));
      Files.createDirectories(Paths.get(System.getProperty("user.dir")));
    }
    Path file = Paths.get(System.getProperty("user.dir")).resolve(staticPath)
        .resolve(imagePath).resolve(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    try (OutputStream os = Files.newOutputStream(file)) {
      os.write(multipartFile.getBytes());
    }
    return imagePath.resolve(multipartFile.getOriginalFilename()).toString();
  }

}
