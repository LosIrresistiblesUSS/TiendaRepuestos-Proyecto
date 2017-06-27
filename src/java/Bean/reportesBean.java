package Bean;

import Helpers.Conexion;
import Model.Login;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean
@ViewScoped
public class reportesBean {
    private Conexion con;
    private Connection cn;
    
    public void reporteCliente() throws JRException, IOException{
        
        con = new Conexion();
        cn = con.getConexion();
        
        Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        Map<String,Object> parametros= new HashMap<String,Object>();
        parametros.put("empleado", user.getPersona().getEmpleado().getApellidos() + " ");
        
        try {
            JRDataSource vacio = new JREmptyDataSource();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ReporteClientes.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, cn);
            
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition","attachment; filename=ReporteClientes.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
            System.out.println("ERROOOOOOOOR: "+ e.getMessage());
        }
    }
    
    public void reporteRepuestos() throws JRException, IOException{
        
        con = new Conexion();
        cn = con.getConexion();
        
        Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        Map<String,Object> parametros= new HashMap<String,Object>();
        parametros.put("empleado", user.getPersona().getEmpleado().getApellidos() + " ");
        
        try {
            JRDataSource vacio = new JREmptyDataSource();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ReporteRepuestos.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, cn);
            
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition","attachment; filename=ReporteRepuestos.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
            System.out.println("ERROOOOOOOOR: "+ e.getMessage());
        }
    }
    
    public void reporteBoletas() throws JRException, IOException{
        
        con = new Conexion();
        cn = con.getConexion();
        
        Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        Map<String,Object> parametros= new HashMap<String,Object>();
        parametros.put("empleado", user.getPersona().getEmpleado().getApellidos() + " ");
        
        try {
            JRDataSource vacio = new JREmptyDataSource();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ReporteBoleta.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, cn);
            
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition","attachment; filename=ReporteBoleta.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
            System.out.println("ERROOOOOOOOR: "+ e.getMessage());
        }
    }
    
    public void reporteFacturas() throws JRException, IOException{
        
        con = new Conexion();
        cn = con.getConexion();
        
        Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        Map<String,Object> parametros= new HashMap<String,Object>();
        parametros.put("empleado", user.getPersona().getEmpleado().getApellidos() + " ");
        
        try {
            JRDataSource vacio = new JREmptyDataSource();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ReporteFactura.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, cn);
            
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition","attachment; filename=ReporteFactura.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
            System.out.println("ERROOOOOOOOR: "+ e.getMessage());
        }
    }
}
