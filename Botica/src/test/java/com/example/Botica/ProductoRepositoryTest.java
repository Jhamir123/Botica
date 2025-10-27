package com.example.Botica;

import com.example.Botica.domain.Producto;
import com.example.Botica.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void saveAndFindAll() {
        Producto p = new Producto();
        p.setNombre("Paracetamol 500mg");
        p.setDescripcion("Analgésico");
        p.setCategoria("Analgésicos");
        p.setPrecio(new BigDecimal("3.50"));
        p.setPrecioRegular(new java.math.BigDecimal("4.20"));
        p.setPromoActiva(true);
        p.setDestacado(true);
        productoRepository.save(p);

        List<Producto> all = productoRepository.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all.get(0).getNombre()).isEqualTo("Paracetamol 500mg");
    }
}
