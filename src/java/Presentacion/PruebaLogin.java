package Presentacion;

import Model.Login;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;

public class PruebaLogin {
    public static void main(String[] args) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("inicioSesion").setParameter("user", "acotrinae").setParameter("pass", DigestUtils.md5Hex("48221945"));
        Login login = null;
        
        List<Login> loginList = query.list();
        
        if (!loginList.isEmpty()) {
            login = loginList.get(0);
            System.out.println("USUARIO: "+ login.getUsuario());
        } else {
            System.out.println("No se encontró un usuario con esas credenciales");
        }
    }
}
