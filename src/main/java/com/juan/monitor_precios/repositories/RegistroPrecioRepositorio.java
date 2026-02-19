package com.juan.monitor_precios.repositories;

import com.juan.monitor_precios.entidades.RegistroPrecio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroPrecioRepositorio extends JpaRepository<RegistroPrecio, Long> {
}
