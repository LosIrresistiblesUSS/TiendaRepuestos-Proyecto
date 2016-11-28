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
@Table(name="DetallePermiso")
public class DetallePermiso implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idDetallePermiso")
    private Integer idDetallePermiso;
    
    @Column(name="accion")
    private char accion;
    
    @Column(name="estado")
    private boolean estado;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idLogin")
    private Login login;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idPermiso")
    private Permiso permiso;

    public DetallePermiso() {
    }

    public DetallePermiso(Integer idDetallePermiso, char accion, boolean estado, Login login, Permiso permiso) {
        this.idDetallePermiso = idDetallePermiso;
        this.accion = accion;
        this.estado = estado;
        this.login = login;
        this.permiso = permiso;
    }

    public Integer getIdDetallePermiso() {
        return idDetallePermiso;
    }

    public void setIdDetallePermiso(Integer idDetallePermiso) {
        this.idDetallePermiso = idDetallePermiso;
    }

    public char getAccion() {
        return accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
}