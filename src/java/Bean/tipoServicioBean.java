package Bean;

import DAO.TipoServicioDao;
import DAO.TipoServicioDaoImplement;
import Model.TipoServicio;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class tipoServicioBean {
    TipoServicio tipoServicio;
    String busqueda = "";
    List<TipoServicio> tipoServicios;
    int flag = 0;
    boolean flag2 = false;

    public tipoServicioBean() {
        tipoServicio = new TipoServicio();
    }
    
    public void insertar(){
    TipoServicioDao linkDAO= new TipoServicioDaoImplement();
    
        try {
            flag = linkDAO.insertarTipoServicio(tipoServicio);
            TipoServicio otro = tipoServicio;
            tipoServicio= new TipoServicio();
            
            switch (flag) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de Servicio '"+ otro.getDescripcion()+ "' insertado correctamente."));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Tipo de Servicio '"+ otro.getDescripcion()+ "' ya se encuentra registrado."));
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Al menos debe intentar insertar algo."));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Insertar el Tipo de Servicio. Error: " + e.getMessage()));
        }
    }
    
    public void modificar(){
    TipoServicioDao linkDAO= new TipoServicioDaoImplement();
        
        try {
            linkDAO.modificarTipoServicio(tipoServicio);
            TipoServicio otro = tipoServicio;
            tipoServicio= new TipoServicio();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Algún campo fue actualizado correctamente por '" + otro.getDescripcion() + "'."));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Editar el Tipo de Servicio. Error: " + e.getMessage()));
        }
    }
    
    public void eliminar(){
    TipoServicioDao linkDAO= new TipoServicioDaoImplement();
        
        try {
            flag2 = linkDAO.eliminarTipoServicio(tipoServicio);
            TipoServicio otro = tipoServicio;
            tipoServicio= new TipoServicio();
            
            if (!flag2) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de Servicio '" + otro.getDescripcion() + "' eliminado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "El Tipo de Servicio '" + otro.getDescripcion() + "' está siendo utilizada como Llave foránea en otra tabla."));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Eliminar el Tipo de Servicio. Error: " + e.getMessage()));
        }
    }
    
    public void limpiar(){
        this.tipoServicio.setIdTipoServicio(0);
        this.tipoServicio.setDescripcion("");
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
    
    public void listar(){
        this.tipoServicios = getTipoServicios();
        
        if (!busqueda.equals("")) {
            if (!tipoServicios.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busqueda));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Tipos de Servicio que contengan el siguiente texto: " + busqueda));
            }   
        }
   }
    
    public List<TipoServicio> getTipoServicios() {
        TipoServicioDao linkDAO= new TipoServicioDaoImplement();
        tipoServicios=linkDAO.mostrarTipoServicios(busqueda);
        return tipoServicios;
    }

    public void setTipoServicios(List<TipoServicio> tipoServicios) {
        this.tipoServicios = tipoServicios;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}