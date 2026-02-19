# Monitor de Precios de Componentes PC

## Descripción

Aplicación Spring Boot que monitorea automáticamente los precios de componentes de computadora en diferentes tiendas online y alerta cuando encuentra ofertas.

## Funcionalidades

- **Monitoreo Automático**: Revisa precios cada 1 minuto
- **Múltiples Tiendas**: Amazon, eBay, Compragamer, PcComponentes, Newegg, Best Buy //depende de tu input de datos
- **Alertas de Precios**: Notificación cuando el precio es igual o menor al objetivo
- **Historial de Precios**: Registra todos los cambios de precios
- **Web Scraping**: Extrae precios usando selectores CSS

## Arquitectura

- **Backend**: Spring Boot con JPA/Hibernate
- **Base de Datos**: MySQL
- **Web Scraping**: Selenuim
- **Programación de Tareas**: Spring Scheduling

## Endpoints API

- `POST /api/componentes/addComponente` - Agregar nuevo componente
- `GET /api/componentes/{id}` - Obtener componente por ID
- `DELETE /api/componentes/{id}` - Eliminar componente
- `GET /api/componentes/ejecutarMonitoreo/{id}` - Ejecutar monitoreo manual

La aplicación iniciará el monitoreo automático cada minuto (podes modificar el tiempo en la anotacion spring) y mostrará alertas en consola cuando encuentre ofertas.

## Estructura de Datos

- **Componente**: ID, nombre, tienda, URL, selectores CSS, precio objetivo
- **RegistroPrecio**: ID, precio, fecha/hora, componente asociado
- **Historial**: Lista de registros de precios por componente

## Ejemplos JSON

[

{
"nombre": "NVIDIA GeForce RTX 5060 Ti",
"tienda": "Amazon",
"precioObjetivo": 450000.0,
"url": "https://www.amazon.com/s?k=RTX+5060+Ti",
"selectores": [
".a-price-whole",
".a-price .a-offscreen",
".a-price.a-text-price.a-size-medium.apexPriceToPay",
".tw-text-price",
"span[class*='tw:text-price']"
]
},

{
"nombre": "Corsair Vengeance DDR5 32GB (2x16GB) 6000MHz",
"tienda": "Amazon",
"precioObjetivo": 110000.0,
"url": "https://www.amazon.com/Corsair-Vengeance-6000MHz-Optimizado-C36-Black/dp/B0BPTKD797",
"selectores": [
".a-price-whole",
".a-price .a-offscreen",
".a-price.a-text-price.a-size-medium.apexPriceToPay",
".tw-text-price",
"span[class*='tw:text-price']"
]
},

{
"nombre": "Samsung 990 Pro 2TB NVMe M.2",
"tienda": "Amazon",
"precioObjetivo": 170000.0,
"url": "https://www.amazon.com/Samsung-990-PRO-Internal-MZ-V9P2T0B/dp/B0BHJJ9Y77",
"selectores": [
".a-price-whole",
".a-price .a-offscreen",
".a-price.a-text-price.a-size-medium.apexPriceToPay",
".tw-text-price",
"span[class*='tw:text-price']"
]
},

{
"nombre": "Intel Core i7-14700K",
"tienda": "Amazon",
"precioObjetivo": 501000.0,
"url": "https://www.amazon.com/-/es/Intel%C2%AE-i7-14700KF-procesador-escritorio-n%C3%BAcleos/dp/B0CGJC178L/ref=sr_1_2?crid=1RY1GMPP51YE&dib=eyJ2IjoiMSJ9.UEY5CWdiocu2CSJ-EvbPL8i3Fuw3z5O16O0timT1vbDlNeWr9AZ160Ra4msa82O-qvgHi-TghHlCDt1pytB4_CMD19f2c9pwggkD-EM2Qxy-PJR0dJ83QbNIBsaNbBhk-qQyetbp7oeS7qubnux67Wax98GWVX4x9Z0K8QJLSO3CRvIC1vytRkW5uVsQI08ssfKmf4bDZlay1J_pzLa_p4OYSMJgwarrcIEAj9gOk1c.Avfu6xTuxhpx5ysHdmDKRoziMnfEl1AS62we3sUyFUY&dib_tag=se&keywords=i7+14700k&qid=1771524555&sprefix=i7+1%2Caps%2C397&sr=8-2",
"selectores": [
".a-price-whole",
".a-price .a-offscreen",
"[data-a-color='price'] .a-offscreen",
".a-price.a-text-price.a-size-medium.apexPriceToPay",
".a-price.a-text-price.apexPriceToPay",
".a-price-fraction",
"[class*='a-price']",
"[class*='price']",
".tw-text-price",
"span[class*='tw:text-price']"
]
}

]