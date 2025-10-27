package com.example.Botica;

import com.example.Botica.domain.Producto;
import com.example.Botica.repository.ProductoRepository;
import com.example.Botica.service.CatalogoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CatalogoServiceTest {

    @Test
    void listarDestacados_filtraSoloDestacados() {
        Producto a = new Producto(); a.setNombre("A"); a.setDestacado(true); a.setNombre("A");
        Producto b = new Producto(); b.setNombre("B"); b.setDestacado(false); b.setNombre("B");

        ProductoRepository repo = Mockito.mock(ProductoRepository.class);
        when(repo.findAll()).thenReturn(List.of(a,b));

        CatalogoService service = new CatalogoService(repo);
        List<Producto> destacados = service.listarDestacados();

        assertThat(destacados).extracting("nombre").containsExactly("A");
        verify(repo, times(1)).findAll();
    }
}
