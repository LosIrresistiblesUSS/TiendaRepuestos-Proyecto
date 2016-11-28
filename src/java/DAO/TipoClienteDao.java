package DAO;

import Model.TipoCliente;
import java.util.List;

public interface TipoClienteDao {
    public List<TipoCliente> mostrarTipoClientes();
    public void insertarTipoCliente(TipoCliente tipoCliente);
    public void modificarTipoCliente(TipoCliente tipoCliente);
    public void eliminarTipoCliente(TipoCliente tipoCliente);  
}