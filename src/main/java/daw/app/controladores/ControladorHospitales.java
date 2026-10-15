package daw.app.controladores;

import daw.app.model.dao.CitaDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import daw.app.model.Hospital;
import daw.app.model.dao.HospitalDAO;
import daw.app.qualifiers.DAOJpa;

import java.io.Serializable;
import java.util.List;

@Named("ControladorHospitales")
@ViewScoped
public class ControladorHospitales implements Serializable {
    private List<Hospital> hospitales;
    private Hospital hospitalSeleccionado;


    private Integer nuevoId;
    private String nuevoNombre;
    private String nuevaDireccion;

    private String hospitalBuscar;
    private List<Hospital> listaHospitales;

    private Hospital hospitalAEditar;
    private Hospital hospitalNuevo;

    @Inject
    @DAOJpa
    private HospitalDAO hospitalDAO;

    @Inject
    @DAOJpa
    private CitaDAO citaDAO;

    @PostConstruct
    public void init() {
        hospitales = hospitalDAO.buscaTodos();

        FacesContext context = FacesContext.getCurrentInstance();
        String nombreHospital = context.getExternalContext().getRequestParameterMap().get("hospitalNombre");

        if (nombreHospital != null && !nombreHospital.isEmpty()) {
            // Buscar el hospital por nombre y asignarlo a hospitalAEditar
            this.hospitalAEditar = hospitalDAO.buscarHospitalPorNombre(nombreHospital);
        } else {
            // Si no hay parámetro, se puede tomar algún valor predeterminado o manejarlo de otra manera
            hospitalAEditar = new Hospital();  // Si no hay un hospital a editar, podrías inicializar uno vacío
        }
    }

    public List<Hospital> getHospitales() {
        return hospitales;
    }

    public String actualizarHospital() {
        hospitalDAO.actualizaHospital(hospitalAEditar);
        return "/administrador/administracion_hospital?faces-redirect=true";
    }


    public void eliminarHospital(Hospital hospital) {
        if (hospital != null) {
            citaDAO.eliminaCitasPorHospital(hospital);
            hospitalDAO.eliminaHospital(hospital.getId());
            buscarHospitalesAJAX();
        }
    }

    public void agregarHospital() {
        Hospital nuevoHospital = new Hospital(nuevoNombre, nuevaDireccion);
        hospitalDAO.agregaHospital(nuevoHospital);
        hospitales = hospitalDAO.buscaTodos();
        nuevoNombre = null;
        nuevaDireccion = null;
    }

    public Hospital getHospitalSeleccionado() {
        return hospitalSeleccionado;
    }

    public void setHospitalSeleccionado(Hospital hospitalSeleccionado) {
        this.hospitalSeleccionado = hospitalSeleccionado;
    }

    public void seleccionarHospital(Hospital hospital) {
        this.hospitalSeleccionado = hospital;
    }

    public Integer getNuevoId() {
        return nuevoId;
    }

    public void setNuevoId (Integer nuevoId) {
        this.nuevoId = nuevoId;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public String getNuevaDireccion() {
        return nuevaDireccion;
    }

    public void setNuevaDireccion(String nuevaDireccion) {
        this.nuevaDireccion = nuevaDireccion;
    }

    public String getHospitalBuscar() {
        return hospitalBuscar;
    }

    public void setHospitalBuscar(String hospitalBuscar) {
        this.hospitalBuscar = hospitalBuscar;
    }

    public List<Hospital> getListaHospitales() {
        return listaHospitales;
    }

    public void setListaHospitales(List<Hospital> listaHospitales) {
        this.listaHospitales = listaHospitales;
    }

    public Hospital getHospitalNuevo() {
        return hospitalNuevo;
    }

    public void setHospitalNuevo(Hospital hospitalNuevo) {
        this.hospitalNuevo = hospitalNuevo;
    }

    public Hospital getHospitalAEditar() {
        return hospitalAEditar;
    }

    public void setHospitalAEditar(Hospital hospitalAEditar) {
        this.hospitalAEditar = hospitalAEditar;
    }

    public void buscarHospitalesAJAX() {
        if (hospitalBuscar == null || hospitalBuscar.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "El nombre del hospital no puede estar vacío.", null));
            listaHospitales = List.of();
            return;
        }

        listaHospitales = hospitalDAO.buscaHospitalesPorSubcadena(hospitalBuscar);

        if (listaHospitales.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "No se encontraron hospitales con el nombre: " + hospitalBuscar + ".", null));
        }
    }

    public String registroHospital() {
        Hospital nuevoHospital = new Hospital(nuevoNombre, nuevaDireccion);
        hospitalDAO.agregaHospital(nuevoHospital);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Hospital creado correctamente.", null));

        return "/administrador/anadir_hospital?faces-redirect=true";
    }
}