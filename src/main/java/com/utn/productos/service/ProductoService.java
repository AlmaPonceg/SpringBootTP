package com.utn.productos.service;

import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.exception.ProductoNotFoundException;
import com.utn.productos.exception.StockInsuficienteException;
import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Capa de negocio de la API.
 * 
 * Acá van las reglas de negocio, validaciones adicionales y la
 * orquestación con el repositorio
 */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor (recomendada)
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // ===================== Métodos solicitados en la consigna =====================

    /**
     * Crea un nuevo producto a partir de la entidad.
     * Normalmente se usaría el DTO acá, pero dejamos este método para cumplir
     * con lo pedido en la parte 2 de la consigna.
     */
    public Producto crearProducto(Producto producto) {
        // Podríamos hacer alguna validación de negocio adicional acá.
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        // En un caso real probablemente haríamos un patch más fino,
        // pero como la consigna habla de actualizar "completo", pisamos campos.
        existente.setNombre(productoActualizado.getNombre());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setStock(productoActualizado.getStock());
        existente.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(existente);
    }

    public Producto actualizarStock(Long id, Integer nuevoStock) {
        if (nuevoStock < 0) {
            throw new StockInsuficienteException("El stock no puede ser negativo");
        }

        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        existente.setStock(nuevoStock);
        return productoRepository.save(existente);
    }

    public void eliminarProducto(Long id) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        productoRepository.delete(existente);
    }

    // ===================== Helpers para mapear entre Entity y DTOs =====================

    public Producto toEntity(ProductoDTO dto) {
        // En proyectos grandes usaría MapStruct,
        // pero para el TP hacerlo “a mano” ayuda a entender mejor el flujo.
        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .categoria(dto.getCategoria())
                .build();
    }

    public ProductoResponseDTO toResponseDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria()
        );
    }

    public List<ProductoResponseDTO> toResponseList(List<Producto> productos) {
        return productos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
