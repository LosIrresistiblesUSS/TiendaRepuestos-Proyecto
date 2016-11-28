package DAO;

import Model.TipoServicio;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TipoServicioDaoImplement implements TipoServicioDao{

    @Override
    public List<TipoServicio> mostrarTipoServicios(String busqueda) {
        Session session=null;
        List<TipoServicio> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("buquedaPorDescripcionTipoServicio").setParameter("descrip", "%"+busqueda+"%");
            lista=(List<TipoServicio>) query.list();
            
        }catch (HibernateException e) {
            System.out.println("Error en Método 'mostrarTipoServicios': "+ e.getMessage());
        }finally{
            if(session!=null){
            session.close();
            }
        }
        return lista;  
    }

    @Override
    public int insertarTipoServicio(TipoServicio tipoServicio) {
        Session session=null;
        List<TipoServicio> lista2;
        int flag = 0;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            lista2 = mostrarTipoServicios("");
            
            if (tipoServicio.getDescripcion().equals("")) {
                flag = 2;
            } else {
                for(TipoServicio ts : lista2) {
                    if (tipoServicio.getDescripcion().equals(ts.getDescripcion())) {
                        flag = 1;
                    }
                }
            }
            
            session.beginTransaction();
            session.save(tipoServicio);
            
            if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarTipoServicio': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    }

    @Override
    public void modificarTipoServicio(TipoServicio tipoServicio) {
        Session session=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();    
            session.beginTransaction();
            session.update(tipoServicio);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            System.out.println("Error en Método 'modificarTipoServicio': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }    

    @Override
    public boolean eliminarTipoServicio(TipoServicio tipoServicio) {
        Session session=null;
        boolean flag = false;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tipoServicio);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            flag = true;
            System.out.println("Error en Método 'eliminarTipoServicio': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    } 
}