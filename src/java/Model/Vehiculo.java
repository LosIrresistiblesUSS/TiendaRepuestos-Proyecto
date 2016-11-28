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
import javax.validation.constraints.Size;

@Entity
@Table(name="Vehiculo")
public class Vehiculo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idVehiculo")
    private Integer idVehiculo;
    
    @Size(min = 1, max = 10)
    @Column(name="placa", nullable = false, length = 10)
    private String placa;
    
    @Size(min = 1, max = 25)
    @Column(name="marca", nullable = false, length = 25)
    private String marca;
    
    @Column(name="modelo")
    private String modelo;
    
    @Column(name="observaciones")
    private String observaciones;
    
    //-----Many To One-----//
    @ManyToOne
    @JoinColumn(name="idPersonaCliente")
    private Cliente cliente;
    
    //-----One to Many-----//
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="idVehiculo")
    private List<Operacion> operacionList = new ArrayList<>();

    public Vehiculo() {
    }

    public Vehiculo(Integer idVehiculo, String placa, String marca, String modelo, String observaciones, Cliente cliente) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.observaciones = observaciones;
        this.cliente = cliente;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Operacion> getOperacionList() {
        return operacionList;
    }

    public void setOperacionList(List<Operacion> operacionList) {
        this.operacionList = operacionList;
    }
}