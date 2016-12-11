package Presentacion;

import Model.Cliente;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PruebaCliente {
    public static void main(String[] args) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Query query = session.getNamedQuery("busquedaPorApellidoRazonSocialCliente").setParameter("descrip", "%%");
        
        List<Cliente> lista = query.list();
        
        for(Cliente c : lista){
            System.out.println(c.getIdPersona() + " " + c.getApellidos() + c.getRazonSocial());
        }
        
        session.close();
    }
}
