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
@Table(name="Kardex")
public class Kardex implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idKardex")
    private Integer idKardex;
    
    @Column(name="detalle")
    private String detalle;
    
    @Column(name="cantidadIngreso")
    private Integer cantidadIngreso;
    
    @Column(name="precioIngreso")
    private double precioIngreso;
    
    @Column(name="cantidadSalida")
    private Integer cantidadSalida;
    
    @Column(name="precioSalida")
    private double precioSalida;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idProductoRepuesto")
    private Repuesto repuesto;

    public Kardex() {
    }

    public Kardex(Integer idKardex, String detalle, Integer cantidadIngreso, double precioIngreso, Integer cantidadSalida, double precioSalida, Repuesto repuesto) {
        this.idKardex = idKardex;
        this.detalle = detalle;
        this.cantidadIngreso = cantidadIngreso;
        this.precioIngreso = precioIngreso;
        this.cantidadSalida = cantidadSalida;
        this.precioSalida = precioSalida;
        this.repuesto = repuesto;
    }

    public Integer getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(Integer idKardex) {
        this.idKardex = idKardex;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getCantidadIngreso() {
        return cantidadIngreso;
    }

    public void setCantidadIngreso(Integer cantidadIngreso) {
        this.cantidadIngreso = cantidadIngreso;
    }

    public double getPrecioIngreso() {
        return precioIngreso;
    }

    public void setPrecioIngreso(double precioIngreso) {
        this.precioIngreso = precioIngreso;
    }

    public Integer getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(Integer cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(double precioSalida) {
        this.precioSalida = precioSalida;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }
}