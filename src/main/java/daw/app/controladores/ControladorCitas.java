package daw.app.controladores;

import daw.app.AppConfig;
import daw.app.UsuarioSession;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import daw.app.model.Cita;

import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import daw.app.model.Hospital;
import daw.app.model.Usuario;
import daw.app.model.dao.CitaDAO;
import daw.app.model.dao.HospitalDAO;
import daw.app.model.dao.UsuarioDAO;
import daw.app.qualifiers.DAOJpa;

@Named("ControladorCitas")
@ViewScoped
public class ControladorCitas implements Serializable {
    @Inject
    private ControladorHospitales controladorHospitales;

    @Inject
    private Principal principal;

    @Inject
    private UsuarioSession usuarioSession;

    private static final Logger logger = Logger.getLogger(ControladorCitas.class.getName());

    private Cita citaSeleccionada;

    @Inject
    private daw.app.CitaSession CitaSession;

    @Inject
    private AppConfig appConfig;

    @Inject
    //@DAOMap
    @DAOJpa
    private CitaDAO citaDAO;

    @Inject
    @DAOJpa
    private UsuarioDAO usuarioDAO;

    private List<Cita> citasEncontradas;
    private List<Cita> citasFiltradas;


    private String mensaje;

    private String medico;
    private String ubicacion;
    private LocalDate fecha;
    private String tipoDeConsulta;
    private double precio;

    @Inject
    @DAOJpa
    private HospitalDAO hospitalDAO;

    private List<Hospital> listaHospitales;

    private String hospitalSeleccionado;

    @PostConstruct
    public void init() {
        String nombreUsuario = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getUserPrincipal()
                .getName();

        List<Cita> todasLasCitasDelMedico = citaDAO.buscaCitasPorMedico(nombreUsuario);

        String fechaParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("fecha");

        if (fechaParam != null && !fechaParam.isEmpty()) {
            try {
                LocalDate fechaSeleccionada = LocalDate.parse(fechaParam);
                citasFiltradas = todasLasCitasDelMedico.stream()
                        .filter(c -> {
                            return c.getFecha().equals(fechaSeleccionada);
                        })
                        .collect(Collectors.toList());
            } catch (DateTimeParseException e) {
                citasFiltradas = todasLasCitasDelMedico;
            }
        } else {
            citasFiltradas = todasLasCitasDelMedico;
        }

        List<Hospital> todos = hospitalDAO.buscaTodos();
        Map<String, Hospital> hospitalesUnicos = new LinkedHashMap<>();
        for (Hospital h : todos) {
            hospitalesUnicos.putIfAbsent(h.getNombre(), h);
        }
        listaHospitales = new ArrayList<>(hospitalesUnicos.values());
    }


    public String getHospitalSeleccionado() {
        return hospitalSeleccionado;
    }

    public void setHospitalSeleccionado(String hospitalSeleccionado) {
        this.hospitalSeleccionado = hospitalSeleccionado;
    }

    public List<Hospital> getListaHospitales() {
        return listaHospitales;
    }

    public void setListaHospitales(List<Hospital> listaHospitales) {
        this.listaHospitales = listaHospitales;
    }

    public List<Cita> getCitasEncontradas() {
        return citasEncontradas;
    }

    public List<Cita> getCitasFiltradas() {
        return citasFiltradas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFecha() { // Cambiado a LocalDate
        return fecha;
    }

    public void setFecha(LocalDate fecha) { // Cambiado a LocalDate
        this.fecha = fecha;
    }

    public String getTipoDeConsulta() {
        return tipoDeConsulta;
    }

    public void setTipoDeConsulta(String tipoDeConsulta) {
        this.tipoDeConsulta = tipoDeConsulta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String buscar() {
        return "/cita/seleccion";
    }

    public String guardarCambios() {
        Cita cita = CitaSession.getCitaSeleccionada();

        if (cita.getHora() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La hora es obligatoria.", null));
            return null;
        }
        if (cita.getPrecio() < 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El precio no puede ser negativo.", null));
            return null;
        }

        citaDAO.actualizarCita(cita);

        if (usuarioSession.getUsuario().getRol() == Usuario.Rol.MEDICO) {
            return "/medico/citas?faces-redirect=true";
        }
            return "/paciente/miscitas?faces-redirect=true";
    }


    public String buscarCita() {
        if (medico == null || medico.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "El médico no puede estar vacío.", null));
            CitaSession.setCitas(new ArrayList<>());
            return null;
        }

        if (fecha != null && fecha.isBefore(LocalDate.now())) {
            FacesContext.getCurrentInstance().addMessage("form:fecha",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "La fecha no puede ser anterior a hoy.", null));
            return null;
        }

        List<Cita> resultado = citaDAO.buscarCitasFiltradas(medico, fecha, hospitalSeleccionado);
        CitaSession.setCitas(resultado);

        if (resultado.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "No se encontraron citas para el médico: " + medico + ".", null));
            return null;
        }

        return "/cita/seleccion?faces-redirect=true";
    }

    public List<Cita> getCitasDelUsuario() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return citaDAO.buscaCitasPorPaciente(nombreUsuario);
    }

    public List<Cita> getCitasDelMedico() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return citaDAO.buscaCitasPorMedico(nombreUsuario);
    }

    public String editarCita(Cita cita) {
        CitaSession.setCitaSeleccionada(cita);
        logger.info("Usuario " + principal.getName() + " ha editado una cita con ID: " + cita.getID());
        return "/cita/edicion?faces-redirect=true";
    }


    public String seleccionarCita(Cita cita) {
        CitaSession.setCitaSeleccionada(cita);
        logger.info("Usuario " + principal.getName() + " ha seleccionado una cita con ID: " + cita.getID());
        return "/cita/confirmar?faces-redirect=true";
    }

    public String confirmarCita() {
        Cita cita = CitaSession.getCitaSeleccionada();
        Usuario usuario = usuarioSession.getUsuario();

        // Asignar el paciente
        cita.setPaciente(usuario);

        // Guardar los cambios
        citaDAO.actualizarCita(cita);

        return "/pantalla_inicio?faces-redirect=true";
    }



    public String buscarCitaAJAX() {
        if (medico == null || medico.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "El médico no puede estar vacío.", null));
            CitaSession.setCitas(new ArrayList<>());
            return null;
        }

        List<Cita> resultado = citaDAO.buscaCitasPorMedico(medico);
        CitaSession.setCitas(resultado);

        if (resultado.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "No se encontraron citas para el médico: " + medico + ".", null));
        }

        return null;
    }

    public Cita getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(Cita citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
    }

    public void verDetalle(Cita cita) {
        this.citaSeleccionada = cita;
        if (cita.getHospital() != null) {
            controladorHospitales.setHospitalSeleccionado(cita.getHospital());
        }
    }

    public void reset() {
        this.citaSeleccionada = null;
    }

    public void eliminarCita(Cita cita) {
        logger.info("Usuario " + principal.getName() + " ha borrado una cita con ID: " + cita.getID());

        citasFiltradas.remove(cita);
        citaDAO.eliminarCita(cita);
    }

}