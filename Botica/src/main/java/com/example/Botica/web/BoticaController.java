package com.example.Botica.web;

import com.example.Botica.cart.CarritoItem;
import com.example.Botica.domain.Producto;
import com.example.Botica.repository.ProductoRepository;
import com.example.Botica.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("carrito")
public class BoticaController {

  private final CatalogoService catalogo;
  private final ProductoRepository productoRepo;

  @ModelAttribute("carrito")
  public List<CarritoItem> initCarrito() {
    return new ArrayList<>();
  }

  @GetMapping({"/", "/inicio"})
  public String inicio(Model model) {
    var destacados = productoRepo.findAll().stream().filter(Producto::isDestacado).toList();
    model.addAttribute("destacados", destacados);
    return "inicio";
  }

  @GetMapping("/promociones")
  public String promociones(Model model) {
    model.addAttribute("items", catalogo.promociones());
    return "promociones";
  }

  @GetMapping("/catalogo")
  public String catalogo(Model model,
                         @RequestParam(required = false) String categoria,
                         @RequestParam(required = false) BigDecimal min,
                         @RequestParam(required = false) BigDecimal max,
                         @RequestParam(required = false) String q) {
    model.addAttribute("q", q);
    model.addAttribute("categoria", categoria);
    model.addAttribute("min", min);
    model.addAttribute("max", max);
    model.addAttribute("items",
      (q != null && !q.isBlank()) ? catalogo.buscar(q)
                                  : catalogo.catalogo(categoria, min, max));
    return "catalogo";
  }

  @PostMapping("/carrito/agregar/{id}")
  public String agregar(@PathVariable Long id,
                        @RequestParam(defaultValue = "1") int cantidad,
                        @ModelAttribute("carrito") List<CarritoItem> carrito) {
    var prod = productoRepo.findById(id).orElseThrow();
    var existente = carrito.stream().filter(ci -> ci.getProducto().getId().equals(id)).findFirst();
    if (existente.isPresent()) {
      existente.get().setCantidad(existente.get().getCantidad() + cantidad);
    } else {
      carrito.add(CarritoItem.builder().producto(prod).cantidad(cantidad).build());
    }
    return "redirect:/carrito";
  }

  @GetMapping("/carrito")
  public String verCarrito(@ModelAttribute("carrito") List<CarritoItem> carrito, Model model) {
    var subtotal = carrito.stream().map(CarritoItem::getImporte).reduce(BigDecimal.ZERO, BigDecimal::add);
    var igv = subtotal.multiply(new BigDecimal("0.18"));
    var total = subtotal.add(igv);
    model.addAttribute("subtotal", subtotal);
    model.addAttribute("igv", igv);
    model.addAttribute("total", total);
    return "carrito";
  }

  @PostMapping("/carrito/cambiar/{id}")
  public String cambiarCantidad(@PathVariable Long id,
                                @RequestParam int cantidad,
                                @ModelAttribute("carrito") List<CarritoItem> carrito) {
    carrito.stream().filter(ci -> ci.getProducto().getId().equals(id)).findFirst()
      .ifPresent(ci -> ci.setCantidad(Math.max(1, cantidad)));
    return "redirect:/carrito";
  }

  @PostMapping("/carrito/eliminar/{id}")
  public String eliminar(@PathVariable Long id, @ModelAttribute("carrito") List<CarritoItem> carrito) {
    carrito.removeIf(ci -> ci.getProducto().getId().equals(id));
    return "redirect:/carrito";
  }

  // 🚫 Importante: NO definir aquí /pago, /login ni /registro (para evitar duplicados)
}
