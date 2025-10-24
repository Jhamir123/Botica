package com.example.Botica.service;

import com.example.Botica.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CatalogoService {
  private final ProductoRepository repo;
  public CatalogoService(ProductoRepository repo) { this.repo = repo; }

  public java.util.List<com.example.Botica.domain.Producto> promociones() {
    return repo.findByPromoActivaTrueOrderByNombreAsc();
  }

  public java.util.List<com.example.Botica.domain.Producto> catalogo(String categoria, BigDecimal min, BigDecimal max) {
    String cat = (categoria == null || categoria.isBlank()) ? "" : categoria;
    var minV = (min == null) ? BigDecimal.ZERO : min;
    var maxV = (max == null) ? new BigDecimal("9999") : max;
    return repo.findByCategoriaIgnoreCaseContainingAndPrecioBetweenOrderByNombreAsc(cat, minV, maxV);
  }

  public java.util.List<com.example.Botica.domain.Producto> buscar(String q) {
    return repo.findByNombreIgnoreCaseContainingOrderByNombreAsc(q == null ? "" : q);
  }
}
