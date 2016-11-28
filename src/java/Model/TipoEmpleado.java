package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TipoEmpleado")
public class TipoEmpleado implements Serializable {
    
    @Id
    @Column(name="idTipoEmpleado", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTipoEmpleado;
    
    @Column(name="descripcion")
    private String descripcion;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idTipoEmpleado")
    private List<Empleado> empleadoList = new ArrayList<>();

    public TipoEmpleado() {
    }

    public TipoEmpleado(Integer idTipoEmpleado, String descripcion) {
        this.idTipoEmpleado = idTipoEmpleado;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }
}