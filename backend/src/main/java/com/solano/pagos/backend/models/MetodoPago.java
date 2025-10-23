package com.solano.pagos.backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "metodos_pago", uniqueConstraints = @UniqueConstraint(columnNames = "tipo"))
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
}
