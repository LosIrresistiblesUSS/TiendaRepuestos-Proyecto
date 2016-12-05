package DAO;

import Model.ComprobanteVenta;
import Model.DetalleOperacion;
import Model.DetalleVenta;
import Model.Operacion;
import java.util.List;

public interface DetalleVentaDao {
    public List<DetalleVenta> mostrarVenta(String busqueda);
    public int insertarVenta(DetalleVenta detalleVenta);
    public int insertarOperacion(Operacion operacion);
    public int insertarComprobanteVenta(ComprobanteVenta comprobanteVenta);
    public int insertarDetalleOperacion(DetalleOperacion detalleOperacion);
    public void cambiarEstadoVenta(DetalleVenta detalleVenta);
    public int ultimoIdOperacion();
    public int ultimoIdComprobanteVenta();
    public int ultimoIdDetalleOperacion();
    public int ultimoIdDetalleVenta();
}
