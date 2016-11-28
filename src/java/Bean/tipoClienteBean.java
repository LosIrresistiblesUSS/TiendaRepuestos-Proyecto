package Bean;

import DAO.TipoClienteDao;
import DAO.TipoClienteDaoImplement;
import Model.TipoCliente;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class tipoClienteBean {
    TipoCliente tipoCliente;
    List<TipoCliente> tipoClientes;

    public tipoClienteBean() {
        tipoCliente = new TipoCliente();
    }
    
    public void insertar(){
    TipoClienteDao linkDAO= new TipoClienteDaoImplement();
        linkDAO.insertarTipoCliente(tipoCliente);
        tipoCliente= new TipoCliente();
    }
    
    public void modificar(){
    TipoClienteDao linkDAO= new TipoClienteDaoImplement();
        linkDAO.modificarTipoCliente(tipoCliente);
        tipoCliente= new TipoCliente();
    }
    
    public void eliminar(){
    TipoClienteDao linkDAO= new TipoClienteDaoImplement();
        linkDAO.eliminarTipoCliente(tipoCliente);
        tipoCliente= new TipoCliente();
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public List<TipoCliente> getTipoClientes() {
        TipoClienteDao linkDAO= new TipoClienteDaoImplement();
        tipoClientes=linkDAO.mostrarTipoClientes();
        return tipoClientes;
    }

    public void setTipoClientes(List<TipoCliente> tipoClientes) {
        this.tipoClientes = tipoClientes;
    } 
}