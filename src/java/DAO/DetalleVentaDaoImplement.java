package DAO;

import Model.DetalleVenta;
import Model.TipoServicio;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class DetalleVentaDaoImplement implements DetalleVentaDao {

    @Override
    public List<DetalleVenta> mostrarVenta(String busqueda) {
        Session session=null;
        List<DetalleVenta> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("busquedaPorNombreVentaRepuesto").setParameter("descrip", "%"+busqueda+"%");
            lista=(List<DetalleVenta>) query.list();
            
        }catch (HibernateException e) {
            System.out.println("Error en Método 'mostrarVenta': "+ e.getMessage());
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return lista;
    }

    @Override
    public int insertarVenta(DetalleVenta detalleVenta) {
        Session session=null;
       
        int flag = 0;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            
            session.beginTransaction();
            session.save(detalleVenta);
            
            if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarVenta': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    }

    @Override
    public void cambiarEstadoVenta(DetalleVenta detalleVenta) {
        Session session=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();    
            
            if (detalleVenta.getComprobanteVenta().getEstado() == true) {
                detalleVenta.getComprobanteVenta().setEstado(false);
            } else {
                detalleVenta.getComprobanteVenta().setEstado(true);
            }
            session.beginTransaction();
            session.update(detalleVenta.getComprobanteVenta());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Error en Método 'cambiarEstadoVenta': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }
    
}