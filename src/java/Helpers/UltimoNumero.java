package Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UltimoNumero {
     private static Conexion con;
    private static Connection cn;
    private static ResultSet rs;
    private static PreparedStatement ps;
    private static String sql;
    
    public static String ultimoNumeroBoleta(){
        sql = "select " +
            "numero " +
            "from comprobanteVenta as cv " +
            "inner join TipoComprobanteVenta as tcv " +
            "on cv.idTipoComprobanteVenta = tcv.idTipoComprobanteVenta " +
            "where tcv.descripcion = 'boleta' " +
            "order by numero desc limit 1";
        String number = null;
        String numeroGeneral = null;
        String numero = null;
        String codigo = null;
        try {
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                number = rs.getString("numero");
            }
        } catch (Exception e) {
            System.out.println("Error ultimoNumeroBoleta: " + e.getMessage());
        } finally{
            con.cerrarConexion(cn);
        }
        
        String num = number.substring(6);
        String cod = number.substring(2, 5);
        
        int numEntero = Integer.parseInt(num);
        int codEntero = Integer.parseInt(cod);
        
        if(numEntero != 999999){
            numEntero = numEntero + 1;
            String numEnteroString = String.valueOf(numEntero);
            char[] arrayNumEntero = numEnteroString.toCharArray();
            int totalCaracteresNumEntero = arrayNumEntero.length;
            
            switch (totalCaracteresNumEntero) {
                case 1:
                    numero = "00000" + numEnteroString;
                    break;
                case 2:
                    numero = "0000" + numEnteroString;
                    break;
                case 3:
                    numero = "000" + numEnteroString;
                    break;
                case 4:
                    numero = "00" + numEnteroString;
                    break;
                case 5:
                    numero = "0" + numEnteroString;
                    break;
                case 6:
                    numero = numEnteroString;
                    break;
                default:
                    break;
            }
            codigo = cod;
            numeroGeneral = "B-"+codigo+"-"+numero;
        } else {
            if (codEntero != 999) {
                codEntero = codEntero + 1;
                String codEnteroString = String.valueOf(codEntero);
                char[] arrayCodEntero = codEnteroString.toCharArray();
                int totalCaracteresCodEntero = arrayCodEntero.length;
                
                switch (totalCaracteresCodEntero) {
                    case 1:
                        codigo = "00" + codEnteroString;
                        break;
                    case 2:
                        codigo = "0" + codEnteroString;
                        break;
                    case 3:
                        codigo = codEnteroString;
                        break;
                    default:
                        break;
                }
                numero = "000001";
                numeroGeneral = "B-"+codigo+"-"+numero;
            } else {
                numeroGeneral = "Ya no existen codigos disponibles";
            }
        }
        return numeroGeneral;
    }
    
    public static String ultimoNumeroFactura(){
        sql = "select " +
            "numero " +
            "from comprobanteVenta as cv " +
            "inner join TipoComprobanteVenta as tcv " +
            "on cv.idTipoComprobanteVenta = tcv.idTipoComprobanteVenta " +
            "where tcv.descripcion = 'factura' " +
            "order by numero desc limit 1";
        String number = null;
        String numeroGeneral = null;
        String numero = null;
        String codigo = null;
        try {
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                number = rs.getString("numero");
            }
        } catch (Exception e) {
            System.out.println("Error ultimoNumeroFactura: " + e.getMessage());
        } finally{
            con.cerrarConexion(cn);
        }
        
        String num = number.substring(6);
        String cod = number.substring(2, 5);
        
        int numEntero = Integer.parseInt(num);
        int codEntero = Integer.parseInt(cod);
        
        if(numEntero != 999999){
            numEntero = numEntero + 1;
            String numEnteroString = String.valueOf(numEntero);
            char[] arrayNumEntero = numEnteroString.toCharArray();
            int totalCaracteresNumEntero = arrayNumEntero.length;
            
            switch (totalCaracteresNumEntero) {
                case 1:
                    numero = "00000" + numEnteroString;
                    break;
                case 2:
                    numero = "0000" + numEnteroString;
                    break;
                case 3:
                    numero = "000" + numEnteroString;
                    break;
                case 4:
                    numero = "00" + numEnteroString;
                    break;
                case 5:
                    numero = "0" + numEnteroString;
                    break;
                case 6:
                    numero = numEnteroString;
                    break;
                default:
                    break;
            }
            codigo = cod;
            numeroGeneral = "F-"+codigo+"-"+numero;
        } else {
            if (codEntero != 999) {
                codEntero = codEntero + 1;
                String codEnteroString = String.valueOf(codEntero);
                char[] arrayCodEntero = codEnteroString.toCharArray();
                int totalCaracteresCodEntero = arrayCodEntero.length;
                
                switch (totalCaracteresCodEntero) {
                    case 1:
                        codigo = "00" + codEnteroString;
                        break;
                    case 2:
                        codigo = "0" + codEnteroString;
                        break;
                    case 3:
                        codigo = codEnteroString;
                        break;
                    default:
                        break;
                }
                numero = "000001";
                numeroGeneral = "F-"+codigo+"-"+numero;
            } else {
                numeroGeneral = "Ya no existen codigos disponibles";
            }
        }
        return numeroGeneral;
    }
}
