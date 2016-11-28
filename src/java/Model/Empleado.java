package Model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Empleado")
public class Empleado implements Serializable {
    
    @Id
    @Column(name="idPersona")
    private Integer idPersona;
    
    @Column(name="apellidos")
    private String apellidos;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idTipoEmpleado")
    private TipoEmpleado tipoEmpleado;
    
    //-----One to One-----//
    @JoinColumn(name="idPersona",
            referencedColumnName = "idPersona",
            insertable = false,
            updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    
    public Empleado() {
    }
    
    public Empleado(Integer idPersona, String apellidos, Persona persona, TipoEmpleado tipoEmpleado) {
        this.idPersona = idPersona;
        this.apellidos = apellidos;
        this.persona = persona;
        this.tipoEmpleado = tipoEmpleado;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}