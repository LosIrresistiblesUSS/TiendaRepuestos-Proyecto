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
@Table(name="TipoComprobanteVenta")
public class TipoComprobanteVenta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idTipoComprobanteVenta")
    private Integer idTipoComprobanteVenta;
    
    @Column(name="descripcion")
    private String descripcion;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idTipoComprobanteVenta")
    private List<ComprobanteVenta> comprobanteVentaList = new ArrayList<>();

    public TipoComprobanteVenta() {
    }

    public TipoComprobanteVenta(Integer idTipoComprobanteVenta, String descripcion) {
        this.idTipoComprobanteVenta = idTipoComprobanteVenta;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoComprobanteVenta() {
        return idTipoComprobanteVenta;
    }

    public void setIdTipoComprobanteVenta(Integer idTipoComprobanteVenta) {
        this.idTipoComprobanteVenta = idTipoComprobanteVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ComprobanteVenta> getComprobanteVentaList() {
        return comprobanteVentaList;
    }

    public void setComprobanteVentaList(List<ComprobanteVenta> comprobanteVentaList) {
        this.comprobanteVentaList = comprobanteVentaList;
    }
}