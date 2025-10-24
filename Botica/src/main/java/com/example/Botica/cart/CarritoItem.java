package com.example.Botica.cart;

import com.example.Botica.domain.Producto;
import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CarritoItem {
  private Producto producto;
  private int cantidad;

  public BigDecimal getImporte() {
    return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
  }
}
