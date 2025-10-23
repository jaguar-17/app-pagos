package com.solano.pagos.backend.services;

public interface MetodoPagoStrategy {
    void procesarPago(Double monto);

    String getTipo();
}
