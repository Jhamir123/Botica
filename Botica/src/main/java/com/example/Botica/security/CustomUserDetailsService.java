package com.example.Botica.security;

import com.example.Botica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var usuario = usuarioRepository.findByEmail(email.toLowerCase().trim())
        .orElseThrow(() -> new UsernameNotFoundException("No existe usuario con email: " + email));

    var authorities = List.of(new SimpleGrantedAuthority(usuario.getRole()));
    return User.builder()
        .username(usuario.getEmail())
        .password(usuario.getPasswordHash())
        .authorities(authorities)
        .accountLocked(!usuario.isEnabled())
        .disabled(!usuario.isEnabled())
        .build();
  }
}
