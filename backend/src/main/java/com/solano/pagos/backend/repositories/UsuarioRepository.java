package com.solano.pagos.backend.repositories;

import com.solano.pagos.backend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
