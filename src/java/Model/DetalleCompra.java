package Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DetalleCompra")
public class DetalleCompra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idDetalleCompra")
    private Integer idDetalleCompra;
    
    @Column(name="cantidad")
    private Integer cantidad;
    
    @Column(name="montoTotal")
    private double montoTotal;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idComprobateCompra")
    private ComprobanteCompra comprobanteCompra;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idProductoRepuesto")
    private Repuesto repuesto;

    public DetalleCompra() {
    }

    public DetalleCompra(Integer idDetalleCompra, Integer cantidad, double montoTotal, ComprobanteCompra comprobanteCompra, Repuesto repuesto) {
        this.idDetalleCompra = idDetalleCompra;
        this.cantidad = cantidad;
        this.montoTotal = montoTotal;
        this.comprobanteCompra = comprobanteCompra;
        this.repuesto = repuesto;
    }

    public Integer getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Integer idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public ComprobanteCompra getComprobanteCompra() {
        return comprobanteCompra;
    }

    public void setComprobanteCompra(ComprobanteCompra comprobanteCompra) {
        this.comprobanteCompra = comprobanteCompra;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }
}
