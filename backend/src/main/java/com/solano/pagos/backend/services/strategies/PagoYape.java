package com.solano.pagos.backend.services.strategies;

import com.solano.pagos.backend.services.MetodoPagoStrategy;
import org.springframework.stereotype.Component;

@Component("yape")
public class PagoYape implements MetodoPagoStrategy {
    @Override
    public void procesarPago(Double monto) {
        System.out.println("Procesando pago con Yape por un monto de: S/." + monto);
    }

    @Override
    public String getTipo() {
        return "yape";
    }
}
