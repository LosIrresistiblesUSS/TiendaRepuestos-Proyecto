package Bean;

import DAO.DetalleVentaDao;
import DAO.DetalleVentaDaoImplement;
import Model.DetalleVenta;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class detalleVentaBean {
    DetalleVenta detalleVenta;
    List<DetalleVenta> detalleVentas;
    String estado = "";
    String busqueda = "";
    int flag = 0;
    boolean flag2 = false;
    
    public detalleVentaBean() {
        detalleVenta = new DetalleVenta();
    }
    
    public void insertar(){
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        
        try {
            flag = linkDAO.insertarVenta(detalleVenta);
            detalleVenta= new DetalleVenta();
            
            switch (flag) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Venta insertada con Exito."));
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Al menos debe intentar insertar algo."));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Insertar una Venta. Error: " + e.getMessage()));
        }
    }
    
    public void cambiarEstado(){
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        
        try {
            linkDAO.cambiarEstadoVenta(detalleVenta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se actualizó el estado de la Venta."));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar actualizar el estado de la Venta. Error: " + e.getMessage()));
        }
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public void listar(){
        this.detalleVentas = getDetalleVentas();
        
        if (!busqueda.equals("")) {
            if (!detalleVentas.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busqueda));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Ventas relacionadas con los clientes que contengan el siguiente texto: " + busqueda));
            }   
        }
   }
    
    public List<DetalleVenta> getDetalleVentas() {
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        detalleVentas=linkDAO.mostrarVenta(busqueda);
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
