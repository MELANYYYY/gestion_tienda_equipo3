import java.util.Scanner;

public class Validaciones {
    private static final int MAX_COD = 10;

    public boolean esTextoValido(String texto){
        return texto != null && !texto.trim().isEmpty();
    }

    public boolean esCodigoValido(String texto){
        return esTextoValido(texto) && texto.length() <= MAX_COD && soloLetrasYNumeros(texto);
    }

    public boolean esNombreValido(String texto){
        return esTextoValido(texto) && soloLetrasNumerosYEspacios(texto);
    }

    public boolean esPrecioValido(double precio){
        return precio > 0;
    }

    public int leerEntero(Scanner in,String mensaje){
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un número entero.");
            }
        }
    }

    public double leerPrecio(Scanner in,String mensaje){
        while (true) {
            System.out.print(mensaje);
            try {
                double precio = Double.parseDouble(in.nextLine().trim());
                if (esPrecioValido(precio)) {
                    return precio;
                }
                System.out.println("El precio debe ser mayor que 0.");
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un número (por ejemplo 12.5).");
            }
        }
    }

    public String leerTexto(Scanner in,String mensaje){
        while (true){
            System.out.print(mensaje);
            String texto = in.nextLine();
            if (esTextoValido(texto)) {
                return texto.trim();
            }
            System.out.println("El texto no puede estar vacío.");
        }
    }

    public String leerNombre(Scanner in,String mensaje){
        while (true){
            System.out.print(mensaje);
            String texto = in.nextLine();
            if (esNombreValido(texto)){
                return texto.trim();
            }
            System.out.println("El nombre no puede estar vacío ni tener símbolos.");
        }
    }

    public String leerCodigo(Scanner in,String mensaje){
        while (true){
            System.out.print(mensaje);
            String codigo = in.nextLine().trim();
            if (esCodigoValido(codigo)){
                return codigo;
            }
            System.out.println("El código solo puede tener letras y números y máximo "+MAX_COD+" caracteres.");
        }
    }

    private boolean soloLetrasYNumeros(String texto){
        for (int i=0;i<texto.length();i++) {
            if (!Character.isLetterOrDigit(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean soloLetrasNumerosYEspacios(String texto){
        for (int i=0;i<texto.length();i++) {
            char c = texto.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != ' '){
                return false;
            }
        }
        return true;
    }
}