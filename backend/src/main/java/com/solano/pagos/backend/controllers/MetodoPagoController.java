package com.solano.pagos.backend.controllers;

import com.solano.pagos.backend.models.MetodoPago;
import com.solano.pagos.backend.repositories.MetodoPagoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metodos-pago")
@CrossOrigin(origins = "*")
public class MetodoPagoController {
    private final MetodoPagoRepository metodoPagoRepository;

    public MetodoPagoController(MetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @PostMapping
    public MetodoPago createMetodoPago(@RequestBody MetodoPago metodoPago){
        return metodoPagoRepository.save(metodoPago);
    }
}
