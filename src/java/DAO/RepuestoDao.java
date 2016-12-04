package DAO;

import Model.Producto;
import Model.Repuesto;
import java.util.List;

public interface RepuestoDao {
    public List<Repuesto> mostrarRepuesto(String busqueda);
    public int ultimoIdProducto();
    public int insertarRepuesto(Producto producto);
    public void modificarRepuesto(Producto producto);
    public boolean eliminarRepuesto(Producto producto);  
}
