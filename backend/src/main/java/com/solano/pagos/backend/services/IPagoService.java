package com.solano.pagos.backend.services;

import com.solano.pagos.backend.models.Pago;

import java.util.List;

public interface IPagoService {
    Pago registrarPago(Pago pago);

    List<Pago> listarPagos();
}
