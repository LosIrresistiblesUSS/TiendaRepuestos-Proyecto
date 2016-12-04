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
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({@NamedQuery(name="busquedaPorDescripcionRepuesto", query="from Repuesto as r where r.producto.descripcion like :descrip order by r.idProducto desc")})
@Entity
@Table(name="Repuesto")
public class Repuesto implements Serializable {
    
    @Id
    @Column(name="idProducto")
    private Integer idProducto;
    
    @Column(name="imagen")
    private String imagen;
    
    //-----One to One-----//
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="idProducto")
    private Producto producto;

    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idProducto")
    private List<Kardex> kardexList = new ArrayList<>();
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idProducto")
    private List<DetalleCompra> detalleCompraList = new ArrayList<>();
    
    public Repuesto() {
    }

    public Repuesto(Integer idProducto, String imagen, Producto producto) {
        this.idProducto = idProducto;
        this.imagen = imagen;
        this.producto = producto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Kardex> getKardexList() {
        return kardexList;
    }

    public void setKardexList(List<Kardex> kardexList) {
        this.kardexList = kardexList;
    }

    public List<DetalleCompra> getDetalleCompraList() {
        return detalleCompraList;
    }

    public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
        this.detalleCompraList = detalleCompraList;
    }
}