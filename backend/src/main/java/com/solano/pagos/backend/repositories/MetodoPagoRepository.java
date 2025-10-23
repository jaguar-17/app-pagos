package com.solano.pagos.backend.repositories;

import com.solano.pagos.backend.models.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago,Long> {
    Optional<MetodoPago> findByTipo(String tipo);
}
