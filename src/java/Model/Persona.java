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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Persona")
public class Persona implements Serializable {
    
    @Id
    @Column(name="idPersona")
    private Integer idPersona;
    
    @Size(min = 1, max = 13)
    @Column(name="numeroDocumento", nullable = false, length = 13)
    private String numeroDocumento;
    
    @Column(name="nombres")
    private String nombres;
    
    @Column(name="direccion")
    private String direccion;
    
    @Size(min = 1, max = 10)
    @Column(name="telefono", nullable = false, length = 10)
    private String telefono;
    
    @Column(name="email")
    private String email;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idTipoDocumento")
    private TipoDocumento tipoDocumento;
    
    //-----One to One-----//
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "persona")
    private Empleado empleado;
    
    //-----One to One-----//
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "persona")
    private Cliente cliente;
    
    //-----One to One-----//
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "persona")
    private Proveedor proveedor;

    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idPersona")
    private List<Operacion> operacionClienteList = new ArrayList<>();
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idPersona")
    private List<Operacion> operacionEmpleadoList = new ArrayList<>();
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idPersona")
    private List<Login> loginList = new ArrayList<>();
    
    public Persona() {
    }

    public Persona(Integer idPersona, String numeroDocumento, String nombres, String direccion, String telefono, String email, TipoDocumento tipoDocumento, Empleado empleado, Cliente cliente, Proveedor proveedor) {
        this.idPersona = idPersona;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.tipoDocumento = tipoDocumento;
        this.empleado = empleado;
        this.cliente = cliente;
        this.proveedor = proveedor;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Operacion> getOperacionClienteList() {
        return operacionClienteList;
    }

    public void setOperacionClienteList(List<Operacion> operacionClienteList) {
        this.operacionClienteList = operacionClienteList;
    }

    public List<Operacion> getOperacionEmpleadoList() {
        return operacionEmpleadoList;
    }

    public void setOperacionEmpleadoList(List<Operacion> operacionEmpleadoList) {
        this.operacionEmpleadoList = operacionEmpleadoList;
    }

    public List<Login> getLoginList() {
        return loginList;
    }

    public void setLoginList(List<Login> loginList) {
        this.loginList = loginList;
    }
}