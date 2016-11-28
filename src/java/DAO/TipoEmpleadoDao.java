package DAO;

import Model.TipoEmpleado;
import java.util.List;

public interface TipoEmpleadoDao {
    public List<TipoEmpleado> mostrarTipoEmpleados(String busqueda);
    public int insertarTipoEmpleado(TipoEmpleado tipoEmpleado);
    public void modificarTipoEmpleado(TipoEmpleado tipoEmpleado);
    public boolean eliminarTipoEmpleado(TipoEmpleado  tipoEmpleado);  
}