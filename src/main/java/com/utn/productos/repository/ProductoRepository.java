package com.utn.productos.repository;

import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Capa de acceso a datos.
 * 
 * Al extender de JpaRepository evitamos escribir código CRUD repetitivo.
 * Spring Data genera la implementación automáticamente en tiempo de ejecución.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Búsqueda sencilla por categoría.
     * Spring Data arma la query a partir del nombre del método.
     */
    List<Producto> findByCategoria(Categoria categoria);
}
