package com.solano.pagos.backend.services.strategies;

import com.solano.pagos.backend.services.MetodoPagoStrategy;
import org.springframework.stereotype.Component;

@Component("transferencia")
public class PagoTransferencia implements MetodoPagoStrategy {
    @Override
    public void procesarPago(Double monto) {
        System.out.println("Procesando pago por transferencia bancaria por un monto de: S/." + monto);
    }

    @Override
    public String getTipo() {
        return "transferencia";
    }
}
