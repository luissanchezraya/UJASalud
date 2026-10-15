package daw.app.controladores;

import daw.app.UsuarioSession;
import daw.app.model.dao.CitaDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import daw.app.model.Usuario;
import daw.app.model.dao.UsuarioDAO;
import daw.app.qualifiers.DAOJpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import java.security.Principal;
import java.util.logging.Logger;

import static daw.app.model.Usuario.Rol.*;

@Named("ControladorUsuarios")
@ViewScoped
public class ControladorUsuarios implements Serializable {
    @Inject
    private Principal principal;

    private static final Logger logger = Logger.getLogger(ControladorUsuarios.class.getName());

    @Inject
    private HttpServletRequest request;

    @Inject
    private UsuarioSession usuarioSession;

    @Inject
    //@DAOMap
    @DAOJpa
    private UsuarioDAO usuarioDAO;

    @Inject
    //@DAOMap
    @DAOJpa
    private CitaDAO citaDAO;

    private Usuario usuarioNuevo;
    private Usuario usuarioAEditar;
    private List<Usuario> usuarios;

    private String nombre;

    @Size(min = 3, max = 25, message = "El nombre de usuario debe tener entre {min} y {max} caracteres.")
    private String usuario;

    @Email(message = "El formato de correo es incorrecto. Ej: aaa@aaa.com")
    private String email;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

    @Pattern(regexp = "[0-9]{8}[A-Z]", message = "Formato DNI erróneo. Ej: 12345678X")
    private String dni;

    @Pattern(regexp = "[A-Z]{2}[0-9]{10}", message = "Formato tarjeta UJASALUD erróneo. Ej: XX1234567890")
    private String tarjeta;

    @Pattern(regexp = "[0-9]{9}", message = "Número de teléfono erróneo. Ej: 123456789")
    private String telefono;

    private String direccion;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    private String usuarioBuscar;
    private List<Usuario> listaUsuarios;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nombreUsuario = context.getExternalContext().getRequestParameterMap().get("usuarioNombre");

        if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
            this.usuarioAEditar = usuarioDAO.buscaUsuarioPorNombre(nombreUsuario);
        } else {
            // Si no hay parámetro, asumimos que estás en el perfil personal
            Usuario actual = usuarioSession.getUsuario();
            if (actual != null) {
                this.usuarioNuevo = new Usuario();
                usuarioNuevo.setNombre(actual.getNombre());
                usuarioNuevo.setUsuario(actual.getUsuario());
                usuarioNuevo.setEmail(actual.getEmail());
                usuarioNuevo.setDni(actual.getDni());
                usuarioNuevo.setFechaNacimiento(actual.getFechaNacimiento());
                usuarioNuevo.setNumeroDeTarjeta(actual.getNumeroDeTarjeta());
                usuarioNuevo.setTelefono(actual.getTelefono());
                usuarioNuevo.setDireccion(actual.getDireccion());
            }
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarioDAO.buscaTodos();
    }


    public void eliminarUsuario(Usuario usuario) {
        if (usuario != null) {
            if (usuario.getRol() == Usuario.Rol.ADMINISTRADOR) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "No se puede eliminar al administrador.", null));
                return;
            }
            citaDAO.eliminaCitasPorUsuario(usuario);
            usuarioDAO.elimina(usuario.getUsuario());
            listaUsuarios.remove(usuario);
        }
    }

    public Usuario getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuarioNuevo(Usuario usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    public Usuario getUsuarioAEditar() {
        return usuarioAEditar;
    }

    public void setUsuarioAEditar(Usuario usuarioAEditar) {
        this.usuarioAEditar = usuarioAEditar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public String getUsuarioBuscar() {
        return usuarioBuscar;
    }

    public void setUsuarioBuscar(String usuarioBuscar) {
        this.usuarioBuscar = usuarioBuscar;
    }

    public String registroUsuario() {
        if (usuarioNuevo.getRol() != MEDICO && usuarioNuevo.getRol() != ADMINISTRADOR)
            usuarioNuevo.setRol(PACIENTE);

        usuarioDAO.agrega(usuarioNuevo);

        logger.info("Usuario " + usuarioNuevo.getUsuario() + " creado");

        return "/pantalla_inicio?faces-redirect=true";
    }

    public String registroUsuarioAdminstrador() {
        usuarioDAO.agrega(usuarioNuevo);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado correctamente.", null));

        return "/administrador/anadir_usuario?faces-redirect=true";
    }

//    public String login() {
//        return "/pantalla_inicio?faces-redirect=true";
//    }

    public String logout() throws ServletException {
        String nombreUsuario = (principal != null) ? principal.getName() : "desconocido";

        logger.info("Usuario " + nombreUsuario + " ha cerrado sesión");

        request.getSession().invalidate();
        request.logout();

        return "/index?faces-redirect=true";
    }

    public String actualizarInformacion() {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();

        Usuario usuarioActual = usuarioSession.getUsuario();

        usuarioActual.setUsuario(usuarioNuevo.getUsuario());
        usuarioActual.setEmail(usuarioNuevo.getEmail());
        usuarioActual.setDni(usuarioNuevo.getDni());
        usuarioActual.setFechaNacimiento(usuarioNuevo.getFechaNacimiento());
        usuarioActual.setNumeroDeTarjeta(usuarioNuevo.getNumeroDeTarjeta());
        usuarioActual.setTelefono(usuarioNuevo.getTelefono());
        usuarioActual.setDireccion(usuarioNuevo.getDireccion());

        usuarioDAO.actualizarUsuario(usuarioActual.getUsuario(), usuarioActual);

        logger.info("El Usuario " + usuarioActual.getUsuario() + " ha sido editado");

        usuarioNuevo = new Usuario(usuarioActual.getNombre(), usuarioActual.getUsuario(), usuarioActual.getEmail(),
                usuarioActual.getContrasena(), usuarioActual.getDni(), usuarioActual.getNumeroDeTarjeta(),
                usuarioActual.getTelefono(), usuarioActual.getDireccion(), usuarioActual.getFechaNacimiento(), usuarioActual.getRol());

        return "/editar?faces-redirect=true";
    }

    public String actualizarUsuarioEditado() {
        usuarioDAO.actualizarUsuario(usuarioAEditar.getUsuario(), usuarioAEditar);
        return "/administrador/administracion_usuario?faces-redirect=true";
    }

    public void buscarUsuariosAJAX() {
        if (usuarioBuscar == null || usuarioBuscar.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "El usuario no puede estar vacío.", null));
            listaUsuarios = List.of();
            return;
        }

        listaUsuarios = usuarioDAO.buscaUsuariosPorSubcadena(usuarioBuscar);

        if (listaUsuarios.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "No se encontraron usuarios con el nombre: " + usuarioBuscar + ".", null));
        }
    }

    public void prepararNuevoUsuario() {
        this.usuarioNuevo = new Usuario();
    }

}