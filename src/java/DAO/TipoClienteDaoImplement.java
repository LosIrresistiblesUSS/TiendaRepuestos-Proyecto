package DAO;

import Model.TipoCliente;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TipoClienteDaoImplement implements TipoClienteDao{

    @Override
    public List<TipoCliente> mostrarTipoClientes(String busqueda) {
        Session session=null;
        List<TipoCliente> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query=session.getNamedQuery("busquedaPorDescripcionTipoCliente").setParameter("descrip", "%"+busqueda+"%");
            lista=(List<TipoCliente>) query.list();
               
        }catch (HibernateException e) {
            System.out.println("Error en Método 'mostrarTipoClientes': "+ e.getMessage());
        }finally{
            if(session!=null){
            session.close();
            }
        }
        return lista;  
    }

    @Override
    public int insertarTipoCliente(TipoCliente tipoCliente) {
        Session session=null;
        List<TipoCliente> lista2;
        int flag = 0;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            lista2 = mostrarTipoClientes("");
            
            if (tipoCliente.getDescripcion().equals("")) {
                flag = 2;
            } else {
                for(TipoCliente cl : lista2) {
                    if (tipoCliente.getDescripcion().equals(cl.getDescripcion())) {
                        flag = 1;
                    }
                }
            }
            session.beginTransaction();
            session.save(tipoCliente);
            
               if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
            
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarTipoCliente':" + e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
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
            System.out.println("Error en Método 'modificarTipoCliente': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        } 
    }    
   

    @Override
    public boolean eliminarTipoCliente(TipoCliente tipoCliente) {
        Session session=null;
        boolean flag = false;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tipoCliente);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            flag = true;
            System.out.println("Error en Método 'eliminarTipoCliente': "+e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    } 
}