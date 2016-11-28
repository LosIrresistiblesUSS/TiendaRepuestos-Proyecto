package Presentacion;

import Model.Cliente;
import Model.Empleado;
import Model.Persona;
import Model.TipoCliente;
import Model.TipoDocumento;
import Model.TipoEmpleado;
import Persistencia.NewHibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
//        Empleado c = (Empleado)session.get(Empleado.class, 6);
        
            Empleado e = new Empleado();
            TipoEmpleado te = new TipoEmpleado();
            
            Cliente c = new Cliente();
            TipoCliente tc = new TipoCliente();
            
            Persona p = new Persona();
            TipoDocumento td = new TipoDocumento();
            
            /*LLENANDO TIPO EMPLEADO*/
            te.setIdTipoEmpleado(2);
            
            /*LLENANDO TIPO CLIENTE*/
            tc.setIdTipoCliente(2);
            
            /*LLENANDO EMPLEADO*/
            e.setIdPersona(38);
            e.setApellidos("Apellido Prueba");
            e.setTipoEmpleado(te);
            
            /*LLENANDO CLIENTE*/
            c.setIdPersona(39);
            c.setApellidos("Apellido del Cliente");
            c.setRazonSocial("Razon Social del Cliente");
            c.setTipoCliente(tc);
            
            /*LLENANDO TIPO DOCUMENTO*/
            td.setIdTipoDocumento(2);
            
            /*LLENANDO PERSONA*/
            p.setIdPersona(38);
            p.setNumeroDocumento("222222");
            p.setNombres("Nombre del Empleado");
            p.setDireccion("Direccion del Empleado");
            p.setTelefono("222222");
            p.setEmail("Email del Empleado");
            p.setTipoDocumento(td);
            p.setCliente(c);
               
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
        
//        if (c == null) {
//            System.out.println("El id No se encotntró...");
//        } else {
//            
//            System.out.println("SERVICIOS:");
//            for(Servicio s : c.getServicioList()){
//                System.out.println(s.getProducto().getDescripcion());
//            }

//            }
            session.close();
        
    }
}