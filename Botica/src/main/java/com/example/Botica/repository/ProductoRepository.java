package com.example.Botica.repository;

import com.example.Botica.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
  List<Producto> findByPromoActivaTrueOrderByNombreAsc();
  List<Producto> findByCategoriaIgnoreCaseContainingAndPrecioBetweenOrderByNombreAsc(
      String categoria, BigDecimal min, BigDecimal max);
  List<Producto> findByNombreIgnoreCaseContainingOrderByNombreAsc(String q);
}
