package com.juan.monitor_precios.repositories;

import com.juan.monitor_precios.entidades.Componente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponenteRepositorio extends JpaRepository<Componente, Long> {
}
