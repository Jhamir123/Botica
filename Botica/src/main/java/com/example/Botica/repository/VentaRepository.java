package com.example.Botica.repository;

import com.example.Botica.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Long> {

  @Query("select v from Venta v left join fetch v.detalles d left join fetch d.producto p where v.id = :id")
  Optional<Venta> findWithDetallesById(Long id);

  boolean existsByNumeroBoleta(String numeroBoleta);
}
