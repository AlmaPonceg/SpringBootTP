package com.utn.productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO muy específico para la operación de PATCH de stock.
 */
@Schema(description = "DTO para actualizar solamente el stock de un producto")
public class ActualizarStockDTO {

    @Schema(description = "Nuevo valor de stock", example = "5")
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    public ActualizarStockDTO() {
    }

    public ActualizarStockDTO(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
