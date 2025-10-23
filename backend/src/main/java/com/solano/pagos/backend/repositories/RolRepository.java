package com.solano.pagos.backend.repositories;

import com.solano.pagos.backend.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
