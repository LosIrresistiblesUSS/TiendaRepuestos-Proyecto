package DAO;

import Model.Login;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class LoginDaoImplement implements LoginDao{

    @Override
    public Login iniciarSesion(Login login) {
        Session session = null;
        Login loginIniciado = null;
        
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("inicioSesion").setParameter("user", login.getUsuario()).setParameter("pass", login.getPass());
            List<Login> loginList = query.list();
            
            if (!loginList.isEmpty()) {
                loginIniciado = loginList.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error en Método 'iniciarSesion': "+ e.getMessage());
        } finally {
            if(session!=null){
                session.close();
            }
        }
        return loginIniciado;
    }
}