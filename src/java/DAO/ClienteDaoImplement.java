package DAO;

import Model.Cliente;
import Model.Repuesto;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ClienteDaoImplement implements ClienteDao {

    @Override
    public List<Cliente> mostrarCliente(String busqueda) {
        Session session=null;
        List<Cliente> lista=null;
        
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("busquedaPorApellidoRazonSocialCliente").setParameter("descrip", "%"+busqueda+"%");
            lista=(List<Cliente>) query.list();
            
        } catch (Exception e) {
            System.out.println("Error en Método 'mostrarCliente': "+ e.getMessage());
        } finally {
            if(session!=null){
                session.close();
            }
        }
        return lista;
    }
    
}
