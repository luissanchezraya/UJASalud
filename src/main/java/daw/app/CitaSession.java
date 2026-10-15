package daw.app;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import daw.app.model.Cita;
import daw.app.model.dao.CitaDAO;
import daw.app.qualifiers.DAOJpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("CitaSession")
@SessionScoped
public class CitaSession implements Serializable {
    private Cita citaSeleccionada;

    private List<Cita> citas;

    @Inject
    //@DAOMap
    @DAOJpa
    private CitaDAO citaDAO;

    @PostConstruct
    public void init() {
        citas = new ArrayList<>(); // empieza vacía, no todas las citas
    }

    public Cita getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(Cita citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
