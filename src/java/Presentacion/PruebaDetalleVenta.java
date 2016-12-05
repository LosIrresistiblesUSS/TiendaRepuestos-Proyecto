package Presentacion;

import Model.DetalleVenta;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PruebaDetalleVenta {
    public static void main(String[] args) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("busquedaPorNombreVentaRepuesto").setParameter("descrip", "%%");
        
        List<DetalleVenta> lista = query.list();
        
        for(DetalleVenta dv : lista){
            System.out.println("Venta " + dv.getIdDetalleVenta()+ ": " + dv.getDetalleOperacion().getOperacion().getPersonaCliente().getNombres());
        }
        
        session.close();
    }
}