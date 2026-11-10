import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Validaciones val = new Validaciones();
        GestorTienda gestor = new GestorTienda();

        int op = -1;
        while (op != 0){
            System.out.println("Menú de opciones---Tienda:");
            System.out.println(" 1. Agregar área");
            System.out.println(" 2. Agregar producto a un área");
            System.out.println(" 3. Actualizar área");
            System.out.println(" 4. Actualizar producto");
            System.out.println(" 5. Eliminar área");
            System.out.println(" 6. Eliminar producto");
            System.out.println(" 7. Buscar producto");
            System.out.println(" 8. Mostrar tienda");
            System.out.println(" 9. Registrar devolución");
            System.out.println("10. Ver última devolución");
            System.out.println("11. Deshacer última devolución");
            System.out.println("12. Mostrar historial de devoluciones");
            System.out.println("13. Cliente llega a caja");
            System.out.println("14. Atender cliente");
            System.out.println("15. Ver siguiente cliente");
            System.out.println("16. Mostrar cola");
            System.out.println("17. Total de áreas con potencial");
            System.out.println("18. Ejecutar casos de prueba");
            System.out.println(" 0. Salir");
            op = val.leerEntero(in, "Elija una opción: ");
            
            switch (op){
                case 1:{
                    String codigo = val.leerCodigo(in,"Código del área: ");
                    String nombre = val.leerNombre(in,"Nombre del área: ");
                    if (gestor.agregarArea(codigo,nombre)){
                        System.out.println("Área agregada.");
                    } else{
                        System.out.println("No se pudo agregar: el código de área ya existe.");
                    }
                    break;
                }
                case 2:{
                    String codigoArea = val.leerCodigo(in,"Código del área: ");
                    if (!gestor.existeArea(codigoArea)){
                        System.out.println("El área no existe.");
                        break;
                    }
                    String nombre = val.leerNombre(in,"Nombre del producto: ");
                    String codigo = val.leerCodigo(in,"Código del producto: ");
                    double precio = val.leerPrecio(in,"Precio: ");
                    if (gestor.agregarProducto(codigoArea,nombre,codigo,precio)){
                        System.out.println("Producto agregado.");
                    } else{
                        System.out.println("No se pudo agregar: ya existe un producto con ese código en el área.");
                    }
                    break;
                }
                case 3:{
                    String codigoArea = val.leerCodigo(in,"Código del área: ");
                    String nuevoNombre = val.leerNombre(in,"Nuevo nombre: ");
                    if (gestor.actualizarArea(codigoArea, nuevoNombre)){
                        System.out.println("Área actualizada.");
                    } else{
                        System.out.println("El área no existe.");
                    }
                    break;
                }
                case 4:{
                    String codigoArea = val.leerCodigo(in,"Código del área: ");
                    String codigoProducto = val.leerCodigo(in,"Código del producto: ");
                    if (!gestor.existeArea(codigoArea)){
                        System.out.println("El área no existe.");
                        break;
                    }
                    if (!gestor.existeProducto(codigoArea,codigoProducto)) {
                        System.out.println("El producto no existe en esa área.");
                        break;
                    }
                    String nuevoNombre = val.leerNombre(in, "Nuevo nombre: ");
                    double nuevoPrecio = val.leerPrecio(in, "Nuevo precio: ");
                    if (gestor.actualizarProducto(codigoArea, codigoProducto, nuevoNombre, nuevoPrecio)){
                        System.out.println("Producto actualizado.");
                    } else{
                        System.out.println("No se pudo actualizar el producto.");
                    }
                    break;
                }
                case 5: {
                    String codigo = val.leerCodigo(in, "Código del área a eliminar: ");
                    if (gestor.eliminarArea(codigo)){
                        System.out.println("Área eliminada (con todos sus productos).");
                    } else{
                        System.out.println("El área no existe.");
                    }
                    break;
                }
                case 6:{
                    String codigoArea = val.leerCodigo(in,"Código del área: ");
                    String codigoProducto = val.leerCodigo(in,"Código del producto a eliminar: ");
                    if (gestor.eliminarProducto(codigoArea,codigoProducto)) {
                        System.out.println("Producto eliminado.");
                    } else{
                        System.out.println("Área o producto no encontrado.");
                    }
                    break;
                }
                case 7:{
                    String codigoArea = val.leerCodigo(in,"Código del área: ");
                    String codigoProducto = val.leerCodigo(in,"Código del producto: ");
                    NodoProducto p = gestor.buscarProducto(codigoArea, codigoProducto);
                    if (p == null){
                        System.out.println("Producto no encontrado.");
                    } else{
                        System.out.println("Encontrado -> Nombre: "+p.getNombre()+" | Código: "+p.getCodigo()+" | Precio: $"+p.getPrecio());
                    }
                    break;
                }
                case 8:
                    gestor.mostrarTienda();
                    break;
                case 9:{
                    String codigoArea = val.leerCodigo(in,"Código del área: ");
                    String codigoProducto = val.leerCodigo(in,"Código del producto: ");
                    String motivo = val.leerTexto(in,"Motivo de la devolución: ");
                    if (gestor.registrarDevolucion(codigoArea,codigoProducto,motivo)) {
                        System.out.println("Devolución registrada.");
                    } else {
                        System.out.println("No se pudo registrar: el producto no existe.");
                    }
                    break;
                }
                case 10:{
                    Devolucion d = gestor.ultimaDevolucion();
                    if (d == null){
                        System.out.println("No hay devoluciones registradas.");
                    } else{
                        System.out.println("Última devolución: "+d);
                    }
                    break;
                }
                case 11:{
                    Devolucion d = gestor.deshacerUltimaDevolucion();
                    if (d == null){
                        System.out.println("No hay devoluciones para deshacer.");
                    } else{
                        System.out.println("Devolución anulada: "+d);
                    }
                    break;
                }
                case 12:
                    gestor.mostrarHistorialDevoluciones();
                    break;
                case 13:{
                    String id = val.leerCodigo(in, "ID del cliente: ");
                    String nombre = val.leerNombre(in, "Nombre del cliente: ");
                    if (gestor.llegarCliente(id, nombre)) {
                        System.out.println("Cliente agregado a la cola.");
                    } else{
                        System.out.println("Datos inválidos.");
                    }
                    break;
                }
                case 14:{
                    Cliente c = gestor.atenderCliente();
                    if (c == null) {
                        System.out.println("No hay clientes en la cola.");
                    } else{
                        System.out.println("Atendiendo a: "+c);
                    }
                    break;
                }
                case 15:{
                    Cliente c = gestor.verSiguiente();
                    if (c == null) {
                        System.out.println("No hay clientes en la cola.");
                    } else {
                        System.out.println("Siguiente cliente: "+c);
                    }
                    break;
                }
                case 16:
                    gestor.mostrarCola();
                    break;
                case 17:
                    System.out.println("Áreas con potencial: "+gestor.contarAreasConPotencial());
                    break;
                case 18:
                    new CasosPrueba().ejecutar();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }   
}