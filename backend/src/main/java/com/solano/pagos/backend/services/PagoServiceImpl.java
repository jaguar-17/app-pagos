package com.solano.pagos.backend.services;

import com.solano.pagos.backend.models.Pago;
import com.solano.pagos.backend.repositories.PagoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PagoServiceImpl implements IPagoService {
    private final PagoRepository pagoRepository;
    private final Map<String, MetodoPagoStrategy> estrategiasPago;

    public PagoServiceImpl(PagoRepository pagoRepository, Map<String, MetodoPagoStrategy> estrategiasPago) {
        this.pagoRepository = pagoRepository;
        this.estrategiasPago = estrategiasPago;
    }

    @Override
    public Pago registrarPago(Pago pago) {
        pago.setFecha(LocalDateTime.now());

        String tipo = pago.getMetodoPago() != null ? pago.getMetodoPago().getTipo() : null;
        if (tipo != null && estrategiasPago.containsKey(tipo)) {
            MetodoPagoStrategy estrategia = estrategiasPago.get(tipo);
            estrategia.procesarPago(pago.getMonto());
        } else {
            System.out.println("Metodo de pago no reconocido: " + tipo);
        }
        return pagoRepository.save(pago);
    }

    @Override
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }
}
