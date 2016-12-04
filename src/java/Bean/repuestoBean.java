package Bean;

import DAO.RepuestoDao;
import DAO.RepuestoDaoImplement;
import Helpers.UtilPath;
import Model.Producto;
import Model.Repuesto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class repuestoBean {
    Producto producto;
    Repuesto repuesto;
    String busqueda = "";
    String imagen = null;
    List<Repuesto> productos;
    int flag = 0;
    boolean flag2 = false;

    public repuestoBean() {
        repuesto = new Repuesto();
        producto = new Producto();
        producto.setRepuesto(repuesto);
    }
    
    public int ultimoID(){
        RepuestoDao linkDAO = new RepuestoDaoImplement();
        int numero = linkDAO.ultimoIdProducto();
        return numero;
    }
    
    public String validarImagen(String imagen){
        String img;
        if (imagen.equals("") || imagen.isEmpty()) {
            img = "sinproducto.jpg";
        } else {
            img = imagen;
        }
        
        return img;
    }
    
    public void handleFileUpload(FileUploadEvent event){
        UploadedFile file = event.getFile();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String path = UtilPath.getUrlDefinida(ec.getRealPath("/"));
        String realPath = path+File.separator+"web"+File.separator+"resources"+File.separator+"img"+File.separator+"repuestos"+File.separator+file.getFileName();
        
        try {
            FileInputStream in = (FileInputStream) file.getInputstream();
            FileOutputStream out = new FileOutputStream(realPath);
            
//            Repuesto r = new Repuesto();
//            r.setImagen(file.getFileName());
            imagen = file.getFileName();
//            this.producto.setRepuesto(r);
            
            byte[] buffer = new byte [(int) file.getSize()];
            int contador = 0;
            while((contador = in.read(buffer)) != -1){
                out.write(buffer, 0, contador);
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Imagen Subida.", "Proceda a guardar."));
            in.close();
            out.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Subir la Imagen. Error: " + e.getMessage()));
        }
    }
    
    public void insertar(){
    RepuestoDao linkDAO = new RepuestoDaoImplement();
    
        try {
            Repuesto r = new Repuesto();
            r.setIdProducto(ultimoID());
            if (imagen!=null) {
                r.setImagen(imagen);
                imagen=null;
            }
            this.producto.setIdProducto(ultimoID());
            this.producto.setRepuesto(r);
            
            flag = linkDAO.insertarRepuesto(producto);
            Producto otro = producto;
            producto = new Producto();
            
            switch (flag) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Repuesto '"+ otro.getDescripcion()+ "' insertado correctamente."));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Repuesto '"+ otro.getDescripcion()+ "' ya se encuentra registrado."));
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Al menos debe intentar insertar algo."));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Insertar el Repuesto. Error: " + e.getMessage()));
        }
    }
    
    public void modificar(){
    RepuestoDao linkDAO = new RepuestoDaoImplement();
        
        try {
            
            if (imagen!=null) {
                producto.getRepuesto().setImagen(imagen);
                imagen=null;
            }
            linkDAO.modificarRepuesto(producto);
            Producto otro = producto;
            producto= new Producto();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Algún campo fue actualizado correctamente por '" + otro.getDescripcion() + "'."));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Editar el Repuesto. Error: " + e.getMessage()));
        }
    }
    
    public void eliminar(){
    RepuestoDao linkDAO = new RepuestoDaoImplement();
        
        try {
            flag2 = linkDAO.eliminarRepuesto(producto);
            Producto otro = producto;
            producto= new Producto();
            
            if (!flag2) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Repuesto '" + otro.getDescripcion() + "' eliminado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "El Repuesto '" + otro.getDescripcion() + "' está siendo utilizada como Llave foránea en otra tabla."));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Eliminar el Repuesto. Error: " + e.getMessage()));
        }
    }
    
    public void limpiar(){
        Repuesto r = new Repuesto();
        this.producto.setIdProducto(0);
        this.producto.setDescripcion("");
        this.producto.setPrecio(0.0);
        this.producto.setPrecioPorMayor(0.0);
        this.producto.setStock(0);
        
        r.setIdProducto(0);
        
        this.producto.setRepuesto(r);
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public void listar(){
        this.productos = getProductos();
        
        if (!busqueda.equals("")) {
            if (!productos.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busqueda));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Repuestos que contengan el siguiente texto: " + busqueda));
            }   
        }
    }

    public List<Repuesto> getProductos() {
        RepuestoDao linkDAO = new RepuestoDaoImplement();
        productos=linkDAO.mostrarRepuesto(busqueda);
        return productos;
    }

    public void setProductos(List<Repuesto> productos) {
        this.productos = productos;
    }
    
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}