package com.utn.productos.exception;

/**
 * Excepción opcional sugerida en la consigna para cuando
 * se intenta dejar el stock por debajo de cero.
 */
public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(Long id, int stockSolicitado) {
        super("No hay stock suficiente para el producto con id " + id +
                ". Stock solicitado: " + stockSolicitado);
    }

    public StockInsuficienteException(String message) {
        super(message);
    }
}
