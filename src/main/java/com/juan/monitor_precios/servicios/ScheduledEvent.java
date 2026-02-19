package com.juan.monitor_precios.servicios;

import com.juan.monitor_precios.entidades.Componente;
import com.juan.monitor_precios.entidades.RegistroPrecio;
import com.juan.monitor_precios.repositories.ComponenteRepositorio;
import com.juan.monitor_precios.repositories.RegistroPrecioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledEvent {

    private RegistroPrecioRepositorio repReg;
    private ComponenteRepositorio repComps;
    private ServicioScrapper servicioScrapper;

    public ScheduledEvent(RegistroPrecioRepositorio repReg, ComponenteRepositorio repComps, ServicioScrapper servicioScrapper) {
        this.repReg = repReg;
        this.repComps = repComps;
        this.servicioScrapper = servicioScrapper;
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void monitorearPrecios() {
        List<Componente> componentes = repComps.findAll();

        System.out.println("Ejecutando rutina de monitoreo de precios - Componentes a monitorear: " + componentes.size());

        for (Componente c : componentes) {
            try {
                System.out.println("Monitoreando: " + c.getNombre() + " - Precio objetivo: $" + c.getPrecioObjetivo());
                
                double precio = servicioScrapper.obtenerPrecio(c.getUrl(), new ArrayList<>(c.getSelectores()));
                
                System.out.println("Precio obtenido: $" + precio + " para " + c.getNombre());

                RegistroPrecio reg = new RegistroPrecio(precio, LocalDateTime.now());
                c.agregarHistorial(reg);

                repReg.save(reg);
                repComps.save(c);

                System.out.println("Verificando condición: " + precio + " <= " + c.getPrecioObjetivo() + " = " + (precio <= c.getPrecioObjetivo()));

                if(precio <= c.getPrecioObjetivo()){
                    System.out.println("¡ALERTA! Condición cumplida para " + c.getNombre());
                    System.out.println("Precio actual: $" + precio + " <= Precio objetivo: $" + c.getPrecioObjetivo());
                } else {
                    System.out.println("No hay oferta para " + c.getNombre() + " - Precio: $" + precio);
                }
                
            } catch (Exception e) {
                System.err.println("Error monitoreando " + c.getNombre() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        System.out.println("Finalizó rutina de monitoreo de precios");
    }
}
