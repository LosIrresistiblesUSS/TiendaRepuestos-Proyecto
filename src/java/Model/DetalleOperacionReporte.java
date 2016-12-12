package Model;

public class DetalleOperacionReporte {
    private Integer idDetalleOperacion;
    private Integer cantidad;
    private Double precio;
    private Double subTotal;
    private String descripcion;

    public DetalleOperacionReporte() {
    }

    public DetalleOperacionReporte(Integer idDetalleOperacion, Integer cantidad, Double precio, Double subTotal, String descripcion) {
        this.idDetalleOperacion = idDetalleOperacion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
        this.descripcion = descripcion;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
