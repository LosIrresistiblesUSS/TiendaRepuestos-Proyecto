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
import org.hibernate.annotations.NamedQuery;

@NamedQuery(name="ultimoIdProducto", query="from Producto as p order by p.idProducto desc")
@Entity
@Table(name="Producto")
public class Producto implements Serializable {
    
    @Id
    @Column(name="idProducto")
    private Integer idProducto;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="stock")
    private Integer stock;
    
    @Column(name="precio")
    private Double precio;
    
    @Column(name="precioPorMayor")
    private Double precioPorMayor;
    
    //-----One to One-----//
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="idProducto")
    private Repuesto repuesto;
    
    //-----One to One-----//
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="idProducto")
    private Servicio servicio;

    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idProducto")
    private List<DetalleOperacion> detalleOperacionList = new ArrayList<>();
    
    public Producto() {
    }

    public Producto(Integer idProducto, String descripcion, Integer stock, Double precio, Double precioPorMayor, Repuesto repuesto, Servicio servicio) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.precioPorMayor = precioPorMayor;
        this.repuesto = repuesto;
        this.servicio = servicio;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecioPorMayor() {
        return precioPorMayor;
    }

    public void setPrecioPorMayor(Double precioPorMayor) {
        this.precioPorMayor = precioPorMayor;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public List<DetalleOperacion> getDetalleOperacionList() {
        return detalleOperacionList;
    }

    public void setDetalleOperacionList(List<DetalleOperacion> detalleOperacionList) {
        this.detalleOperacionList = detalleOperacionList;
    }
}