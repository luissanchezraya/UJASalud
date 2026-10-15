package daw.app;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import daw.app.model.Usuario;
import daw.app.model.dao.UsuarioDAO;
import daw.app.qualifiers.DAOJpa;

import java.io.Serializable;

@Named("usuarioSession")
@SessionScoped
public class UsuarioSession implements Serializable {

    private Usuario usuario;

    @Inject
    //@DAOMap
    @DAOJpa
    private UsuarioDAO usuarioDAO;

    @PostConstruct
    public void init() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (nombreUsuario != null) {
            this.usuario = usuarioDAO.buscaUsuarioPorNombre(nombreUsuario);
        }
    }

    public UsuarioSession() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void clearSession() {
        this.usuario = null;
    }
}