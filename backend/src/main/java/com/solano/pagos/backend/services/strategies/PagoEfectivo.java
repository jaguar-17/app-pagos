package com.solano.pagos.backend.services.strategies;

import com.solano.pagos.backend.services.MetodoPagoStrategy;
import org.springframework.stereotype.Component;

@Component("efectivo")
public class PagoEfectivo implements MetodoPagoStrategy {
    @Override
    public void procesarPago(Double monto) {
        System.out.println("Procesando pago en efectivo por un monto de: S/." + monto);
    }

    @Override
    public String getTipo() {
        return "efectivo";
    }
}
