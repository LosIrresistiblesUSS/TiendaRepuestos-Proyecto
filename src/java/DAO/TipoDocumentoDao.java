package DAO;

import Model.TipoDocumento;
import java.util.List;

public interface TipoDocumentoDao {
  public List<TipoDocumento> mostrarTipoDocumentos(String busqueda);
    public int insertarTipoDocumento(TipoDocumento tipoDocumento);
    public void modificarTipoDocumento(TipoDocumento tipoDocumento);
    public boolean eliminarTipoDocumento(TipoDocumento tipoDocumento);  
}