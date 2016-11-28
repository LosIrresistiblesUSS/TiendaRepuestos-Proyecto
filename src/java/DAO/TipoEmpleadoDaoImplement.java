package DAO;

import Model.TipoEmpleado;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TipoEmpleadoDaoImplement implements TipoEmpleadoDao{

    @Override
    public List<TipoEmpleado> mostrarTipoEmpleados() {
        Session session=null;
        List<TipoEmpleado> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query=session.createQuery("from TipoEmpleado");
            lista=(List<TipoEmpleado>) query.list();
            
            System.out.println("HOLAAAAAAAAA: "+lista.get(0).getDescripcion());
            
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }finally{
            if(session!=null){
            session.close();
            }
        }
        return lista;  
    }

    @Override
    public void insertarTipoEmpleado(TipoEmpleado tipoEmpleado) {
        Session session=null;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(tipoEmpleado);
            session.getTransaction().commit();
            
        }catch (HibernateException e) {
            System.out.println("TIPO EMPLEADO" + e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void modificarTipoEmpleado(TipoEmpleado tipoEmpleado) {
       Session session=null;
       
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(tipoEmpleado);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        } 
    }    
   

    @Override
    public void eliminarTipoEmpleado(TipoEmpleado tipoEmpleado) {
        Session session=null;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tipoEmpleado);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    } 
}