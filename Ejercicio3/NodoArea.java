public class NodoArea {
    private String codigo;
    private String nombre;
    private NodoProducto productos; 
    private NodoArea siguiente;

    public NodoArea(String codigo,String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.productos = null;
        this.siguiente = null;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NodoProducto getProductos() {
        return productos;
    }

    public void setProductos(NodoProducto productos) {
        this.productos = productos;
    }

    public NodoArea getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoArea siguiente) {
        this.siguiente = siguiente;
    }
}
