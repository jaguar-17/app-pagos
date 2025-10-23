package com.solano.pagos.backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}
