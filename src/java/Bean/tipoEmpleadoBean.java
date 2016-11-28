package Bean;

import DAO.TipoEmpleadoDao;
import DAO.TipoEmpleadoDaoImplement;
import Model.TipoEmpleado;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class tipoEmpleadoBean {
    TipoEmpleado tipoEmpleado;
    List<TipoEmpleado> tipoEmpleados;

    public tipoEmpleadoBean() {
        tipoEmpleado = new TipoEmpleado();
    }
    
    public void insertar(){
    TipoEmpleadoDao linkDAO= new TipoEmpleadoDaoImplement();
        linkDAO.insertarTipoEmpleado(tipoEmpleado);
        tipoEmpleado= new TipoEmpleado();
    }
    
    public void modificar(){
    TipoEmpleadoDao linkDAO= new TipoEmpleadoDaoImplement();
        linkDAO.modificarTipoEmpleado(tipoEmpleado);
        tipoEmpleado= new TipoEmpleado();
    }
    
    public void eliminar(){
    TipoEmpleadoDao linkDAO= new TipoEmpleadoDaoImplement();
        linkDAO.eliminarTipoEmpleado(tipoEmpleado);
        tipoEmpleado= new TipoEmpleado();
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public List<TipoEmpleado> getTipoEmpleados() {
        TipoEmpleadoDao linkDAO= new TipoEmpleadoDaoImplement();
        tipoEmpleados=linkDAO.mostrarTipoEmpleados();
        return tipoEmpleados;
    }

    public void setTipoEmpleados(List<TipoEmpleado> tipoEmpleados) {
        this.tipoEmpleados = tipoEmpleados;
    } 
}