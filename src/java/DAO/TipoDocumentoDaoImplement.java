package DAO;

import Model.TipoDocumento;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TipoDocumentoDaoImplement implements TipoDocumentoDao{

    @Override
    public List<TipoDocumento> mostrarTipoDocumentos(String busqueda) {
        Session session=null;
        List<TipoDocumento> lista=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("buquedaPorDescripcionTipoDocumento").setParameter("descrip", "%"+busqueda+"%");
            lista=(List<TipoDocumento>) query.list();
                        
        }catch (HibernateException e) {
            System.out.println("Error en Método 'mostrarTipoDocumentos': "+ e.getMessage());
        }finally{
            if(session!=null){
            session.close();
            }
        }
        return lista;  
    }

    @Override
    public int insertarTipoDocumento(TipoDocumento tipoDocumento) {
        Session session=null;
        List<TipoDocumento> lista2;
        int flag = 0;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            lista2 = mostrarTipoDocumentos("");
            
            if (tipoDocumento.getDescripcion().equals("")) {
                flag = 2;
            } else {
                for(TipoDocumento td : lista2) {
                    if (tipoDocumento.getDescripcion().equals(td.getDescripcion())) {
                        flag = 1;
                    }
                }
            }
            
            session.beginTransaction();
            session.save(tipoDocumento);
            
            if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarTipoDocumento': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    }

    @Override
    public void modificarTipoDocumento(TipoDocumento tipoDocumento) {
       Session session=null;
       
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(tipoDocumento);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            System.out.println("Error en Método 'modificarTipoDocumento': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }  
    }

    @Override
    public boolean eliminarTipoDocumento(TipoDocumento tipoDocumento) {
        Session session=null;
        boolean flag = false;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tipoDocumento);
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