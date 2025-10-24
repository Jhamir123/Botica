package com.example.Botica.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "venta_detalle")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class VentaDetalle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Venta venta;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Producto producto;

  @Column(nullable = false)
  private Integer cantidad;

  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal precioUnitario;

  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal subtotal;
}
