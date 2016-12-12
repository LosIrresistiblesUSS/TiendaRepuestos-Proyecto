package Presentacion;

public class PruebaBusqueda {
    public static void main(String[] args) {
        String cadena = "Hola como estas";
        cadena = cadena.toLowerCase();
        if (cadena.contains("ESTAS".toLowerCase())) {
            System.out.println("Si lo contiene");
        } else {
            System.out.println("No lo contiene");
        }
    }
}
