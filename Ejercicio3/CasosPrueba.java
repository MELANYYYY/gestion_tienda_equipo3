public class CasosPrueba {
    private int correctos;
    private int fallidos;

    private void verificar(String descripcion,Object esperado,Object obtenido){
        boolean ok = String.valueOf(esperado).equals(String.valueOf(obtenido));
        if (ok) {
            correctos++;
        } else {
            fallidos++;
        }
        System.out.println("[" +(ok ? "OK" : "FALLO")+ "] " +descripcion+" | esperado: "+esperado+" | obtenido: "+obtenido);
    }

    private void titulo(String texto) {
        System.out.println();
        System.out.println(texto);
    }

    public void ejecutar() {
        correctos = 0;
        fallidos = 0;

        titulo("MULTILISTA - casos positivos");
        GestorTienda g = new GestorTienda();

        verificar("Agregar área A1 válida",true,g.agregarArea("A1","Zapatos"));
        verificar("Agregar área A2 válida",true,g.agregarArea("A2","Ropa"));
        verificar("Agregar producto P1 a A1",true,g.agregarProducto("A1","Tenis","P1", 120));
        verificar("Agregar producto P2 a A1",true,g.agregarProducto("A1","Botas","P2", 200));
        verificar("Buscar producto existente P1",true,g.buscarProducto("A1","P1") != null);
        verificar("Actualizar nombre del área A2", true,g.actualizarArea("A2","Ropa deportiva"));
        verificar("El área A2 cambió de nombre", "Ropa deportiva",g.buscarArea("A2").getNombre());
        verificar("Actualizar producto P1 (nombre y precio)", true,g.actualizarProducto("A1", "P1", "Tenis nuevos", 140));
        verificar("El producto P1 cambió de precio", 140.0,g.buscarProducto("A1", "P1").getPrecio());

        titulo("MULTILISTA - casos negativos");
        verificar("Código de área repetido",false,g.agregarArea("A1","Otra"));
        verificar("Código de producto repetido en la misma área",false,g.agregarProducto("A1","Otro","P1",50));
        verificar("Precio 0",false,g.agregarProducto("A1","Gratis","P9", 0));
        verificar("Código de área vacío",false,g.agregarArea("", "X"));
        verificar("Nombre de área vacío",false,g.agregarArea("A9", "   "));
        verificar("Código de área con espacios", false, g.agregarArea("A 9","X"));
        verificar("Código de área con símbolo",false, g.agregarArea("A@9","X"));
        verificar("Código de área de más de 10 caracteres",false,g.agregarArea("ABCDEFGHIJK","X"));
        verificar("Nombre de área con símbolo",false, g.agregarArea("A9", "Ropa#"));
        verificar("Nombre de producto vacío",false, g.agregarProducto("A1", "   ","P9", 10));
        verificar("Agregar producto a área inexistente",false, g.agregarProducto("ZZ","X","P9", 10));
        verificar("Buscar producto inexistente",null, g.buscarProducto("A1","NOEXISTE"));
        verificar("Actualizar área inexistente",false, g.actualizarArea("ZZ","X"));
        verificar("Eliminar área inexistente",false, g.eliminarArea("ZZ"));
        verificar("Eliminar producto inexistente",false, g.eliminarProducto("A1","NOEXISTE"));

        titulo("MULTILISTA - eliminar áreas");
        GestorTienda ga = new GestorTienda();
        ga.agregarArea("B1", "Dos");
        ga.agregarArea("B2", "Dos B");
        ga.agregarArea("B3","Tres");
        ga.agregarArea("B4","Cuatro");
        System.out.println("Lista inicial:");
        ga.mostrarTienda();
        verificar("Eliminar la cabeza (B1)",true, ga.eliminarArea("B1"));
        verificar("B1 ya no existe",false, ga.existeArea("B1"));
        verificar("Eliminar una intermedia (B3)",true, ga.eliminarArea("B3"));
        verificar("B2 y B4 siguen existiendo",true, ga.existeArea("B2") && ga.existeArea("B4"));
        verificar("Eliminar la última (B4)",true, ga.eliminarArea("B4"));
        verificar("Eliminar la única área (B2)",true, ga.eliminarArea("B2"));
        verificar("Con la tienda vacía, el recursivo da",0, ga.contarAreasConPotencial());
        ga.mostrarTienda();
        
        titulo("MULTILISTA - eliminar productos");
        GestorTienda gp = new GestorTienda();
        gp.agregarArea("C1", "Area C1");
        gp.agregarProducto("C1", "Uno","P1", 10);
        gp.agregarProducto("C1", "Dos","P2", 20);
        gp.agregarProducto("C1", "Tres","P3", 30);
        gp.agregarProducto("C1", "Cuatro","P4", 40);
        gp.mostrarTienda();
        verificar("Eliminar el primer producto (P1)",true, gp.eliminarProducto("C1", "P1"));
        verificar("Eliminar uno intermedio (P3)",true, gp.eliminarProducto("C1", "P3"));
        verificar("Eliminar el último (P4)",true, gp.eliminarProducto("C1", "P4"));
        verificar("P2 sigue existiendo",true, gp.existeProducto("C1", "P2"));
        gp.mostrarTienda();
        verificar("Eliminar el área con productos (C1)",true, gp.eliminarArea("C1"));
        verificar("Sus productos ya no se encuentran",null, gp.buscarProducto("C1", "P2"));

        titulo("RECURSIVO - áreas con potencial");
        GestorTienda gr = new GestorTienda();
        verificar("Tienda sin áreas",0, gr.contarAreasConPotencial());

        gr.agregarArea("A", "Area A");
        gr.agregarProducto("A","a1", "a1", 200);
        gr.agregarProducto("A","a2", "a2", 160);
        gr.agregarProducto("A","a3", "a3", 100);
        gr.agregarProducto("A","a4", "a4", 50);
        verificar("Área A (2 caros de 4, 50% exacto) -> sin potencial", 0, gr.contarAreasConPotencial());

        gr.agregarArea("B", "Area B");
        gr.agregarProducto("B","b1", "b1", 200);
        gr.agregarProducto("B","b2", "b2", 300);
        gr.agregarProducto("B","b3", "b3", 100);
        verificar("Con A y B (B tiene 2 de 3)", 1, gr.contarAreasConPotencial());

        gr.agregarArea("C", "Area C");
        gr.agregarProducto("C","c1", "c1", 151);
        gr.agregarProducto("C","c2", "c2", 150);
        gr.agregarArea("D","Area D vacia");
        gr.agregarArea("E","Area E");
        gr.agregarProducto("E","e1", "e1", 151);
        verificar("Con A, B, C, D y E (solo B y E tienen potencial)", 2, gr.contarAreasConPotencial());

        titulo("PILA - devoluciones");
        GestorTienda gd = new GestorTienda();
        gd.mostrarHistorialDevoluciones();
        verificar("Top con la pila vacía",null, gd.ultimaDevolucion());
        verificar("Pop con la pila vacía",null, gd.deshacerUltimaDevolucion());

        gd.agregarArea("A1", "Zapatos");
        gd.agregarProducto("A1", "Tenis","P1", 120);
        gd.agregarProducto("A1", "Botas","P2", 200);

        verificar("Registrar devolución de P1",true, gd.registrarDevolucion("A1", "P1", "talla incorrecta"));
        verificar("Registrar devolución de P2",true, gd.registrarDevolucion("A1", "P2", "defecto de fábrica"));
        verificar("Top es la devolución de P2 (la última)","P2", gd.ultimaDevolucion().getCodigoProducto());
        gd.mostrarHistorialDevoluciones();
        verificar("Mostrar el historial no pierde datos (Top sigue siendo P2)", "P2",gd.ultimaDevolucion().getCodigoProducto());
        verificar("Devolución de producto inexistente", false, gd.registrarDevolucion("A1", "NOEXISTE", "motivo"));
        verificar("Devolución con motivo vacío", false, gd.registrarDevolucion("A1", "P1", "  "));
        verificar("Pop devuelve la ÚLTIMA (LIFO): P2", "P2", gd.deshacerUltimaDevolucion().getCodigoProducto());
        verificar("Pop siguiente devuelve P1", "P1", gd.deshacerUltimaDevolucion().getCodigoProducto());
        verificar("Pop con la pila ya vacía", null, gd.deshacerUltimaDevolucion());

        titulo("COLA - clientes");
        GestorTienda gc = new GestorTienda();
        gc.mostrarCola();
        verificar("Peek con la cola vacía",null, gc.verSiguiente());
        verificar("Poll con la cola vacía",null, gc.atenderCliente());
        verificar("La cola está vacía",true, gc.colaVacia());
        verificar("Cliente con id vacío",false, gc.llegarCliente("", "Ana"));
        verificar("Cliente con símbolo en el nombre", false, gc.llegarCliente("C1", "Ana$"));
        verificar("Llega C1",true, gc.llegarCliente("C1", "Ana"));
        verificar("Llega C2",true, gc.llegarCliente("C2", "Luis"));
        verificar("Llega C3",true, gc.llegarCliente("C3", "Rosa"));
        verificar("Peek devuelve al primero (C1)", "C1", gc.verSiguiente().getId());
        gc.mostrarCola();
        verificar("Mostrar la cola no pierde datos (peek sigue en C1)", "C1", gc.verSiguiente().getId());
        verificar("Se atiende primero C1 (FIFO)","C1", gc.atenderCliente().getId());
        verificar("Se atiende después C2","C2", gc.atenderCliente().getId());
        verificar("Se atiende al final C3","C3", gc.atenderCliente().getId());
        verificar("Llega C4 tras vaciar la cola",true, gc.llegarCliente("C4", "Marta"));
        verificar("Peek tras vaciar y volver a agregar","C4", gc.verSiguiente().getId());

        System.out.println();
        System.out.println("RESUMEN: "+correctos+" correctos, " + fallidos + " fallidos.");
    }
}