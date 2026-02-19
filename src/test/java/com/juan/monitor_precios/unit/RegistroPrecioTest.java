package com.juan.monitor_precios.unit;

import com.juan.monitor_precios.entidades.RegistroPrecio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RegistroPrecioTest {

    private RegistroPrecio registroPrecio;
    private LocalDateTime fechaTest;

    @BeforeEach
    void setUp() {
        fechaTest = LocalDateTime.of(2024, 1, 15, 10, 30);
        registroPrecio = new RegistroPrecio(1500.75, fechaTest);
    }

    @Test
    void constructorConParametros_DebeCrearRegistroConDatosCorrectos() {
        // Assert
        assertEquals(1500.75, registroPrecio.getPrecio());
        assertEquals(fechaTest, registroPrecio.getFechaCaptura());
    }

    @Test
    void constructorVacio_DebeCrearRegistroVacio() {
        // Act
        RegistroPrecio registroVacio = new RegistroPrecio();
        
        // Assert
        assertNull(registroVacio.getId());
        assertNull(registroVacio.getPrecio());
        assertNull(registroVacio.getFechaCaptura());
    }

    @Test
    void settersAndGetters_DebeFuncionarCorrectamente() {
        // Arrange
        Long nuevoId = 5L;
        Double nuevoPrecio = 2500.99;
        LocalDateTime nuevaFecha = LocalDateTime.of(2024, 2, 20, 14, 45);
        
        // Act
        registroPrecio.setId(nuevoId);
        registroPrecio.setPrecio(nuevoPrecio);
        registroPrecio.setFechaCaptura(nuevaFecha);
        
        // Assert
        assertEquals(nuevoId, registroPrecio.getId());
        assertEquals(nuevoPrecio, registroPrecio.getPrecio());
        assertEquals(nuevaFecha, registroPrecio.getFechaCaptura());
    }

    @Test
    void precio_DebeAceptarDiferentesValoresNumericos() {
        // Test con diferentes valores de precio
        RegistroPrecio registroEntero = new RegistroPrecio(1000, LocalDateTime.now());
        RegistroPrecio registroDecimal = new RegistroPrecio(999.99, LocalDateTime.now());
        RegistroPrecio registroCero = new RegistroPrecio(0.0, LocalDateTime.now());
        
        assertEquals(1000.0, registroEntero.getPrecio());
        assertEquals(999.99, registroDecimal.getPrecio());
        assertEquals(0.0, registroCero.getPrecio());
    }

    @Test
    void fechaCaptura_DebeManejarFechasCorrectamente() {
        // Arrange
        LocalDateTime fechaPasada = LocalDateTime.now().minusDays(1);
        LocalDateTime fechaFutura = LocalDateTime.now().plusDays(1);
        
        // Act
        RegistroPrecio registroPasado = new RegistroPrecio(100.0, fechaPasada);
        RegistroPrecio registroFuturo = new RegistroPrecio(200.0, fechaFutura);
        
        // Assert
        assertEquals(fechaPasada, registroPasado.getFechaCaptura());
        assertEquals(fechaFutura, registroFuturo.getFechaCaptura());
        assertTrue(registroPasado.getFechaCaptura().isBefore(LocalDateTime.now()));
        assertTrue(registroFuturo.getFechaCaptura().isAfter(LocalDateTime.now()));
    }
}
