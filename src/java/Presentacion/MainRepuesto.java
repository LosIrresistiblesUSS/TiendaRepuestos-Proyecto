package Presentacion;

import Bean.repuestoBean;
import Model.Producto;
import Model.Repuesto;
import Persistencia.NewHibernateUtil;
import org.hibernate.Session;

public class MainRepuesto {
    public static void main(String[] args) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
//        Repuesto r = (Repuesto)session.get(Repuesto.class, 1);
//        
//        
//        System.out.println(r.getProducto().getDescripcion());
//        System.out.println(r.getProducto().getStock());
//        System.out.println(r.getImagen());

            Producto producto = new Producto();
            Repuesto repuesto = new Repuesto();
            
            repuesto.setIdProducto(254);
//            repuesto.setImagen("imagen4.jpg");
            
//            producto.setIdProducto(254);
//            producto.setDescripcion("Un producto de Prueba sin ID 2");
//            producto.setPrecio(20.5);
//            producto.setPrecioPorMayor(20.1);
//            producto.setStock(100);
            
            producto.setRepuesto(repuesto);
            
            session.beginTransaction();
            session.delete(repuesto);
            session.getTransaction().commit();
            
//            repuestoBean rb = new repuestoBean();
//            rb.setProducto(producto);
//            rb.insertar();

            session.close();
    }
}