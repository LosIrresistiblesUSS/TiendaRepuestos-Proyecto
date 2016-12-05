package DAO;

import Model.DetalleVenta;
import java.util.List;

public interface DetalleVentaDao {
    public List<DetalleVenta> mostrarVenta(String busqueda);
    public int insertarVenta(DetalleVenta detalleVenta);
    public void cambiarEstadoVenta(DetalleVenta detalleVenta); 
}
