package Presentacion;

import Model.Login;
import Persistencia.NewHibernateUtil;
import org.hibernate.Session;

public class MainUsuario {
    public static void main(String[] args) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Login user = (Login) session.get(Login.class, 1);
        
        
        System.out.println("USUARIO: "+user.getUsuario());
        System.out.println("PASS: "+user.getPass());
        System.out.println("NOMBRES: " + user.getPersona().getNombres());
        System.out.println("EMAIL: "+ user.getPersona().getEmail());
        System.out.println("TIPO DE EMPLEADO: " + user.getPersona().getEmpleado().getTipoEmpleado().getDescripcion());
        
        session.close();
    }
    
}
