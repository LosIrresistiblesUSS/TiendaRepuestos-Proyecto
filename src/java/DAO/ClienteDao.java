package DAO;

import Model.Cliente;
import java.util.List;

public interface ClienteDao {
    public List<Cliente> mostrarCliente(String busqueda);
}
