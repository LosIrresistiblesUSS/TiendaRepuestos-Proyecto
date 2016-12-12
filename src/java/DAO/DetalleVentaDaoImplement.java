package DAO;

import Model.ComprobanteVenta;
import Model.DetalleOperacion;
import Model.DetalleVenta;
import Model.Operacion;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class DetalleVentaDaoImplement implements DetalleVentaDao {

    @Override
    public List<DetalleVenta> mostrarVentaConDistint(String busqueda) {
        Session session=null;
        List<DetalleVenta> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("busquedaPorNombreVentaRepuestoConDistint").setParameter("descrip", "%"+busqueda+"%");
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
    public List<DetalleVenta> mostrarVentaSinDistint(String busqueda) {
        Session session=null;
        List<DetalleVenta> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("busquedaPorNombreVentaRepuestoSinDistint").setParameter("descrip", "%"+busqueda+"%");
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
    public int insertarOperacion(Operacion operacion) {
        Session session=null;
       
        int flag = 0;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            session.save(operacion);
            
            if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarOperacion': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    }

    @Override
    public int insertarComprobanteVenta(ComprobanteVenta comprobanteVenta) {
        Session session=null;
       
        int flag = 0;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            session.save(comprobanteVenta);
            
            if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarComprobanteVenta': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    }

    @Override
    public int insertarDetalleOperacion(DetalleOperacion detalleOperacion) {
        Session session=null;
       
        int flag = 0;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            session.save(detalleOperacion);
            
            if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarDetalleOperacion': "+ e.getMessage());
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

    @Override
    public int ultimoIdOperacion() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("ultimoIdOperacion");
        query.setMaxResults(1);
        Operacion ope = (Operacion) query.list().get(0);
        int numero = ope.getIdOperacion()+1;
        return numero;
    }

    @Override
    public int ultimoIdComprobanteVenta() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("ultimoIdComprobanteVenta");
        query.setMaxResults(1);
        ComprobanteVenta cv = (ComprobanteVenta) query.list().get(0);
        int numero = cv.getIdComprobanteVenta()+1;
        return numero;
    }

    @Override
    public int ultimoIdDetalleOperacion() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("ultimoIdDetalleOperacion");
        query.setMaxResults(1);
        DetalleOperacion dop = (DetalleOperacion) query.list().get(0);
        int numero = dop.getIdDetalleOperacion()+1;
        return numero;
    }

    @Override
    public int ultimoIdDetalleVenta() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("ultimoIdDetalleVenta");
        query.setMaxResults(1);
        DetalleVenta dv = (DetalleVenta) query.list().get(0);
        int numero = dv.getIdDetalleVenta()+1;
        return numero;
    }
}