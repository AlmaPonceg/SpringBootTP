package com.utn.productos.exception;

/**
 * Excepción específica cuando no se encuentra un producto.
 * 
 * Usar excepciones dedicadas hace que el código sea más expresivo
 * y más fácil de manejar en el @ControllerAdvice.
 */
public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(Long id) {
        super("No se encontró el producto con id " + id);
    }

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
