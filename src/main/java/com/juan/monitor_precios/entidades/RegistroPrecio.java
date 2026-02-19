package com.juan.monitor_precios.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "registros_precios")
public class RegistroPrecio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double precio;
    private LocalDateTime fechaCaptura;

    public RegistroPrecio(double nuevoPrecio, LocalDateTime now) {
        this.precio = nuevoPrecio;
        this.fechaCaptura = now;
    }
}