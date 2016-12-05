package Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name="busquedaPorNombreVentaRepuesto", query="from DetalleVenta as dv where dv.detalleOperacion.operacion.personaCliente.nombres like :descrip order by dv.comprobanteVenta.idComprobanteVenta desc"),
    @NamedQuery(name="ultimoIdDetalleVenta", query="from DetalleVenta as dv order by dv.idDetalleVenta desc")
})
@Entity
@Table(name="DetalleVenta")
public class DetalleVenta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idDetalleVenta")
    private Integer idDetalleVenta;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idComprobanteVenta")
    private ComprobanteVenta comprobanteVenta;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idDetalleOperacion")
    private DetalleOperacion detalleOperacion;
    
    public DetalleVenta() {
    }

    public DetalleVenta(Integer idDetalleVenta, DetalleOperacion detalleOperacion, ComprobanteVenta comprobanteVenta) {
        this.idDetalleVenta = idDetalleVenta;
        this.detalleOperacion = detalleOperacion;
        this.comprobanteVenta = comprobanteVenta;
    }

    public Integer getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Integer idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public DetalleOperacion getDetalleOperacion() {
        return detalleOperacion;
    }

    public void setDetalleOperacion(DetalleOperacion detalleOperacion) {
        this.detalleOperacion = detalleOperacion;
    }

    public ComprobanteVenta getComprobanteVenta() {
        return comprobanteVenta;
    }

    public void setComprobanteVenta(ComprobanteVenta comprobanteVenta) {
        this.comprobanteVenta = comprobanteVenta;
    }
}