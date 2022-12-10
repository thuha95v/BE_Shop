package com.example.shopbe;

import com.example.shopbe.entity.Role;
import com.example.shopbe.entity.User;
import com.example.shopbe.repository.RoleRepository;
import com.example.shopbe.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShopBeApplication {

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  public ShopBeApplication(RoleRepository roleRepository, UserRepository userRepository) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(ShopBeApplication.class, args);
  }

  @Bean
  CommandLineRunner init() {
    return (args) -> {
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      if (roleRepository.count() == 0) {
        roleRepository.save(new Role("ROLE_ADMIN", null));
        roleRepository.save(new Role("ROLE_CUSTOMER", null));
      }

      if (userRepository.count() == 0) {
        User user = new User("admin", "admin", passwordEncoder.encode("admin"), "admin@gmail.com");
        user.setRole(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
      }
    };
  }
}