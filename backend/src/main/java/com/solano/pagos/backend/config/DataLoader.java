package com.solano.pagos.backend.config;

import com.solano.pagos.backend.models.MetodoPago;
import com.solano.pagos.backend.models.Rol;
import com.solano.pagos.backend.models.Usuario;
import com.solano.pagos.backend.repositories.MetodoPagoRepository;
import com.solano.pagos.backend.repositories.RolRepository;
import com.solano.pagos.backend.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final MetodoPagoRepository metodoPagoRepository;

    public DataLoader(RolRepository rolRepository,
                      UsuarioRepository usuarioRepository,
                      MetodoPagoRepository metodoPagoRepository) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo carga si la tabla de roles está vacía (evita duplicados en reinicios)
        if (rolRepository.count() == 0) {
            // Crear roles
            Rol admin = new Rol();
            admin.setNombre("ADMIN");
            rolRepository.save(admin);

            Rol cliente = new Rol();
            cliente.setNombre("CLIENTE");
            rolRepository.save(cliente);

            // Crear usuario demo con rol CLIENTE
            Usuario u = new Usuario();
            u.setNombre("Usuario Demo");
            u.setEmail("demo@demo.com");
            u.setRol(cliente);
            usuarioRepository.save(u);

            // Crear métodos de pago por defecto
            MetodoPago m1 = new MetodoPago();
            m1.setTipo("tarjeta");
            metodoPagoRepository.save(m1);

            MetodoPago m2 = new MetodoPago();
            m2.setTipo("yape");
            metodoPagoRepository.save(m2);

            MetodoPago m3 = new MetodoPago();
            m3.setTipo("transferencia");
            metodoPagoRepository.save(m3);

            System.out.println("[DataLoader] Datos iniciales creados.");
        } else {
            System.out.println("[DataLoader] Datos ya existen. No se realizaron cambios.");
        }
    }
}
