package com.juan.monitor_precios.unit;

import com.juan.monitor_precios.servicios.ServicioScrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ServicioScrapperTest {

    private ServicioScrapper servicioScrapper;

    @BeforeEach
    void setUp() {
        servicioScrapper = new ServicioScrapper();
    }

    @Test
    void obtenerPrecio_DebeRetornarPrecioCorrecto_CuandoElementoExiste() throws IOException {
        // Arrange
        String url = "https://www.amazon.com/-/es/Intel%C2%AE-i7-14700KF-procesador-escritorio-n%C3%BAcleos/dp/B0CGJC178L/ref=sr_1_2?crid=1RY1GMPP51YE&dib=eyJ2IjoiMSJ9.UEY5CWdiocu2CSJ-EvbPL8i3Fuw3z5O16O0timT1vbDlNeWr9AZ160Ra4msa82O-qvgHi-TghHlCDt1pytB4_CMD19f2c9pwggkD-EM2Qxy-PJR0dJ83QbNIBsaNbBhk-qQyetbp7oeS7qubnux67Wax98GWVX4x9Z0K8QJLSO3CRvIC1vytRkW5uVsQI08ssfKmf4bDZlay1J_pzLa_p4OYSMJgwarrcIEAj9gOk1c.Avfu6xTuxhpx5ysHdmDKRoziMnfEl1AS62we3sUyFUY&dib_tag=se&keywords=i7+14700k&qid=1771524555&sprefix=i7+1%2Caps%2C397&sr=8-2";

        ArrayList<String> selectoresAmazon = new ArrayList<>();

        selectoresAmazon.add(".a-price-whole");
        selectoresAmazon.add(".a-price .a-offscreen");
        selectoresAmazon.add("[data-a-color='price'] .a-offscreen");
        selectoresAmazon.add(".a-price.a-text-price.a-size-medium.apexPriceToPay");
        selectoresAmazon.add(".a-price.a-text-price.apexPriceToPay");
        selectoresAmazon.add(".a-price-fraction");
        selectoresAmazon.add("[class*='a-price']");
        selectoresAmazon.add("[class*='price']");
        selectoresAmazon.add(".tw-text-price");
        selectoresAmazon.add("span[class*='tw:text-price']");

        double precioEsperado = 488908.0;

        double resultado = servicioScrapper.obtenerPrecio(url, selectoresAmazon);
        
        assertEquals(precioEsperado, resultado);
    }

    /*@Test
    void obtenerPrecio_DebeRetornarNull_CuandoElementoNoExiste() throws IOException {
        // Arrange
        String url = "https://ejemplo.com/producto";
        String selector = ".price-non-existent";

    }

    @Test
    void obtenerPrecio_DebeRetornarNull_CuandoHayIOException() throws IOException {
        // Arrange
        String url = "https://ejemplo.com/producto";
        String selector = ".price-tag";
        

    }

    @Test
    void limpiarPrecio_DebeRetornarDoubleCorrecto_ConDiferentesFormatos() {

    }*/
}
