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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Proveedor")
public class Proveedor implements Serializable {
    
    @Id
    @Column(name="idPersona")
    private Integer idPersona;
    
    @Column(name="razonComercial")
    private String razonComercial;
    
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
    private List<ComprobanteCompra> comprobanteCompraList = new ArrayList<>();

    public Proveedor() {
    }

    public Proveedor(Integer idPersona, String razonComercial, Persona persona) {
        this.idPersona = idPersona;
        this.razonComercial = razonComercial;
        this.persona = persona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getRazonComercial() {
        return razonComercial;
    }

    public void setRazonComercial(String razonComercial) {
        this.razonComercial = razonComercial;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<ComprobanteCompra> getComprobanteCompraList() {
        return comprobanteCompraList;
    }

    public void setComprobanteCompraList(List<ComprobanteCompra> comprobanteCompraList) {
        this.comprobanteCompraList = comprobanteCompraList;
    }
}