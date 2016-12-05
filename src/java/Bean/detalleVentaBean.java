package Bean;

import DAO.DetalleVentaDao;
import DAO.DetalleVentaDaoImplement;
import DAO.RepuestoDao;
import DAO.RepuestoDaoImplement;
import Model.DetalleOperacion;
import Model.DetalleVenta;
import Model.Producto;
import Model.Repuesto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;

@ManagedBean
@ViewScoped
public class detalleVentaBean {
    DetalleVenta detalleVenta;
    List<DetalleVenta> detalleVentas;
    Producto producto;
    Repuesto repuesto;
    List<Repuesto> productos;
    List<Repuesto> productos2;
    DetalleOperacion detalleOperacion;
    List<DetalleOperacion> detalleOperaciones;
    String estado = "";
    String busqueda = "";
    double subtotal = 0;
    double igv = 0;
    double totalTemp = 0;
    int flag = 0;
    boolean flag2 = false;
    
    public detalleVentaBean() {
        detalleVenta = new DetalleVenta();
        repuesto = new Repuesto();
        producto = new Producto();
        producto.setRepuesto(repuesto);
        detalleOperacion = new DetalleOperacion();
        detalleOperaciones = new ArrayList<>();
        productos2 = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        productos2 = getProductos();
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
    
    public String validarImagen(String imagen){
        String img;
        if (imagen.equals("") || imagen.isEmpty()) {
            img = "sinproducto.jpg";
        } else {
            img = imagen;
        }
        
        return img;
    }
    
    public void onCarDrop(DragDropEvent ddEvent) {
        Repuesto rep = ((Repuesto) ddEvent.getData());
  
        DetalleOperacion dOpe = new DetalleOperacion();
        dOpe.setCantidad(1);
        dOpe.setPrecio(rep.getProducto().getPrecio());
        dOpe.setSubTotal(dOpe.getCantidad()*dOpe.getPrecio());
        dOpe.setProducto(rep.getProducto());
        
        detalleOperaciones.add(dOpe);
        productos2.remove(rep);
        
        subtotal = 0;
        igv = 0;
        totalTemp = 0;
        for(DetalleOperacion doperacion : detalleOperaciones){
            totalTemp = totalTemp + doperacion.getSubTotal();
            igv = (totalTemp * 18)/100;
            subtotal = totalTemp - igv;
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

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Repuesto> getProductos() {
        RepuestoDao linkDAO = new RepuestoDaoImplement();
        productos=linkDAO.mostrarRepuesto(busqueda);
        return productos;
    }

    public void setProductos(List<Repuesto> productos) {
        this.productos = productos;
    }

    public DetalleOperacion getDetalleOperacion() {
        return detalleOperacion;
    }

    public void setDetalleOperacion(DetalleOperacion detalleOperacion) {
        this.detalleOperacion = detalleOperacion;
    }

    public List<DetalleOperacion> getDetalleOperaciones() {
        return detalleOperaciones;
    }

    public void setDetalleOperaciones(List<DetalleOperacion> detalleOperaciones) {
        this.detalleOperaciones = detalleOperaciones;
    }

    public List<Repuesto> getProductos2() {
        return productos2;
    }

    public void setProductos2(List<Repuesto> productos2) {
        this.productos2 = productos2;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotalTemp() {
        return totalTemp;
    }

    public void setTotalTemp(double totalTemp) {
        this.totalTemp = totalTemp;
    }
}