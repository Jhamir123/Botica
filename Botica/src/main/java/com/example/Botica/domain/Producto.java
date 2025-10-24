package com.example.Botica.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Producto {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String descripcion;

  @Column(precision = 10, scale = 2)
  private BigDecimal precio;

  @Column(precision = 10, scale = 2)
  private BigDecimal precioRegular;

  private String categoria;
  private String imagenUrl;

  private boolean destacado;
  private boolean promoActiva;
}
