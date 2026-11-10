public class GestorTienda {
    private Tienda tienda;
    private LinkedStack<Devolucion> devoluciones;
    private LinkedQueue<Cliente> cola;
    private Validaciones val;

    public GestorTienda() {
        this.tienda = new Tienda();
        this.devoluciones = new LinkedStack<>();
        this.cola = new LinkedQueue<>();
        this.val = new Validaciones();
    }

    public boolean agregarArea(String codigo,String nombre){
        if (!val.esCodigoValido(codigo) || !val.esNombreValido(nombre)) {
            return false;
        }
        return tienda.agregarArea(codigo,nombre);
    }

    public boolean agregarProducto(String codigoArea,String nombre,String codigo,double precio) {
        if (!val.esCodigoValido(codigoArea) || !val.esNombreValido(nombre) || !val.esCodigoValido(codigo) || !val.esPrecioValido(precio)) {
            return false;
        }
        return tienda.agregarProducto(codigoArea,nombre,codigo,precio);
    }

    public boolean actualizarArea(String codigoArea,String nuevoNombre) {
        if (!val.esCodigoValido(codigoArea) || !val.esNombreValido(nuevoNombre)) {
            return false;
        }
        return tienda.actualizarArea(codigoArea,nuevoNombre);
    }

    public boolean actualizarProducto(String codigoArea,String codigoProducto,String nuevoNombre,double nuevoPrecio) {
        if (!val.esCodigoValido(codigoArea) || !val.esCodigoValido(codigoProducto) || !val.esNombreValido(nuevoNombre) || !val.esPrecioValido(nuevoPrecio)) {
            return false;
        }
        return tienda.actualizarProducto(codigoArea,codigoProducto,nuevoNombre,nuevoPrecio);
    }

    public boolean eliminarArea(String codigo){
        if (!val.esCodigoValido(codigo)) {
            return false;
        }
        return tienda.eliminarArea(codigo);
    }

    public boolean eliminarProducto(String codigoArea, String codigoProducto) {
        if (!val.esCodigoValido(codigoArea) || !val.esCodigoValido(codigoProducto)) {
            return false;
        }
        return tienda.eliminarProducto(codigoArea, codigoProducto);
    }

    public boolean existeArea(String codigo) {
        return tienda.buscarArea(codigo) != null;
    }

    public boolean existeProducto(String codigoArea,String codigoProducto) {
        return tienda.buscarProducto(codigoArea,codigoProducto) != null;
    }

    public NodoArea buscarArea(String codigo) {
        return tienda.buscarArea(codigo);
    }

    public NodoProducto buscarProducto(String codigoArea,String codigoProducto) {
        return tienda.buscarProducto(codigoArea,codigoProducto);
    }

    public void mostrarTienda() {
        tienda.imprimir();
    }

    public int contarAreasConPotencial() {
        return tienda.contarAreasConPotencial();
    }

    public boolean registrarDevolucion(String codigoArea,String codigoProducto,String motivo) {
        if (!val.esTextoValido(motivo)) {
            return false;
        }
        NodoProducto p = tienda.buscarProducto(codigoArea,codigoProducto);
        if (p == null) {
            return false;
        }
        Devolucion d = new Devolucion(codigoArea,codigoProducto,p.getNombre(),p.getPrecio(),motivo.trim());
        devoluciones.push(d);
        return true;
    }

    public Devolucion ultimaDevolucion() {
        return devoluciones.top();
    }

    public Devolucion deshacerUltimaDevolucion() {
        return devoluciones.pop();
    }

    public void mostrarHistorialDevoluciones() {
        if (devoluciones.isEmpty()) {
            System.out.println("No hay devoluciones registradas.");
            return;
        }
        LinkedStack<Devolucion> temp = new LinkedStack<>();
        while (!devoluciones.isEmpty()) {
            Devolucion d = devoluciones.pop();
            System.out.println(d);
            temp.push(d);
        }
        while (!temp.isEmpty()) {
            devoluciones.push(temp.pop());
        }
    }

    public boolean llegarCliente(String id,String nombre) {
        if (!val.esCodigoValido(id) || !val.esNombreValido(nombre)) {
        // FIN CAMBIO
            return false;
        }
        cola.add(new Cliente(id,nombre.trim()));
        return true;
    }

    public Cliente atenderCliente() {
        return cola.poll();
    }

    public Cliente verSiguiente() {
        return cola.peek();
    }

    public boolean colaVacia() {
        return cola.isEmpty();
    }

    public void mostrarCola() {
        if (cola.isEmpty()) {
            System.out.println("La cola de clientes está vacía.");
            return;
        }
        LinkedQueue<Cliente> temp = new LinkedQueue<>();
        while (!cola.isEmpty()) {
            Cliente c = cola.poll();
            System.out.println(c);
            temp.add(c);
        }
        while (!temp.isEmpty()) {
            cola.add(temp.poll());
        }
    }
}