package Bean;

import DAO.ClienteDao;
import DAO.ClienteDaoImplement;
import DAO.DetalleVentaDao;
import DAO.DetalleVentaDaoImplement;
import DAO.RepuestoDao;
import DAO.RepuestoDaoImplement;
import Model.Cliente;
import Model.ComprobanteVenta;
import Model.DetalleOperacion;
import Model.DetalleOperacionReporte;
import Model.DetalleVenta;
import Model.Login;
import Model.Operacion;
import Model.Persona;
import Model.Producto;
import Model.Repuesto;
import Model.TipoComprobanteVenta;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import Helpers.Conexion;
import Helpers.UltimoNumero;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.primefaces.event.DragDropEvent;

@ManagedBean
@ViewScoped
public class detalleVentaBean {
    DetalleVenta detalleVenta;
    List<DetalleVenta> detalleVentas;
    Producto producto;
    Repuesto repuesto;
    Operacion operacion;
    ComprobanteVenta comprobanteVenta;
    DetalleOperacion detalleOperacion;
    DetalleOperacionReporte detalleOperacionReporte;
    List<DetalleOperacionReporte> detalleOperacionReportes;
    List<Repuesto> productos;
    List<Repuesto> productos2;
    List<Repuesto> productosFiltrados;
    List<DetalleOperacion> detalleOperaciones;
    
    List<DetalleOperacion> listaReporte = new ArrayList<>();
    
    Persona persona;
    Cliente cliente;
    String busquedaCliente = "";
    String busquedaRepuesto = "";
    List<Cliente> personaClientes;
    
    String estado = "";
    String busqueda = "";
    String nombreOrazonSocialTemp = "";
    String numeroDNICliente = "";
    String direccionCliente = "";
    String nombresCliente = "";
    double subtotal = 0;
    double igv = 0;
    double totalTemp = 0;
    int flag = 0;
    boolean flag2 = false;
    
    int idPersonaEmpleadoTemp;
    int idPersonaClienteTemp;
    int idTipoComprobanteVentaTemp;
    String numeroTemp;
    Date fechaTemp;
    String descripcionTemp;
    
    String ultimoBoleta;
    String ultimoFactura;
    
    public detalleVentaBean() {
        detalleVenta = new DetalleVenta();
        repuesto = new Repuesto();
        producto = new Producto();
        producto.setRepuesto(repuesto);
        operacion = new Operacion();
        comprobanteVenta = new ComprobanteVenta();
        detalleOperacion = new DetalleOperacion();
        detalleOperacion = new DetalleOperacion();
        detalleOperacionReporte = new DetalleOperacionReporte();
        detalleOperaciones = new ArrayList<>();
        productos2 = new ArrayList<>();
        detalleOperacionReportes = new ArrayList<>();
        productosFiltrados = new ArrayList<>();
        
        cliente = new Cliente();
        persona = new Persona();
        persona.setCliente(cliente);
    }
    
    @PostConstruct
    public void init() {
        productos2 = getProductos();
        productosFiltrados = getProductos();
    }

    public void exportarPDFBoleta(ActionEvent actionEvent) throws JRException, IOException{
        Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        this.comprobanteVenta = (ComprobanteVenta)actionEvent.getComponent().getAttributes().get("myattribute");
        
        Conexion con;
        Connection cn;
        con = new Conexion();
        cn = con.getConexion();
        
        Map<String,Object> parametros= new HashMap<String,Object>();
        parametros.put("empleado", user.getPersona().getEmpleado().getApellidos() + " ");
        parametros.put("cliente",comprobanteVenta.getDetalleVentaList().get(0).getDetalleOperacion().getOperacion().getPersonaCliente().getCliente().getApellidos() + comprobanteVenta.getDetalleVentaList().get(0).getDetalleOperacion().getOperacion().getPersonaCliente().getCliente().getRazonSocial() + " ");
        parametros.put("direccion", comprobanteVenta.getDetalleVentaList().get(0).getDetalleOperacion().getOperacion().getPersonaCliente().getDireccion() + " ");
        parametros.put("documento", comprobanteVenta.getDetalleVentaList().get(0).getDetalleOperacion().getOperacion().getPersonaCliente().getNumeroDocumento());
        parametros.put("fecha", comprobanteVenta.getFecha());
        parametros.put("total", comprobanteVenta.getImporte());
        parametros.put("tipoComprobante", comprobanteVenta.getTipoComprobanteVenta().getDescripcion().toUpperCase() + " ");
        parametros.put("numero", comprobanteVenta.getNumero());
        
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ComprobanteVentaBoleta.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, cn);
            
            

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition","attachment; filename=ComprobanteVentaBoleta.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
            System.out.println("ERROOOOOOOOR al exportar la Boleta: "+ e.getMessage());
        }
        
    }
    
    public void exportarPDFFactura(ActionEvent actionEvent) throws JRException, IOException{
        Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        this.comprobanteVenta = (ComprobanteVenta)actionEvent.getComponent().getAttributes().get("myattribute");
        
        Conexion con;
        Connection cn;
        con = new Conexion();
        cn = con.getConexion();
        
        double totalTemp2, igv2, subtotal2;
        totalTemp2 = comprobanteVenta.getImporte();
        totalTemp2 = Math.round(totalTemp2*100)/100.0;

        igv2 = (totalTemp2 / 1.18)*0.18;
        igv2 = Math.round(igv2*100)/100.0;

        subtotal2 = totalTemp2 - igv2;
        subtotal2 = Math.round(subtotal2*100)/100.0;
        
        
        Map<String,Object> parametros= new HashMap<String,Object>();
        parametros.put("empleado", user.getPersona().getEmpleado().getApellidos() + " ");
        parametros.put("cliente",comprobanteVenta.getDetalleVentaList().get(0).getDetalleOperacion().getOperacion().getPersonaCliente().getCliente().getApellidos() + comprobanteVenta.getDetalleVentaList().get(0).getDetalleOperacion().getOperacion().getPersonaCliente().getCliente().getRazonSocial() + " ");
        parametros.put("direccion", comprobanteVenta.getDetalleVentaList().get(0).getDetalleOperacion().getOperacion().getPersonaCliente().getDireccion() + " ");
        parametros.put("documento", comprobanteVenta.getDetalleVentaList().get(0).getDetalleOperacion().getOperacion().getPersonaCliente().getNumeroDocumento());
        parametros.put("fecha", comprobanteVenta.getFecha());
        parametros.put("total", comprobanteVenta.getImporte());
        parametros.put("tipoComprobante", comprobanteVenta.getTipoComprobanteVenta().getDescripcion().toUpperCase() + " ");
        parametros.put("numero", comprobanteVenta.getNumero());
        parametros.put("subtotal", String.valueOf(subtotal2));
        parametros.put("igv", String.valueOf(igv2));
        
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ComprobanteVentaFactura.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, cn);
            
            

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition","attachment; filename=ComprobanteVentaFactura.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
            System.out.println("ERROOOOOOOOR al exportar la Factura: "+ e.getMessage());
        }
        
    }
    
    public String insertar(){
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        String redireccion = null;
        try {
            Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
            
            
            //DATOS DE PERSONA CLIENTE
            Persona personaClienteTemp = new Persona();
            Persona personaEmpleadoTemp = new Persona();
            personaClienteTemp.setIdPersona(idPersonaClienteTemp);
            personaEmpleadoTemp.setIdPersona(user.getPersona().getIdPersona());
            
            //DATOS DE OPERACION
            Operacion operacionTemp = new Operacion();
            operacionTemp.setIdOperacion(linkDAO.ultimoIdOperacion());
            operacionTemp.setEstado(true);
            operacionTemp.setPersonaCliente(personaClienteTemp);
            operacionTemp.setPersonaEmpleado(personaEmpleadoTemp);
            
            linkDAO.insertarOperacion(operacionTemp);
            
            //DATOS DE DETALLE OPERACIONES
            List<Integer> listaEnteros = new ArrayList<>();
            for(DetalleOperacion doTemp : detalleOperaciones){
                doTemp.setOperacion(operacionTemp);
                doTemp.setIdDetalleOperacion(linkDAO.ultimoIdDetalleOperacion());
                linkDAO.insertarDetalleOperacion(doTemp);
                listaEnteros.add(doTemp.getIdDetalleOperacion());
            }
            
            //DATOS PARA TIPO COMPROBANTE VENTA
            TipoComprobanteVenta tipoComprobanteVentaTemp = new TipoComprobanteVenta();
            tipoComprobanteVentaTemp.setIdTipoComprobanteVenta(idTipoComprobanteVentaTemp);
            
            ComprobanteVenta comprobanteVentaTemp = new ComprobanteVenta();
            comprobanteVentaTemp.setIdComprobanteVenta(linkDAO.ultimoIdComprobanteVenta());
            comprobanteVentaTemp.setNumero(numeroTemp);
            comprobanteVentaTemp.setFecha(fechaTemp);
            comprobanteVentaTemp.setDescripcion(descripcionTemp);
            comprobanteVentaTemp.setImporte(totalTemp);
            comprobanteVentaTemp.setEstado(true);
            comprobanteVentaTemp.setTipoComprobanteVenta(tipoComprobanteVentaTemp);
            
            linkDAO.insertarComprobanteVenta(comprobanteVentaTemp);
            
            List<DetalleVenta> detalleVentasTemp = new ArrayList<>();
            
            for(DetalleOperacion doTemp : detalleOperaciones){
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdDetalleVenta(linkDAO.ultimoIdDetalleVenta());
                detalle.setDetalleOperacion(doTemp);
                detalle.setComprobanteVenta(comprobanteVentaTemp);
                flag = linkDAO.insertarVenta(detalle);
                detalleVentasTemp.add(detalle);
            }
                        
            detalleVenta= new DetalleVenta();            
            
            switch (flag) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Venta insertada con Exito."));
                    redireccion = "VentaRepuestos?faces-redirect=true";
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Al menos debe intentar insertar algo."));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Insertar una Venta. Error: " + e.getMessage()));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "VentaRepuestos.xhtml");
        return redireccion;
    }
        
    public int ultimoIdOperacion(){
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        int numero = linkDAO.ultimoIdOperacion();
        return numero;
    }
    
    public int ultimoIdComprobanteVenta(){
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        int numero = linkDAO.ultimoIdComprobanteVenta();
        return numero;
    }
    
    public int ultimoIdDetalleOperacion(){
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        int numero = linkDAO.ultimoIdDetalleOperacion();
        return numero;
    }
    
    public int ultimoIdDetalleVenta(){
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        int numero = linkDAO.ultimoIdDetalleVenta();
        return numero;
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
    
    public void cambiarCliente(){
        idPersonaClienteTemp = persona.getIdPersona();
        nombreOrazonSocialTemp = persona.getCliente().getApellidos() + persona.getCliente().getRazonSocial();
        numeroDNICliente = persona.getNumeroDocumento();
        direccionCliente = persona.getDireccion();
        nombresCliente = persona.getNombres();
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
    
    public void listarProductos(){
        String nombreProductoString = "";
        List<Repuesto> lista = new ArrayList<>();
        for (Repuesto r : productos2) {
            nombreProductoString = r.getProducto().getDescripcion().toLowerCase();
            if (nombreProductoString.contains(busquedaRepuesto.toLowerCase())) {
                lista.add(r);
            }
        }
        
        productosFiltrados = lista;
       
        if (!busquedaRepuesto.equals("")) {
            if (!lista.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busquedaRepuesto));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Repuestos que contengan el siguiente texto: " + busquedaRepuesto));
            }   
        }
    }
    
    public void listarCliente(){
        this.personaClientes = getPersonaClientes();
        
        if (!busquedaCliente.equals("")) {
            if (!personaClientes.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busquedaCliente));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Clientes que contengan el siguiente texto: " + busquedaCliente));
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
        productosFiltrados.remove(rep);
        
        subtotal = 0;
        igv = 0;
        totalTemp = 0;
        for(DetalleOperacion doperacion : detalleOperaciones){
            totalTemp = totalTemp + doperacion.getSubTotal();
            totalTemp = Math.round(totalTemp*100)/100.0;
            
            igv = (totalTemp / 1.18)*0.18;
            igv = Math.round(igv*100)/100.0;
            
            subtotal = totalTemp - igv;
            subtotal = Math.round(subtotal*100)/100.0;
        }
    }
    
    public String ultimoNumeroBoleta(){
        String numero = UltimoNumero.ultimoNumeroBoleta();
        return numero;
    }
    
    public String ultimoNumeroFactura(){
        String numero = UltimoNumero.ultimoNumeroFactura();
        return numero;
    }
    
    public List<DetalleVenta> getDetalleVentas() {
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        detalleVentas=linkDAO.mostrarVentaConDistint(busqueda);
        return detalleVentas;
    }
    
    public List<DetalleVenta> getDetalleVentasSinDistint() {
        DetalleVentaDao linkDAO= new DetalleVentaDaoImplement();
        detalleVentas=linkDAO.mostrarVentaSinDistint(busqueda);
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

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public ComprobanteVenta getComprobanteVenta() {
        return comprobanteVenta;
    }

    public void setComprobanteVenta(ComprobanteVenta comprobanteVenta) {
        this.comprobanteVenta = comprobanteVenta;
    }

    public int getIdPersonaEmpleadoTemp() {
        return idPersonaEmpleadoTemp;
    }

    public void setIdPersonaEmpleadoTemp(int idPersonaEmpleadoTemp) {
        this.idPersonaEmpleadoTemp = idPersonaEmpleadoTemp;
    }

    public int getIdPersonaClienteTemp() {
        return idPersonaClienteTemp;
    }

    public void setIdPersonaClienteTemp(int idPersonaClienteTemp) {
        this.idPersonaClienteTemp = idPersonaClienteTemp;
    }

    public int getIdTipoComprobanteVentaTemp() {
        return idTipoComprobanteVentaTemp;
    }

    public void setIdTipoComprobanteVentaTemp(int idTipoComprobanteVentaTemp) {
        this.idTipoComprobanteVentaTemp = idTipoComprobanteVentaTemp;
    }

    public String getNumeroTemp() {
        return numeroTemp;
    }

    public void setNumeroTemp(String numeroTemp) {
        this.numeroTemp = numeroTemp;
    }

    public Date getFechaTemp() {
        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        return sqlDate;
    }

    public void setFechaTemp(Date fechaTemp) {
        this.fechaTemp = fechaTemp;
    }

    public String getDescripcionTemp() {
        return descripcionTemp;
    }

    public void setDescripcionTemp(String descripcionTemp) {
        this.descripcionTemp = descripcionTemp;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getPersonaClientes() {
        ClienteDao linkDAO = new ClienteDaoImplement();
        personaClientes=linkDAO.mostrarCliente(busquedaCliente);
        return personaClientes;
    }

    public void setPersonaClientes(List<Cliente> personaClientes) {
        this.personaClientes = personaClientes;
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getBusquedaCliente() {
        return busquedaCliente;
    }

    public void setBusquedaCliente(String busquedaCliente) {
        this.busquedaCliente = busquedaCliente;
    }

    public String getNombreOrazonSocialTemp() {
        return nombreOrazonSocialTemp;
    }

    public void setNombreOrazonSocialTemp(String nombreOrazonSocialTemp) {
        this.nombreOrazonSocialTemp = nombreOrazonSocialTemp;
    }

    public String getNumeroDNICliente() {
        return numeroDNICliente;
    }

    public void setNumeroDNICliente(String numeroDNICliente) {
        this.numeroDNICliente = numeroDNICliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getNombresCliente() {
        return nombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }

    public String getBusquedaRepuesto() {
        return busquedaRepuesto;
    }

    public void setBusquedaRepuesto(String busquedaRepuesto) {
        this.busquedaRepuesto = busquedaRepuesto;
    }

    public List<Repuesto> getProductosFiltrados() {
        return productosFiltrados;
    }

    public void setProductosFiltrados(List<Repuesto> productosFiltrados) {
        this.productosFiltrados = productosFiltrados;
    }

    public List<DetalleOperacion> getListaReporte() {
        List<DetalleOperacion> listaReporteTemp = new ArrayList<>();
        List<DetalleVenta> listaDetalle = new ArrayList<>();
        listaDetalle = getDetalleVentasSinDistint();
        
        for (DetalleVenta dv : listaDetalle) {
            if(dv.getComprobanteVenta().getIdComprobanteVenta() == this.comprobanteVenta.getIdComprobanteVenta()){
                listaReporteTemp.add(dv.getDetalleOperacion());
            }
        }
        
        listaReporte = listaReporteTemp;
        return listaReporte;
    }

    public void setListaReporte(List<DetalleOperacion> listaReporte) {
        this.listaReporte = listaReporte;
    }

    public DetalleOperacionReporte getDetalleOperacionReporte() {        
        return detalleOperacionReporte;
    }

    public void setDetalleOperacionReporte(DetalleOperacionReporte detalleOperacionReporte) {
        this.detalleOperacionReporte = detalleOperacionReporte;
    }

    public List<DetalleOperacionReporte> getDetalleOperacionReportes() {
        for(DetalleOperacion dop : listaReporte){
            detalleOperacionReporte.setCantidad(dop.getCantidad());
            detalleOperacionReporte.setDescripcion(dop.getProducto().getDescripcion());
            detalleOperacionReporte.setPrecio(dop.getPrecio());
            detalleOperacionReporte.setSubTotal(dop.getSubTotal());
            detalleOperacionReportes.add(detalleOperacionReporte);
        }
        return detalleOperacionReportes;
    }

    public void setDetalleOperacionReportes(List<DetalleOperacionReporte> detalleOperacionReportes) {
        this.detalleOperacionReportes = detalleOperacionReportes;
    }

    public String getUltimoBoleta() {
        String numero = ultimoNumeroBoleta();
        numeroTemp = numero;
        return numero;
    }

    public void setUltimoBoleta(String ultimoBoleta) {
        this.ultimoBoleta = ultimoBoleta;
    }

    public String getUltimoFactura() {
        String numero = ultimoNumeroFactura();
        numeroTemp = numero;
        return numero;
    }

    public void setUltimoFactura(String ultimoFactura) {
        this.ultimoFactura = ultimoFactura;
    }
}