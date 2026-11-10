public interface ITienda {
    boolean agregarArea(String codigo,String nombre);
    boolean agregarProducto(String codigoArea,String nombre, String codigo, double precio);
    NodoProducto obtenerProductos(String codigoArea);
    NodoArea buscarArea(String codigo);                                   
    NodoProducto buscarProducto(String codigoArea, String codigoProducto); 
    boolean actualizarArea(String codigoArea, String nuevoNombre);
    boolean actualizarProducto(String codigoArea, String codigoProducto, String nuevoNombre, double nuevoPrecio);
    boolean eliminarArea(String codigo);
    boolean eliminarProducto(String codigoArea, String codigoProducto);
    void imprimir();
    int contarAreasConPotencial();
}