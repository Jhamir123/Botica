package com.example.Botica.bootstap;

import com.example.Botica.domain.Producto;
import com.example.Botica.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component @Profile("!prod")
public class DataLoader implements CommandLineRunner {
  private final ProductoRepository repo;
  public DataLoader(ProductoRepository repo) { this.repo = repo; }

  @Override public void run(String... args) {
    if (repo.count() > 0) return;

    List<Producto> items = List.of(
      Producto.builder().nombre("Paracetamol 500mg (Caja x 10)")
        .descripcion("Analgésico y antipirético")
        .precio(new BigDecimal("5.99")).precioRegular(new BigDecimal("5.99"))
        .categoria("Analgésicos").imagenUrl("/img/paracetamol.jpg")
        .destacado(true).promoActiva(false).build(),

      Producto.builder().nombre("Ibuprofeno 400mg (Blíster x 10)")
        .descripcion("Antiinflamatorio")
        .precio(new BigDecimal("8.20")).precioRegular(new BigDecimal("8.20"))
        .categoria("Analgésicos").imagenUrl("/img/ibuprofeno.jpg")
        .destacado(true).promoActiva(false).build(),

      Producto.builder().nombre("Jarabe Tos Infantil 100ml")
        .descripcion("Alivia la tos en niños")
        .precio(new BigDecimal("12.90")).precioRegular(new BigDecimal("12.90"))
        .categoria("Resfrío y Gripe").imagenUrl("/img/jarabe.jpg")
        .promoActiva(false).build(),

      Producto.builder().nombre("Vitaminas Complejo B (Caja x 30)")
        .descripcion("Contribuye al metabolismo")
        .precio(new BigDecimal("15.00")).precioRegular(new BigDecimal("15.00"))
        .categoria("Vitaminas y Suplementos").imagenUrl("/img/vitaminasb.jpg")
        .promoActiva(false).build(),

      // Promociones
      Producto.builder().nombre("Jarabe Pediátrico Antitusivo")
        .descripcion("Sabor fresa, para niños").precio(new BigDecimal("19.90"))
        .precioRegular(new BigDecimal("25.00")).categoria("Resfrío y Gripe")
        .imagenUrl("/img/promo-jarabe.jpg").promoActiva(true).build(),

      Producto.builder().nombre("Analgésico 500mg (Blíster)")
        .descripcion("Dolores de cabeza y musculares").precio(new BigDecimal("9.99"))
        .precioRegular(new BigDecimal("12.50")).categoria("Analgésicos")
        .imagenUrl("/img/promo-analgesico.jpg").promoActiva(true).build(),

      Producto.builder().nombre("Crema Hidratante con Aloe Vera")
        .descripcion("Hidratación profunda").precio(new BigDecimal("22.50"))
        .precioRegular(new BigDecimal("30.00")).categoria("Cuidado Personal")
        .imagenUrl("/img/crema.jpg").promoActiva(true).build(),

      Producto.builder().nombre("Protector Solar FPS 50+")
        .descripcion("Alta protección resistente al agua").precio(new BigDecimal("48.00"))
        .precioRegular(new BigDecimal("55.00")).categoria("Cuidado Personal")
        .imagenUrl("/img/protector.jpg").promoActiva(true).build(),

      Producto.builder().nombre("Suplemento Omega-3")
        .descripcion("Salud cardiovascular y cerebral").precio(new BigDecimal("59.90"))
        .precioRegular(new BigDecimal("70.00")).categoria("Vitaminas y Suplementos")
        .imagenUrl("/img/omega3.jpg").promoActiva(true).build(),

      Producto.builder().nombre("Tiritas Adhesivas Pack Familiar")
        .descripcion("100 unidades, hipoalergénicas").precio(new BigDecimal("14.50"))
        .precioRegular(new BigDecimal("18.00")).categoria("Primeros Auxilios")
        .imagenUrl("/img/tiritas.jpg").promoActiva(true).build(),

      Producto.builder().nombre("Termómetro Digital Infrarrojo")
        .descripcion("Medición rápida y precisa").precio(new BigDecimal("69.90"))
        .precioRegular(new BigDecimal("85.00")).categoria("Primeros Auxilios")
        .imagenUrl("/img/termometro.jpg").promoActiva(true).build(),

      Producto.builder().nombre("Cepillo Dental Eléctrico")
        .descripcion("Limpieza profunda").precio(new BigDecimal("99.00"))
        .precioRegular(new BigDecimal("120.00")).categoria("Cuidado Personal")
        .imagenUrl("/img/cepillo.jpg").promoActiva(true).build()
    );

    repo.saveAll(items);
  }
}
