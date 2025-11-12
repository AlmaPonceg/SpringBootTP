package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO pensado solo para respuestas.
 * 
 * No lleva validaciones porque este objeto no entra por la API,
 * solo se utiliza para devolver datos de forma controlada.
 */
@Schema(description = "DTO de respuesta para productos")
public class ProductoResponseDTO {

    @Schema(description = "Identificador único del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Notebook gamer")
    private String nombre;

    @Schema(description = "Descripción del producto")
    private String descripcion;

    @Schema(description = "Precio del producto", example = "999.99")
    private Double precio;

    @Schema(description = "Stock disponible", example = "10")
    private Integer stock;

    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;

    public ProductoResponseDTO() {
    }

    public ProductoResponseDTO(Long id,
                               String nombre,
                               String descripcion,
                               Double precio,
                               Integer stock,
                               Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Getters y setters estándar

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
