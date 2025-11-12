package com.utn.productos.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para devolver errores de forma consistente desde el @ControllerAdvice.
 */
public class ErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private int status;

    private String mensaje;

    private String path;

    /**
     * Lista opcional de detalles (por ejemplo, errores de validación de campos).
     */
    private List<String> detalles;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp,
                         int status,
                         String mensaje,
                         String path,
                         List<String> detalles) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensaje = mensaje;
        this.path = path;
        this.detalles = detalles;
    }

    // Getters y setters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
    }
}
