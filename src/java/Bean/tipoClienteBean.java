package Bean;

import DAO.TipoClienteDao;
import DAO.TipoClienteDaoImplement;
import Model.TipoCliente;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class tipoClienteBean {
    TipoCliente tipoCliente;
    String busqueda = "";
    List<TipoCliente> tipoClientes;
    int flag = 0;
    boolean flag2 = false;

    public tipoClienteBean() {
        tipoCliente = new TipoCliente();
    }
    
    public void insertar(){
    TipoClienteDao linkDAO= new TipoClienteDaoImplement();
       
        try {
            flag = linkDAO.insertarTipoCliente(tipoCliente);
            TipoCliente otro = tipoCliente;
            tipoCliente= new TipoCliente();
            
            switch (flag) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de Cliente '"+ otro.getDescripcion()+ "' insertado correctamente."));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Tipo de Cliente '"+ otro.getDescripcion()+ "' ya se encuentra registrado."));
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Al menos debe intentar insertar algo."));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Insertar el Tipo de Cliente. Error: " + e.getMessage()));
        }
    }
    
    public void modificar(){
    TipoClienteDao linkDAO= new TipoClienteDaoImplement();
        try {
            linkDAO.modificarTipoCliente(tipoCliente);
            TipoCliente otro = tipoCliente;
            tipoCliente= new TipoCliente();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Algún campo fue actualizado correctamente por '" + otro.getDescripcion() + "'."));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Editar el Tipo de Servicio. Error: " + e.getMessage()));
        }
    }
    
    public void eliminar(){
    TipoClienteDao linkDAO= new TipoClienteDaoImplement();
       try {
            flag2 = linkDAO.eliminarTipoCliente(tipoCliente);
            TipoCliente otro = tipoCliente;
            tipoCliente= new TipoCliente();
            
            if (!flag2) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de Cliente '" + otro.getDescripcion() + "' eliminado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "El Tipo de Cliente '" + otro.getDescripcion() + "' está siendo utilizada como Llave foránea en otra tabla."));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Eliminar el Tipo de Cliente. Error: " + e.getMessage()));
        }
    }
    
      public void limpiar(){
        this.tipoCliente.setIdTipoCliente(0);
        this.tipoCliente.setDescripcion("");
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
     public void listar(){
        this.tipoClientes = getTipoClientes();
        
        if (!busqueda.equals("")) {
            if (!tipoClientes.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busqueda));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Tipos de Servicio que contengan el siguiente texto: " + busqueda));
            }   
        }
   }
        

    public List<TipoCliente> getTipoClientes() {
        TipoClienteDao linkDAO= new TipoClienteDaoImplement();
        tipoClientes=linkDAO.mostrarTipoClientes(busqueda);
        return tipoClientes;
    }

    public void setTipoClientes(List<TipoCliente> tipoClientes) {
        this.tipoClientes = tipoClientes;
    } 
    
     public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    
    
}