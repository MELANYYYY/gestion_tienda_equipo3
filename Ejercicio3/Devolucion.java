public class Devolucion {
    private String codigoArea;
    private String codProducto;
    private String nombProd;
    private double precio;
    private String motivo;

    public Devolucion(String codigoArea, String codigoProducto, String nombreProducto,double precio, String motivo) {
        this.codigoArea = codigoArea;
        this.codProducto = codigoProducto;
        this.nombProd = nombreProducto;
        this.precio = precio;
        this.motivo = motivo;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public String getCodigoProducto() {
        return codProducto;
    }

    public String getNombreProducto() {
        return nombProd;
    }

    public double getPrecio() {
        return precio;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "Área: "+codigoArea+" | Producto: "+codProducto+" ("+nombProd+") | $"+precio+" | Motivo: "+motivo;
    }
}