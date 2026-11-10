# Gestión-tienda-Equipo3
# Ejercicio 3: Gestión de Comercialización e Inventario en Tienda

Proyecto de consola desarrollado en Java para administrar las áreas y los productos de una tienda, registrar devoluciones y organizar la atención de clientes en caja. La solución integra una multilista dinámica, una pila, una cola y un algoritmo recursivo para identificar áreas con potencial comercial.

## Integrantes del equipo

- Ariela Ivón Corrales Rodríguez
- Mélany Fernández Díaz
- Miriam Elena Pérez Hdez
- Julio César León Gato
- Ronal Hernández Castillo

## Problemática

La tienda necesita organizar su inventario por áreas y mantener en cada una los productos con su nombre, código y precio. También necesita registrar las devoluciones recientes y atender a los clientes en el orden en que llegan a caja.

Este programa permite gestionar esas tareas desde un menú de consola y cuenta con casos de prueba. Además, calcula cuántas áreas tienen potencial comercial: un área cuenta como potencial si más de la mitad de sus productos tiene un precio superior a 150 pesos. El programa trabaja en memoria; los datos no se conservan al cerrar la aplicación.

## Objetivos

- Modelar la relación Tienda → Áreas → Productos usando nodos enlazados dinámicamente.
- Gestionar las devoluciones y la atención de clientes con estructuras LIFO y FIFO.
- Aplicar recursividad para contar las áreas que cumplen el criterio de potencial.
- Verificar los comportamientos principales con casos positivos y negativos.

## Funcionalidades

Desde el menú de consola, el usuario puede:

- Agregar, actualizar, eliminar, buscar y mostrar áreas y productos.
- Registrar una devolución, consultar la última devolución, retirar la última del historial y mostrar el historial completo.
- Agregar clientes a la cola de caja, atender al siguiente, consultar quién sigue y mostrar la cola.
- Calcular el número de áreas con potencial comercial.
- Ejecutar los casos de prueba integrados.

Los códigos deben ser alfanuméricos y tener como máximo 10 caracteres; los nombres admiten letras y espacios; los precios deben ser mayores que cero. La validación se realiza antes de procesar las operaciones.

## Utilidad

El proyecto sirve como una aplicación académica de consola para demostrar cómo estructuras enlazadas pueden representar un inventario jerárquico y gestionar eventos operativos. Puede utilizarse para practicar programación orientada a objetos, listas dinámicas, pilas, colas, validación de datos y recursividad.

## Estructuras de datos y funcionamiento

### Multilista de inventario

- `Tienda` mantiene una lista enlazada de nodos `NodoArea`.
- Cada `NodoArea` conserva el código y el nombre del área, un enlace al primer producto y un enlace al área siguiente.
- Cada `NodoProducto` conserva el nombre, el código, el precio y un enlace al producto siguiente.
- Los productos se agregan dentro de su área; no se permite repetir el código de producto dentro de la misma área.

### Pila de devoluciones (LIFO)

`LinkedStack<Devolucion>` almacena los registros de devolución. La última devolución registrada es la primera que se consulta o se retira. La opción de deshacer retira el registro más reciente de la pila; no modifica el inventario del producto.

### Cola de clientes (FIFO)

`LinkedQueue<Cliente>` mantiene los clientes en orden de llegada. `atenderCliente()` retira al primero de la cola y `verSiguiente()` permite consultarlo sin retirarlo. La implementación es FIFO, sin prioridad ni caducidad por tiempo.

### Recursividad para áreas con potencial

En `Tienda`, los métodos recursivos recorren los productos para contar el total y los que tienen precio estrictamente superior a 150. Después recorren las áreas y cuentan aquellas para las que se cumple:

```text
productos con precio > 150 × 2 > total de productos del área
```

La comparación estricta significa que un área con exactamente el 50 % de productos por encima de 150 no cuenta como potencial. Las áreas vacías tampoco cuentan.

## Estructura del repositorio

```text
Repositorio/
├── README.md
├── Ejercicio3/
│   ├── CasosPrueba.java
│   ├── Cliente.java
│   ├── Devolucion.java
│   ├── GestorTienda.java
│   ├── IStack.java
│   ├── ITienda.java
│   ├── LinkedQueue.java
│   ├── LinkedStack.java
│   ├── Main.java
│   ├── Nodo.java
│   ├── NodoArea.java
│   ├── NodoProducto.java
│   ├── Queue.java
│   ├── Tienda.java
│   └── Validaciones.java
└── presentacion/
    └── Presentacion_Ejercicio3.pptx
```

[Abrir presentación del proyecto](./presentacion/Presentacion_Ejercicio3.pptx)

## Requisitos

- Git para clonar el repositorio.
- JDK instalado (Java 8 o posterior), con los comandos `java` y `javac` disponibles en la terminal.
- No se requieren bibliotecas externas para compilar y ejecutar el proyecto.

## Cómo comenzar

Ejecuta los comandos desde la carpeta raíz del proyecto.

### Windows (PowerShell)

```powershell
New-Item -ItemType Directory -Force bin | Out-Null
javac -encoding UTF-8 -d bin .\Ejercicio3\*.java
java -cp bin Main
```

### macOS y Linux (Terminal)

```sh
mkdir -p bin
javac -encoding UTF-8 -d bin Ejercicio3/*.java
java -cp bin Main
```

### Usar el menú

El programa es interactivo: escribe el número de la opción deseada y sigue las indicaciones que aparecen en la consola. La opción `18` ejecuta los casos de prueba integrados y la opción `0` termina la aplicación. Mantén abierta la terminal mientras lo usas y proporciona una respuesta cada vez que el programa la solicite.

## Casos de prueba

Los casos están implementados en `Ejercicio3/CasosPrueba.java` y se ejecutan desde el menú con la opción `18`. Al finalizar, se muestra un resumen de verificaciones correctas y fallidas.

| Grupo | Verificaciones | Qué se comprueba |
|---|---:|---|
| Multilista: operaciones positivas | 9 | Agregar áreas y productos, buscar un producto y actualizar áreas y productos. |
| Multilista: entradas y operaciones negativas | 15 | Códigos repetidos, vacíos, con símbolos, espacios o longitud excesiva; nombres inválidos; precio cero; área o producto inexistente. |
| Eliminación de áreas | 7 | Eliminar la cabeza, un área intermedia, la última y la única área; confirmar los elementos restantes y el resultado del conteo en una tienda vacía. |
| Eliminación de productos | 6 | Eliminar el primer producto, uno intermedio y el último; comprobar los productos restantes y eliminar un área con productos. |
| Recursividad | 4 | Tienda sin áreas, área vacía, exactamente 50 % de productos por encima del umbral y áreas que sí superan el 50 %. |
| Pila de devoluciones | 11 | Consultas y retiros con la pila vacía, registros válidos e inválidos, orden LIFO y conservación del historial al mostrarlo. |
| Cola de clientes | 15 | Consultas y atención con la cola vacía, validaciones, orden FIFO, consulta sin retirar, mostrar sin perder elementos y volver a insertar tras vaciarla. |
| **Total** | **67** | |

Para ejecutarlas, iniciar el programa, seleccionar `18`, esperar el resumen y luego seleccionar `0` para salir.

## Declaración sobre el uso de Inteligencia Artificial

Se utilizó Inteligencia Artificial como apoyo durante la revisión del proyecto, como apoyo en la realizacion de los casos de prueba de la opción 18 que controla el método ejecutar de la clase CasosPrueba y en la revision y mejora en la redacción de este README.
