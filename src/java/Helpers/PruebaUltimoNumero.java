package Helpers;

public class PruebaUltimoNumero {
    public static void main(String[] args) {
        String ultimaBoleta = UltimoNumero.ultimoNumeroBoleta();
        System.out.println(ultimaBoleta);
        String ultimaFactura = UltimoNumero.ultimoNumeroFactura();
        System.out.println(ultimaFactura);
    }
}