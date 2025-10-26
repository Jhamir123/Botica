package com.example.Botica.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filter(HttpSecurity http) throws Exception {
    http
      // CSRF habilitado por defecto (recomendado)
      .csrf(Customizer.withDefaults())
      .authorizeHttpRequests(reg -> reg
        .requestMatchers(
          "/", "/inicio", "/promociones", "/catalogo", "/carrito/**",
          "/pago", "/checkout/**", "/comprobante/**",
          "/css/**", "/js/**", "/img/**", "/webjars/**",
          "/login", "/registro", "/registro/**", "/error"
        ).permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(form -> form
        .loginPage("/login")            // GET
        .loginProcessingUrl("/login")   // POST
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/inicio", true)
        .permitAll()
      )
      .logout(logout -> logout
        .logoutUrl("/logout")           // POST
        .logoutSuccessUrl("/inicio")
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .deleteCookies("JSESSIONID")
        .permitAll()
      );

    return http.build();
  }
}
