package org.example.springsecurity.jwtConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String authorization =  request.getHeader("Authorization");
    if (authorization != null && authorization.startsWith("Bearer ")) {
      String token = authorization.substring(7);
      String username = jwtProvider.getUsername(token);

      SecurityContextHolder.getContext().setAuthentication()
    }

  }
}
