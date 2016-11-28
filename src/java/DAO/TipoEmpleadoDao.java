package DAO;

import Model.TipoEmpleado;
import java.util.List;

public interface TipoEmpleadoDao {
    public List<TipoEmpleado> mostrarTipoEmpleados();
    public void insertarTipoEmpleado(TipoEmpleado tipoEmpleado);
    public void modificarTipoEmpleado(TipoEmpleado tipoEmpleado);
    public void eliminarTipoEmpleado(TipoEmpleado  tipoEmpleado);  
}