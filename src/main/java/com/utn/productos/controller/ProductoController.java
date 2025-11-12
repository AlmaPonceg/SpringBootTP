package com.utn.productos.controller;

import com.utn.productos.dto.ActualizarStockDTO;
import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST principal del TP.
 *
 * Esta clase es la “puerta de entrada” a la API.
 */
@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Endpoints para gestionar productos del e-commerce")
public class ProductoController {

    private final ProductoService productoService;

    // Inyección por constructor
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ===================== GET: listar todos =====================

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<ProductoResponseDTO> productos = productoService
                .toResponseList(productoService.obtenerTodos());
        return ResponseEntity.ok(productos);
    }

    // ===================== GET: por ID =====================

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new com.utn.productos.exception.ProductoNotFoundException(id));

        return ResponseEntity.ok(productoService.toResponseDTO(producto));
    }

    // ===================== GET: por categoría =====================

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Listar productos por categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado por categoría obtenido correctamente")
    })
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria) {
        List<ProductoResponseDTO> productos = productoService
                .toResponseList(productoService.obtenerPorCategoria(categoria));
        return ResponseEntity.ok(productos);
    }

    // ===================== POST: crear producto =====================

    @PostMapping
    @Operation(summary = "Crear un nuevo producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoDTO dto) {
        Producto entidad = productoService.toEntity(dto);
        Producto creado = productoService.crearProducto(entidad);
        ProductoResponseDTO response = productoService.toResponseDTO(creado);

        // 201 Created según la consigna
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ===================== PUT: actualizar completo =====================

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto completo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody ProductoDTO dto) {
        Producto entidad = productoService.toEntity(dto);
        Producto actualizado = productoService.actualizarProducto(id, entidad);
        return ResponseEntity.ok(productoService.toResponseDTO(actualizado));
    }

    // ===================== PATCH: actualizar solo stock =====================

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Actualizar solamente el stock de un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoResponseDTO> actualizarStock(@PathVariable Long id,
                                                               @Valid @RequestBody ActualizarStockDTO dto) {
        Producto actualizado = productoService.actualizarStock(id, dto.getStock());
        return ResponseEntity.ok(productoService.toResponseDTO(actualizado));
    }

    // ===================== DELETE: eliminar =====================

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        // 204 No Content, como pide la consigna
        return ResponseEntity.noContent().build();
    }
}
