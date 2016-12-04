package DAO;

import Model.Producto;
import Model.Repuesto;
import Model.TipoServicio;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class RepuestoDaoImplement implements RepuestoDao {

    @Override
    public List<Repuesto> mostrarRepuesto(String busqueda) {
        Session session=null;
        List<Repuesto> lista=null;
        
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("busquedaPorDescripcionRepuesto").setParameter("descrip", "%"+busqueda+"%");
            lista=(List<Repuesto>) query.list();
            
        } catch (Exception e) {
            System.out.println("Error en Método 'mostrarRepuesto': "+ e.getMessage());
        } finally {
            if(session!=null){
                session.close();
            }
        }
        return lista;
    }
    
    @Override
    public int ultimoIdProducto(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("ultimoIdProducto");
        query.setMaxResults(1);
        Producto pro = (Producto) query.list().get(0);
        int numero = pro.getIdProducto()+1;
        return numero;
    }

    @Override
    public int insertarRepuesto(Producto producto) {
        Session session=null;
        List<Repuesto> lista2;
        int flag = 0;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            lista2 = mostrarRepuesto("");
            
            if (producto.getDescripcion().equals("")) {
                flag = 2;
            } else {
                for(Repuesto r : lista2) {
                    if (producto.getDescripcion().equals(r.getProducto().getDescripcion())) {
                        flag = 1;
                    }
                }
            }
            
            session.beginTransaction();
            session.save(producto);
            
            if (flag == 0) {
                session.getTransaction().commit();
            }else {
                session.getTransaction().rollback();
            }
        }catch (HibernateException e) {
            System.out.println("Error en Método 'insertarRepuesto': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    }

    @Override
    public void modificarRepuesto(Producto producto) {
        Session session=null;
        
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();    
            session.beginTransaction();
            session.update(producto);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            System.out.println("Error en Método 'modificarRepuesto': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public boolean eliminarRepuesto(Producto producto) {
        Session session=null;
        boolean flag = false;
      
        try {
            session= NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(producto);
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            flag = true;
            System.out.println("Error en Método 'eliminarRepuesto': "+ e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
        return flag;
    }
}
