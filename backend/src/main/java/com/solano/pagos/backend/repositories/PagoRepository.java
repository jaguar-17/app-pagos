package com.solano.pagos.backend.repositories;

import com.solano.pagos.backend.models.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
