package Presentacion;

import Model.Producto;
import Model.Repuesto;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ConsultaNombradaRepuesto {
    public static void main(String[] args) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Query query = session.getNamedQuery("busquedaPorDescripcionRepuesto").setParameter("descrip", "%%");
        
        List<Repuesto> lista = query.list();
        
        for(Repuesto pro : lista){
            System.out.println(pro.getIdProducto() + " - " + pro.getProducto().getDescripcion());
            System.out.println(pro.getImagen());
        }
        
        System.out.println(lista.size());
        
//        Query query = session.getNamedQuery("ultimoIdProducto");
//        query.setMaxResults(1);
//        Producto pro = (Producto) query.list().get(0);
//        
//        int numero = pro.getIdProducto()+1;
//        System.out.println("ULTIMO ID: " + numero);
        //-----------------------------------------------
//        Query query = session.getNamedQuery("ultimoIdProducto");
//        query.setMaxResults(1);
//        Producto pro = (Producto) query.list().get(0);
//        
//        int numero = pro.getIdProducto()+1;
//        System.out.println("ULTIMO ID: " + numero);


    }
}
