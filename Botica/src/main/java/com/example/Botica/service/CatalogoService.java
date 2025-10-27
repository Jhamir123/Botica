package com.example.Botica.service;

import com.example.Botica.domain.Producto;
import com.example.Botica.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CatalogoService {

    private final ProductoRepository repo;

    public CatalogoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> promociones() {
        return repo.findByPromoActivaTrueOrderByNombreAsc();
    }

    public List<Producto> catalogo(String categoria, BigDecimal min, BigDecimal max) {
        String cat = (categoria == null || categoria.isBlank()) ? "" : categoria;
        BigDecimal minV = (min == null) ? BigDecimal.ZERO : min;
        BigDecimal maxV = (max == null) ? new BigDecimal("9999") : max;
        return repo.findByCategoriaIgnoreCaseContainingAndPrecioBetweenOrderByNombreAsc(cat, minV, maxV);
    }

    public List<Producto> buscar(String q) {
        return repo.findByNombreIgnoreCaseContainingOrderByNombreAsc(q == null ? "" : q);
    }

    public List<Producto> listarDestacados() {
        return repo.findAll().stream().filter(Producto::isDestacado).toList();
    }
}
