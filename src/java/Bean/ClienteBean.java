package Bean;

import DAO.ClienteDao;
import DAO.ClienteDaoImplement;
import Model.Cliente;
import Model.Empleado;
import Model.Persona;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ClienteBean {
    Persona persona;
    Cliente cliente;
    String busqueda = "";
    List<Cliente> personaClientes;

    public ClienteBean() {
        cliente = new Cliente();
        persona = new Persona();
        persona.setCliente(cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void listar(){
        this.personaClientes = getPersonaClientes();
        
        if (!busqueda.equals("")) {
            if (!personaClientes.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Resultados de busqueda que contienen el texto: " + busqueda));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existen Clientes que contengan el siguiente texto: " + busqueda));
            }   
        }
    }
    
    
    public List<Cliente> getPersonaClientes() {
        ClienteDao linkDAO = new ClienteDaoImplement();
        personaClientes=linkDAO.mostrarCliente(busqueda);
        return personaClientes;
    }

    public void setPersonaClientes(List<Cliente> personaClientes) {
        this.personaClientes = personaClientes;
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}
