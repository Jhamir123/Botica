package com.example.Botica.web;

import com.example.Botica.repository.ProductoRepository;
import com.example.Botica.web.export.ProductoExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ExportController {

    private final ProductoRepository productoRepository;

    public ExportController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/admin/productos/export")
    public void exportProductos(HttpServletResponse response) throws IOException {
        var productos = productoRepository.findAll();
        new ProductoExcelExporter(productos).export(response);
    }
}
