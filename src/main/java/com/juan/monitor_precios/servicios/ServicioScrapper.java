package com.juan.monitor_precios.servicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.ArrayList;

@Service
public class ServicioScrapper {

    public Double obtenerPrecio(String url, ArrayList<String> selectores) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.setCapability("browserVersion", "145");

        WebDriver driver = new ChromeDriver(options);

        try {
            System.out.println("Navegando a: " + url);
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            WebElement elemento = null;
            String selectorUsado = null;

            for (String selector : selectores) {
                try {
                    System.out.println("Intentando selector: " + selector);
                    
                    elemento = wait.until(d -> {
                        try {
                            WebElement el = d.findElement(By.cssSelector(selector));
                            String val = el.getText();
                            System.out.println("Texto encontrado (getText): '" + val + "'");
                            if (val.isEmpty()) val = el.getAttribute("innerText");
                            System.out.println("Texto encontrado (innerText): '" + val + "'");
                            if (val == null || val.isEmpty()) val = el.getAttribute("textContent");
                            System.out.println("Texto encontrado (textContent): '" + val + "'");

                            return (val != null && !val.trim().isEmpty()) ? el : null;
                        } catch (Exception e) {
                            System.out.println("Error con selector " + selector + ": " + e.getMessage());
                            return null;
                        }
                    });
                    
                    if (elemento != null) {
                        selectorUsado = selector;
                        System.out.println("Selector exitoso: " + selector);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Falló selector " + selector + ": " + e.getMessage());
                    continue;
                }
            }
            
            if (elemento == null) {
                System.err.println("No se encontró ningún elemento de precio con los selectores probados");
                return 0.0;
            }

            System.out.println("Elemento encontrado exitosamente con selector: " + selectorUsado);

            String texto = elemento.getText().trim();
            System.out.println("Texto original: '" + texto + "'");

            if (texto.isEmpty()) {
                texto = elemento.getAttribute("innerText");
                System.out.println("Usando innerText: '" + texto + "'");
            }

            String soloNumeros = texto.replaceAll("[^\\d.]", "");
            System.out.println("Solo números y puntos: '" + soloNumeros + "'");

            soloNumeros = soloNumeros.replace(",", ".");
            System.out.println("Después de reemplazar comas: '" + soloNumeros + "'");

            String[] partes = soloNumeros.split("\\.");
            if (partes.length > 2) {
                soloNumeros = String.join("", java.util.Arrays.copyOf(partes, partes.length - 1)) + "." + partes[partes.length - 1];
            }
            System.out.println("Valor final para parsear: '" + soloNumeros + "'");

            return Double.parseDouble(soloNumeros);

        } catch (Exception e) {
            System.err.println("Error en Selenium al capturar precio: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

}
