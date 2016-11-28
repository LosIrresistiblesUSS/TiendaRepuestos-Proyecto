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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({@NamedQuery(name="buquedaPorDescripcionTipoServicio", query="from TipoServicio as ts where ts.descripcion like :descrip order by ts.idTipoServicio desc")})
@Entity
@Table(name="TipoServicio")
public class TipoServicio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idTipoServicio")
    private Integer idTipoServicio;
    
    @Column(name="descripcion")
    private String descripcion;
    
    //-----One to Many-----
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idTipoServicio")
    private List<Servicio> servicioList = new ArrayList<>();
    
    public TipoServicio() {
    }

    public TipoServicio(Integer idTipoServicio, String descripcion) {
        this.idTipoServicio = idTipoServicio;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }
}