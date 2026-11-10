public class Tienda implements ITienda {
    private NodoArea cabeza;

    public Tienda() {
        this.cabeza = null;
    }

    @Override
    public boolean agregarArea(String codigo,String nombre){
        if (buscarArea(codigo) != null) {
            return false;
        }
        NodoArea nuevo = new NodoArea(codigo,nombre);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoArea actual = cabeza;
            while (actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        return true;
    }

    @Override
    public boolean agregarProducto(String codigoArea,String nombre,String codigo,double precio){
        NodoArea area = buscarArea(codigoArea);
        if (area == null) {
            return false; 
        }
        if (buscarProducto(codigoArea,codigo) != null) {
            return false; 
        }
        NodoProducto nuevoNodo = new NodoProducto(nombre,codigo,precio);
        if (area.getProductos() == null) {
            area.setProductos(nuevoNodo);
        } else {
            NodoProducto temp = area.getProductos();
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
        return true;
    }

    @Override
    public NodoProducto obtenerProductos(String codigoArea){
        NodoArea area = buscarArea(codigoArea);
        if (area == null){
            return null;
        }
        return area.getProductos();
    }

    @Override
    public NodoArea buscarArea(String codigo) {
        NodoArea actual = cabeza;
        while (actual != null){
            if (actual.getCodigo().equals(codigo)){
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public NodoProducto buscarProducto(String codigoArea,String codigoProducto) {
        NodoArea area = buscarArea(codigoArea);
        if (area == null){
            return null;
        }
        NodoProducto actual = area.getProductos();
        while (actual != null){
            if (actual.getCodigo().equals(codigoProducto)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public boolean actualizarArea(String codigoArea,String nuevoNombre) {
        NodoArea area = buscarArea(codigoArea);
        if (area == null) {
            return false;
        }
        area.setNombre(nuevoNombre);
        return true;
    }

    @Override
    public boolean actualizarProducto(String codigoArea,String codigoProducto,String nuevoNombre,double nuevoPrecio) {
        NodoProducto producto = buscarProducto(codigoArea, codigoProducto);
        if (producto == null){
            return false;
        }
        producto.setNombre(nuevoNombre);
        producto.setPrecio(nuevoPrecio);
        return true;
    }

    @Override 
    public boolean eliminarArea(String codigo){
        if (cabeza == null){
            return false; 
        }
        if (cabeza.getCodigo().equals(codigo)){
            cabeza = cabeza.getSiguiente(); 
            return true;
        }
        NodoArea anterior = cabeza;
        while (anterior.getSiguiente() != null){
            if (anterior.getSiguiente().getCodigo().equals(codigo)){
                anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
                return true;
            }
            anterior = anterior.getSiguiente();
        }
        return false; 
    }

    @Override
    public boolean eliminarProducto(String codigoArea,String codigoProducto){
        NodoArea area = buscarArea(codigoArea);
        if (area == null){
            return false; 
        }
        if (area.getProductos() == null){
            return false; 
        }
        if (area.getProductos().getCodigo().equals(codigoProducto)) {
            area.setProductos(area.getProductos().getSiguiente()); 
            return true;
        }
        NodoProducto anterior = area.getProductos();
        while (anterior.getSiguiente() != null){
            if (anterior.getSiguiente().getCodigo().equals(codigoProducto)) {
                anterior.setSiguiente(anterior.getSiguiente().getSiguiente()); // otra posición
                return true;
            }
            anterior = anterior.getSiguiente();
        }
        return false; 
    }

    @Override
    public void imprimir(){
        if (cabeza == null){
            System.out.println("La tienda no tiene áreas.");
            return;
        }
        NodoArea actualArea = cabeza;
        while (actualArea != null){
            System.out.print("Área ["+actualArea.getCodigo() + "] "+actualArea.getNombre()+": ");
            NodoProducto actualProducto = actualArea.getProductos();
            if (actualProducto == null) {
                System.out.print("No hay productos en esta área.");
            }
            while (actualProducto != null) {
                System.out.print("("+actualProducto.getNombre() + ", "+actualProducto.getCodigo()+", $"+actualProducto.getPrecio()+") -> ");
                actualProducto = actualProducto.getSiguiente();
            }
            System.out.println();
            actualArea = actualArea.getSiguiente();
        }
    }
    
    private int contarProductos(NodoProducto actual){
        if (actual == null){
            return 0;
        }else{
            return 1 + contarProductos(actual.getSiguiente());
        }
        
    }

    private int contarProductosCaros(NodoProducto actual){
        if (actual == null){
            return 0;
        }
        int resto = contarProductosCaros(actual.getSiguiente());
        if (actual.getPrecio() > 150) {
            return 1 + resto;
        } else {
            return resto;
        }
    }

    private boolean areaTienePotencial(NodoArea area){
        if (area == null) {
            return false;
        }
        int cantProd = contarProductos(area.getProductos());
        int cantCaros = contarProductosCaros(area.getProductos());
        return cantProd>0 && cantCaros*2 > cantProd;
    }

    private int contarAreasConPotencial(NodoArea actual) {
        if (actual == null) {
            return 0;
        }
        int resto = contarAreasConPotencial(actual.getSiguiente());
        if (areaTienePotencial(actual)) {
            return 1 + resto;
        } else {
            return resto;
        }
    }
    
    @Override 
    public int contarAreasConPotencial() {
        return contarAreasConPotencial(cabeza);
    }
}
