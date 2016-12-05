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
import javax.validation.constraints.Size;

@Entity
@Table(name="ComprobanteVenta")
public class ComprobanteVenta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idComprobanteVenta")
    private Integer idComprobanteVenta;
    
    @Size(min = 1, max = 15)
    @Column(name="numero", nullable = false, length = 15)
    private String numero;
    
    @Column(name="fecha")
    private Date fecha;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="importe")
    private double importe;
    
    @Column(name="estado")
    private boolean estado;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idTipoComprobanteVenta")
    private TipoComprobanteVenta tipoComprobanteVenta;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idComprobanteVenta")
    private List<DetalleVenta> detalleVentaList = new ArrayList<>();

    public ComprobanteVenta() {   
    }

    public ComprobanteVenta(Integer idComprobanteVenta, String numero, Date fecha, String descripcion, double importe, boolean estado, TipoComprobanteVenta tipoComprobanteVenta) {
        this.idComprobanteVenta = idComprobanteVenta;
        this.numero = numero;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.importe = importe;
        this.estado = estado;
        this.tipoComprobanteVenta = tipoComprobanteVenta;
    }

    public Integer getIdComprobanteVenta() {
        return idComprobanteVenta;
    }

    public void setIdComprobanteVenta(Integer idComprobanteVenta) {
        this.idComprobanteVenta = idComprobanteVenta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public TipoComprobanteVenta getTipoComprobanteVenta() {
        return tipoComprobanteVenta;
    }

    public void setTipoComprobanteVenta(TipoComprobanteVenta tipoComprobanteVenta) {
        this.tipoComprobanteVenta = tipoComprobanteVenta;
    }
}
