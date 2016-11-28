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
@Table(name="Servicio")
public class Servicio implements Serializable {
    
    @Id
    @Column(name="idProducto")
    private Integer idProducto;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idTipoServicio")
    private TipoServicio tipoServico;
    
    //-----One to One-----//
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="idProducto")
    private Producto producto;
    
    public Servicio() {
    }

    public Servicio(Integer idProducto, Producto producto, TipoServicio tipoServico) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.tipoServico = tipoServico;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public TipoServicio getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServicio tipoServico) {
        this.tipoServico = tipoServico;
    }
}