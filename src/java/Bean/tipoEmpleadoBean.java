package Bean;

import DAO.TipoEmpleadoDao;
import DAO.TipoEmpleadoDaoImplement;
import Model.TipoEmpleado;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class tipoEmpleadoBean {
    TipoEmpleado tipoEmpleado;
    String busqueda = "";
    List<TipoEmpleado> tipoEmpleados;
    int flag = 0;
    boolean flag2 = false;
    
    public tipoEmpleadoBean() {
        tipoEmpleado = new TipoEmpleado();
    }
    
    public void insertar(){
    TipoEmpleadoDao linkDAO= new TipoEmpleadoDaoImplement();
        
        try{
            flag = linkDAO.insertarTipoEmpleado(tipoEmpleado);
            TipoEmpleado otro  = tipoEmpleado;
            tipoEmpleado= new TipoEmpleado();
            switch (flag) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de Empleado '"+ otro.getDescripcion()+ "' insertado correctamente."));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Tipo de Empleado '"+ otro.getDescripcion()+ "' ya se encuentra registrado."));
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Al menos debe intentar insertar algo."));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Insertar el Tipo de Empleado. Error: " + e.getMessage()));
        }
    }
    
    public void modificar(){
    TipoEmpleadoDao linkDAO= new TipoEmpleadoDaoImplement();
        
        try{
            linkDAO.modificarTipoEmpleado(tipoEmpleado);
            TipoEmpleado otro = tipoEmpleado;
            tipoEmpleado= new TipoEmpleado();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Algún campo fue actualizado correctamente por '" + otro.getDescripcion() + "'."));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Editar el Tipo de Servicio. Error: " + e.getMessage()));
        }
    }
    
    public void eliminar(){
    TipoEmpleadoDao linkDAO= new TipoEmpleadoDaoImplement();
        
        try{
            flag2 = linkDAO.eliminarTipoEmpleado(tipoEmpleado);
            TipoEmpleado otro = tipoEmpleado;
            tipoEmpleado= new TipoEmpleado();
            
            if (!flag2) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de Empleado '" + otro.getDescripcion() + "' eliminado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "El Tipo de Empleado '" + otro.getDescripcion() + "' está siendo utilizada como Llave foránea en otra tabla."));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Eliminar el Tipo de Empleado. Error: " + e.getMessage()));
        }
    }
    
    public void limpiar(){
        this.tipoEmpleado.setIdTipoEmpleado(0);
        this.tipoEmpleado.setDescripcion("");
    }
    
    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
    
    public void listar(){
        this.tipoEmpleados = getTipoEmpleados();
        
        if (!busqueda.equals("")) {
            if (!tipoEmpleados.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busqueda));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Tipos de Empleado que contengan el siguiente texto: " + busqueda));
            }   
        }
   }
    
    public List<TipoEmpleado> getTipoEmpleados() {
        TipoEmpleadoDao linkDAO= new TipoEmpleadoDaoImplement();
        tipoEmpleados=linkDAO.mostrarTipoEmpleados(busqueda);
        return tipoEmpleados;
    }

    public void setTipoEmpleados(List<TipoEmpleado> tipoEmpleados) {
        this.tipoEmpleados = tipoEmpleados;
    } 
    
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}