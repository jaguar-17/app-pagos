package com.solano.pagos.backend.services.strategies;

import com.solano.pagos.backend.services.MetodoPagoStrategy;
import org.springframework.stereotype.Component;

@Component("tarjeta")
public class PagoTarjeta implements MetodoPagoStrategy {
    @Override
    public void procesarPago(Double monto) {
        System.out.println("Procesando pago con tarjeta por un monto de: S/." + monto);
    }

    @Override
    public String getTipo() {
        return "tarjeta";
    }
}
