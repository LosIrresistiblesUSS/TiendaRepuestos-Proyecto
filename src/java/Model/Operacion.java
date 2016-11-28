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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Operacion")
public class Operacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idOperacion")
    private Integer idOperacion;
    
    @Column(name="estado")
    private boolean estado;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idPersonaCliente")
    private Persona personaCliente;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idPersonaEmpleado")
    private Persona personaEmpleado;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idVehiculo")
    private Vehiculo vehiculo;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idOperacion")
    private List<DetalleOperacion> detalleOperacionList = new ArrayList<>();

    public Operacion() {
    }

    public Operacion(Integer idOperacion, boolean estado, Persona personaCliente, Persona personaEmpleado, Vehiculo vehiculo) {
        this.idOperacion = idOperacion;
        this.estado = estado;
        this.personaCliente = personaCliente;
        this.personaEmpleado = personaEmpleado;
        this.vehiculo = vehiculo;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Persona getPersonaCliente() {
        return personaCliente;
    }

    public void setPersonaCliente(Persona personaCliente) {
        this.personaCliente = personaCliente;
    }

    public Persona getPersonaEmpleado() {
        return personaEmpleado;
    }

    public void setPersonaEmpleado(Persona personaEmpleado) {
        this.personaEmpleado = personaEmpleado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<DetalleOperacion> getDetalleOperacionList() {
        return detalleOperacionList;
    }

    public void setDetalleOperacionList(List<DetalleOperacion> detalleOperacionList) {
        this.detalleOperacionList = detalleOperacionList;
    }
}