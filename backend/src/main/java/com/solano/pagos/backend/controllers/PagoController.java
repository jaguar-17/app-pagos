package com.solano.pagos.backend.controllers;

import com.solano.pagos.backend.models.MetodoPago;
import com.solano.pagos.backend.models.Pago;
import com.solano.pagos.backend.models.Usuario;
import com.solano.pagos.backend.repositories.MetodoPagoRepository;
import com.solano.pagos.backend.repositories.UsuarioRepository;
import com.solano.pagos.backend.services.IPagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagoController {
    private final IPagoService pagoService;
    private final MetodoPagoRepository metodoPagoRepository;
    private final UsuarioRepository usuarioRepository;

    public PagoController(IPagoService pagoService, MetodoPagoRepository metodoPagoRepository, UsuarioRepository usuarioRepository) {
        this.pagoService = pagoService;
        this.metodoPagoRepository = metodoPagoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public Pago crearPago(@RequestBody Pago pago){
        if (pago.getUsuario() != null && pago.getUsuario().getId() != null) {
            Optional<Usuario> u = usuarioRepository.findById(pago.getUsuario().getId());
            u.ifPresent(pago::setUsuario);
        }
        if (pago.getMetodoPago() != null && pago.getMetodoPago().getTipo() != null) {
            Optional<MetodoPago> m = metodoPagoRepository.findByTipo(pago.getMetodoPago().getTipo());
            m.ifPresent(pago::setMetodoPago);
        }
        return pagoService.registrarPago(pago);
    }

    @GetMapping
    public List<Pago> listarPagos(){
        return pagoService.listarPagos();
    }
}
