package DAO;

import Model.TipoCliente;
import java.util.List;

public interface TipoClienteDao {
    public List<TipoCliente> mostrarTipoClientes(String busqueda);
    public int insertarTipoCliente(TipoCliente tipoCliente);
    public void modificarTipoCliente(TipoCliente tipoCliente);
    public boolean eliminarTipoCliente(TipoCliente tipoCliente);  
}