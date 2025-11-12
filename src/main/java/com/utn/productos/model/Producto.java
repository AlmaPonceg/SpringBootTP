package com.utn.productos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad principal del TP.
 * Representa un producto del catálogo de un e-commerce básico.
 */
@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre comercial del producto.
     * 
     * A nivel de base de datos no aplicamos validaciones, eso lo controla el DTO
     * con Bean Validation para no mezclar responsabilidades.
     */
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    /**
     * Guardamos el nombre del enum (STRING) para que sea legible en la base
     * y más robusto ante cambios de orden en el enum.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Categoria categoria;
}
