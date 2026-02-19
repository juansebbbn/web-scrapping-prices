import com.juan.monitor_precios.servicios.ServicioScrapper;

public class DebugTest {
    public static void main(String[] args) {
        ServicioScrapper servicio = new ServicioScrapper();
        
        String url = "https://www.amazon.com/-/es/Intel%C2%AE-i7-14700KF-procesador-escritorio-n%C3%BAcleos/dp/B0CGJC178L/ref=sr_1_2?crid=1RY1GMPP51YE&dib=eyJ2IjoiMSJ9.UEY5CWdiocu2CSJ-EvbPL8i3Fuw3z5O16O0timT1vbDlNeWr9AZ160Ra4msa82O-qvgHi-TghHlCDt1pytB4_CMD19f2c9pwggkD-EM2Qxy-PJR0dJ83QbNIBsaNbBhk-qQyetbp7oeS7qubnux67Wax98GWVX4x9Z0K8QJLSO3CRvIC1vytRkW5uVsQI08ssfKmf4bDZlay1J_pzLa_p4OYSMJgwarrcIEAj9gOk1c.Avfu6xTuxhpx5ysHdmDKRoziMnfEl1AS62we3sUyFUY&dib_tag=se&keywords=i7+14700k&qid=1771524555&sprefix=i7+1%2Caps%2C397&sr=8-2";
        String selector = "a-price-whole";
        
        System.out.println("Iniciando prueba...");
        try {
            double precio = servicio.obtenerPrecio(url, selector);
            System.out.println("Precio obtenido: " + precio);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
