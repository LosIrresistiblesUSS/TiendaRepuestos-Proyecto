package Bean;

import DAO.LoginDao;
import DAO.LoginDaoImplement;
import Model.Login;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean
@ViewScoped
public class loginBean {
    Login login;

    public loginBean() {
        login = new Login();
    }
    
    public String iniciarSesion(){
    LoginDao linkDAO = new LoginDaoImplement();
    Login l;
    String redireccion = null;
    
        try {
            String passEncriptada = DigestUtils.md5Hex(this.login.getPass());
            this.login.setPass(passEncriptada);
            
            l = linkDAO.iniciarSesion(login);
            
            if (l!=null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", l);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido:", l.getPersona().getNombres() +"."));
                redireccion = "Principal?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Datos de Usuario Incorrectos."));
            
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Ocurrio un error al intentar Iniciar Sesion. Error: " + e.getMessage()));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        return redireccion;
    }
    
    public void verificarSesionCerrada(){
        try {
            Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
            
            if (user == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./");
            }
        } catch (Exception e) {
            
        }
    }
    
    public void verificarSesionAbierta(){
        try {
            Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
            
            if (user != null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./Principal.xhtml");
            }
        } catch (Exception e) {
            
        }
    }
    
    public Login mostrarUsuarioLogeado(){
        Login user = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        return user;
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
