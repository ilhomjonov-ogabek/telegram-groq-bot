package uz.airline.uzairlinebookingsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.airline.uzairlinebookingsystem.entity.UserEntity;
import uz.airline.uzairlinebookingsystem.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) {
    UserEntity user = userRepository.findByEmail(email)
        .orElseThrow(() ->
            new UsernameNotFoundException("User not found: " + email));

    return User.withUsername(user.getEmail())
        .password(user.getPassword())
        .roles(user.getRole().name())
        .build();
  }
}

