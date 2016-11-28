package Bean;

import DAO.TipoDocumentoDao;
import DAO.TipoDocumentoDaoImplement;
import Model.TipoDocumento;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class tipoDocumentoBean {
    TipoDocumento tipoDocumento;
    String busqueda = "";
    List<TipoDocumento> tipoDocumentos;
    int flag = 0;
    boolean flag2 = false;
    
    public tipoDocumentoBean() {
        tipoDocumento = new TipoDocumento();
    }
    
    public void insertar(){
    TipoDocumentoDao linkDAO= new TipoDocumentoDaoImplement();
    
        try{
           flag = linkDAO.insertarTipoDocumento(tipoDocumento);
           TipoDocumento otro = tipoDocumento;
           tipoDocumento = new TipoDocumento(); 
           switch (flag) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de Documento '"+ otro.getDescripcion()+ "' insertado correctamente."));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Tipo de Documento '"+ otro.getDescripcion()+ "' ya se encuentra registrado."));
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Al menos debe intentar insertar algo."));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Insertar el Tipo de Documento. Error: " + e.getMessage()));
        }
        
    }
    
     public void modificar(){
    TipoDocumentoDao linkDAO= new TipoDocumentoDaoImplement();
    
        try{
            linkDAO.modificarTipoDocumento(tipoDocumento);
            TipoDocumento otro = tipoDocumento;
            tipoDocumento = new TipoDocumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Algún campo fue actualizado correctamente por '" + otro.getDescripcion() + "'."));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Editar el Tipo de Servicio. Error: " + e.getMessage()));
        }
    }
     
    public void eliminar(){
    TipoDocumentoDao linkDAO= new TipoDocumentoDaoImplement();
    
        try{
            flag2 = linkDAO.eliminarTipoDocumento(tipoDocumento);
            TipoDocumento otro = tipoDocumento;
            tipoDocumento = new TipoDocumento();
            
            if (!flag2) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de Documento '" + otro.getDescripcion() + "' eliminado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "El Tipo de Documento '" + otro.getDescripcion() + "' está siendo utilizada como Llave foránea en otra tabla."));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Eliminar el Tipo de Documento. Error: " + e.getMessage()));
        }
    }
    
    public void limpiar(){
        this.tipoDocumento.setIdTipoDocumento(0);
        this.tipoDocumento.setDescripcion("");
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public void listar(){
        this.tipoDocumentos = getTipoDocumentos();
        
        if (!busqueda.equals("")) {
            if (!tipoDocumentos.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busqueda));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Tipos de Documento que contengan el siguiente texto: " + busqueda));
            }  
        }
   }

    public List<TipoDocumento> getTipoDocumentos() {
        TipoDocumentoDao linkDAO= new TipoDocumentoDaoImplement();
        tipoDocumentos=linkDAO.mostrarTipoDocumentos(busqueda);
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }
    
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}