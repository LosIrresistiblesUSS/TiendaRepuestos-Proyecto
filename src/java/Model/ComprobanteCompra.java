package Model;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name="ComprobanteCompra")
public class ComprobanteCompra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idComprobanteCompra")
    private Integer idComprobanteCompra;
    
    @Column(name="fecha")
    private Date fecha;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idPersonaProveedor")
    private Proveedor proveedor;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idComprobanteCompra")
    private List<DetalleCompra> detalleCompraList = new ArrayList<>();

    public ComprobanteCompra() {
    }

    public ComprobanteCompra(Integer idComprobanteCompra, Date fecha, Proveedor proveedor) {
        this.idComprobanteCompra = idComprobanteCompra;
        this.fecha = fecha;
        this.proveedor = proveedor;
    }

    public Integer getIdComprobanteCompra() {
        return idComprobanteCompra;
    }

    public void setIdComprobanteCompra(Integer idComprobanteCompra) {
        this.idComprobanteCompra = idComprobanteCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetalleCompra> getDetalleCompraList() {
        return detalleCompraList;
    }

    public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
        this.detalleCompraList = detalleCompraList;
    }   
}
