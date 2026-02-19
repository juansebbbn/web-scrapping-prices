package com.juan.monitor_precios.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "componentes")
public class Componente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tienda;

    @Column(length = 2048)
    private String url;
    private Double precioObjetivo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "componente_selector")
    private List<String> selectores = new ArrayList<>();

    @OneToMany
    private List<RegistroPrecio> historial = new ArrayList<>();

    public void agregarHistorial(RegistroPrecio reg) {
        historial.add(reg);
    }
}

