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
@Table(name="Permiso")
public class Permiso implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idPermiso")
    private Integer idPermiso;
    
    @Column(name="descripcion")
    private String descripcion;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idPermiso")
    private List<DetallePermiso> detallePermisoList = new ArrayList<>();

    public Permiso() {
    }

    public Permiso(Integer idPermiso, String descripcion) {
        this.idPermiso = idPermiso;
        this.descripcion = descripcion;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetallePermiso> getDetallePermisoList() {
        return detallePermisoList;
    }

    public void setDetallePermisoList(List<DetallePermiso> detallePermisoList) {
        this.detallePermisoList = detallePermisoList;
    }
}