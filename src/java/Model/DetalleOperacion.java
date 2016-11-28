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
@Table(name="DetalleOperacion")
public class DetalleOperacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idDetalleOperacion")
    private Integer idDetalleOperacion;
    
    @Column(name="cantidad")
    private Integer cantidad;
    
    @Column(name="precio")
    private double precio;
    
    @Column(name="subTotal")
    private double subTotal;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idOperacion")
    private Operacion operacion;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idProducto")
    private Producto producto;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idDetalleOperacion")
    private List<DetalleVenta> detalleVentaList = new ArrayList<>();

    public DetalleOperacion() {
    }

    public DetalleOperacion(Integer idDetalleOperacion, Integer cantidad, double precio, double subTotal, Operacion operacion, Producto producto) {
        this.idDetalleOperacion = idDetalleOperacion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
        this.operacion = operacion;
        this.producto = producto;
    }

    public Integer getIdDetalleOperacion() {
        return idDetalleOperacion;
    }

    public void setIdDetalleOperacion(Integer idDetalleOperacion) {
        this.idDetalleOperacion = idDetalleOperacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }
}