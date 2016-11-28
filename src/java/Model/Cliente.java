package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cliente")
public class Cliente implements Serializable {
    
    @Id
    @Column(name="idPersona")
    private Integer idPersona;
    
    @Column(name="apellidos")
    private String apellidos;
    
    @Column(name="razonSocial")
    private String razonSocial;
    
     //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idTipoCliente")
    private TipoCliente tipoCliente;

    //-----One to One-----//
    @JoinColumn(name="idPersona",
            referencedColumnName = "idPersona",
            insertable = false,
            updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
   
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idPersona")
    private List<Vehiculo> vehiculoList = new ArrayList<>();

    public Cliente() {
    }
    
    public Cliente(Integer idPersona, String apellidos, String razonSocial, Persona persona, TipoCliente tipoCliente) {
        this.idPersona = idPersona;
        this.apellidos = apellidos;
        this.razonSocial = razonSocial;
        this.persona = persona;
        this.tipoCliente = tipoCliente;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }
}