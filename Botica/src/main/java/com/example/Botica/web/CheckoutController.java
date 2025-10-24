package com.example.Botica.web;

import com.example.Botica.cart.CarritoItem;
import com.example.Botica.domain.MetodoPago;
import com.example.Botica.domain.Venta;
import com.example.Botica.repository.VentaRepository;
import com.example.Botica.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("carrito")
public class CheckoutController {

  private final CheckoutService checkoutService;
  private final VentaRepository ventaRepo;

  @ModelAttribute("carrito")
  public List<CarritoItem> initCarrito() {
    return new java.util.ArrayList<>();
  }

  @GetMapping("/pago")
  public String pago() {
    return "pago";
  }

  @PostMapping("/checkout/confirmar")
  public String confirmar(@RequestParam("metodo") String metodoStr,
                          @ModelAttribute("carrito") List<CarritoItem> carrito,
                          RedirectAttributes ra) {

   
    String emailCliente = "cliente@demo.com";

    MetodoPago metodo = MetodoPago.valueOf(metodoStr); // YAPE, PLIN, TARJETA, EFECTIVO
    Venta venta = checkoutService.confirmarCompra(metodo, carrito, emailCliente);

    // Limpia el carrito tras confirmar
    carrito.clear();

    ra.addFlashAttribute("ok", "¡Pago registrado! N° " + venta.getNumeroBoleta());
    return "redirect:/comprobante/" + venta.getId();
  }

  @GetMapping("/comprobante/{id}")
  public String comprobante(@PathVariable Long id, Model model) {
    Venta venta = ventaRepo.findWithDetallesById(id)
        .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

    model.addAttribute("venta", venta);
    model.addAttribute("detalles", venta.getDetalles());
    model.addAttribute("subtotal", venta.getSubtotal());
    model.addAttribute("igv", venta.getIgv());
    model.addAttribute("total", venta.getTotal());
    return "comprobante";
  }

  // Descarga PDF – placeholder (aún no genera PDF)
  @GetMapping("/comprobante/{id}/pdf")
  public String descargarPdf(@PathVariable Long id, Model model) {
    Venta venta = ventaRepo.findWithDetallesById(id)
        .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

    model.addAttribute("venta", venta);
    model.addAttribute("detalles", venta.getDetalles());
    return "comprobante"; // por ahora volvemos a la vista
  }
}
