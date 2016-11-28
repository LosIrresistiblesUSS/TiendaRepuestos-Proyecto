package DAO;

import Model.TipoServicio;
import java.util.List;

public interface TipoServicioDao {
    public List<TipoServicio> mostrarTipoServicios(String busqueda);
    public int insertarTipoServicio(TipoServicio tipoServicio);
    public void modificarTipoServicio(TipoServicio tipoServicio);
    public boolean eliminarTipoServicio(TipoServicio tipoServicio);  
}