package DAO;

import Model.TipoCliente;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TipoClienteDaoImplement implements TipoClienteDao{

    @Override
    public List<TipoCliente> mostrarTipoClientes() {
        Session session=null;
        List<TipoCliente> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query=session.createQuery("from TipoCliente");
            lista=(List<TipoCliente>) query.list();
            
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
    public void insertarTipoCliente(TipoCliente tipoCliente) {
        Session session=null;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(tipoCliente);
            session.getTransaction().commit();
            
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void modificarTipoCliente(TipoCliente tipoCliente) {
       Session session=null;
       
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(tipoCliente);
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
    public void eliminarTipoCliente(TipoCliente tipoCliente) {
        Session session=null;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tipoCliente);
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