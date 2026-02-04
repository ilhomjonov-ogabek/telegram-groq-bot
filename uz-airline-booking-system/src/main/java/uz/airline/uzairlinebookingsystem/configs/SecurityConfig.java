package uz.airline.uzairlinebookingsystem.configs;

import jakarta.servlet.ServletOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;
import uz.airline.uzairlinebookingsystem.dto.AppErrorDTO;
import uz.airline.uzairlinebookingsystem.jwtConfig.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
      ObjectMapper objectMapper,
      JwtFilter jwtFilter
  ) throws Exception {

    return http
        .csrf(csrf -> csrf.disable())


        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        //  401
        .exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) -> {
          String errorPath = request.getRequestURI();
          String errorMessage = authException.getMessage();
          Integer errorCode = 401;

          AppErrorDTO appErrorDto = new AppErrorDTO(errorPath, errorMessage, errorCode);
          response.setStatus(errorCode);
          response.setContentType("application/json");

          ServletOutputStream out = response.getOutputStream();
          objectMapper.writeValue(out, appErrorDto);
        }))
        // 403
        .exceptionHandling(ex ->ex.accessDeniedHandler((request, response, accessDeniedException) -> {
          String errorPath = request.getRequestURI();
          String errorMessage = accessDeniedException.getMessage();
          Integer errorCode = 403;

          AppErrorDTO appErrorDto = new AppErrorDTO(errorPath, errorMessage, errorCode);
          response.setStatus(errorCode);
          response.setContentType("application/json");

          ServletOutputStream out = response.getOutputStream();
          objectMapper.writeValue(out, appErrorDto);
        }))

        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/v1/user/login", "/api/v1/user/sign-in","/api/v1/user/search","/api/v1/user/booking").permitAll()
            .anyRequest().authenticated()
        )

        .httpBasic(httpBasic -> httpBasic.disable())

        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
