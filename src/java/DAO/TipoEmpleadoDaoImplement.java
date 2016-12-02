package DAO;

import Model.TipoEmpleado;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TipoEmpleadoDaoImplement implements TipoEmpleadoDao{

    @Override
    public List<TipoEmpleado> mostrarTipoEmpleados(String busqueda) {
        Session session=null;
        List<TipoEmpleado> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("buquedaPorDescripcionTipoEmpleado").setParameter("descrip", "%"+busqueda+"%");
            lista=(List<TipoEmpleado>) query.list();
            
        }catch (HibernateException e) {
            System.out.println("Error en Método 'mostrarTipoEmpleados': "+ e.getMessage());
        }finally{
            if(session!=null){
            session.close();
            }
        }
        return lista;  
    }

    @Override
    public int insertarTipoEmpleado(TipoEmpleado tipoEmpleado) {
        Session session=null;
        List<TipoEmpleado> lista2;
        int flag = 0;

      try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            lista2 = mostrarTipoEmpleados("");
            
            if (tipoEmpleado.getDescripcion().equals("")) {
                flag = 2;
            } else {
                for(TipoEmpleado te : lista2) {
                    if (tipoEmpleado.getDescripcion().equals(te.getDescripcion())) {
                        flag = 1;
                    }
                }
            }
            
            session.beginTransaction();
            session.save(tipoEmpleado);
            
            if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarTipoEmpleado': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
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
            System.out.println("Error en Método 'modificarTipoEmpleado': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        } 
    }    
   

    @Override
    public boolean eliminarTipoEmpleado(TipoEmpleado tipoEmpleado) {
        Session session=null;
        boolean flag = false;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tipoEmpleado);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            flag = true;
            System.out.println("Error en Método 'eliminarTipoEmpleado': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    } 
}