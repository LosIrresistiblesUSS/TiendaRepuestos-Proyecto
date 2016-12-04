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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({@NamedQuery(name="busquedaPorDescripcionTipoCliente", query="from TipoCliente as cl where cl.descripcion like :descrip order by cl.idTipoCliente desc")})
@Entity
@Table(name="TipoCliente")
public class TipoCliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idTipoCliente")
    private Integer idTipoCliente;
    
    @Column(name="descripcion")
    private String descripcion;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idTipoCliente")
    private List<Cliente> clienteList = new ArrayList<>();

    public TipoCliente() {
    }

    public TipoCliente(Integer idTipoCliente, String descripcion) {
        this.idTipoCliente = idTipoCliente;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(Integer idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }    
}