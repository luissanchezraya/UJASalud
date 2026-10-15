package daw.app.model.dao;

import daw.app.model.Hospital;
import jakarta.enterprise.context.ApplicationScoped;
import daw.app.model.Cita;
import daw.app.model.Usuario;
import daw.app.qualifiers.DAOMap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class CitaDAOMap implements CitaDAO {
    private List<Cita> citasDAO;

    public CitaDAOMap() {
        citasDAO = new ArrayList<>();
    }

    @Override
    public void agregaCita(Cita cita) {
        if (cita != null) {
            citasDAO.add(cita);
        }
    }

    @Override
    public List<Cita> buscarCitasFiltradas(String medico, LocalDate fecha, String hospital) {
        return citasDAO.stream()
                .filter(c -> c.getPaciente() == null)
                .filter(c -> (medico == null || medico.isEmpty() || c.getMedico().getUsuario().equalsIgnoreCase(medico)))
                .filter(c -> (fecha == null || c.getFecha().equals(fecha)))
                .filter(c -> (hospital == null || hospital.isEmpty() || c.getHospital().getNombre().equalsIgnoreCase(hospital)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> listadoCitas() {
        return citasDAO.stream()
                .filter(cita -> cita.getEstado() == Cita.EstadoCita.PENDIENTE || cita.getEstado() == Cita.EstadoCita.CONFIRMADA)
                .collect(Collectors.toList());
    }

    @Override
    public Cita buscaCitaPorHora(LocalTime hora) {
        return citasDAO.stream()
                .filter(cita -> cita.getHora().equals(hora))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void eliminaCitasPorUsuario(Usuario usuario) {
        citasDAO.removeIf(cita -> cita.getPaciente() != null && cita.getPaciente().getUsuario().equals(usuario.getUsuario()));
        citasDAO.removeIf(cita -> cita.getMedico() != null && cita.getMedico().getUsuario().equals(usuario.getUsuario()));
    }

    @Override
    public void eliminaCitasPorHospital(Hospital hospital) {
        citasDAO.removeIf(cita -> cita.getHospital().equals(hospital));
    }

    @Override
    public List<Cita> buscaCitasPorPaciente(String usuario) {
        return citasDAO.stream()
                .filter(cita -> cita.getPaciente() != null &&
                        cita.getPaciente().getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> buscaCitasPorMedico(Usuario medico) {
        return citasDAO.stream()
                .filter(c -> c.getMedico() != null && c.getMedico().equals(medico))
                .collect(Collectors.toList());
    }


    @Override
    public void actualizarCita(Cita nuevaCita) {
        if (nuevaCita != null && nuevaCita.getID() != null) {
            for (int i = 0; i < citasDAO.size(); i++) {
                Cita cita = citasDAO.get(i);
                if (cita.getID().equals(nuevaCita.getID())) {
                    citasDAO.set(i, nuevaCita);
                    return;
                }
            }
        }
    }



    @Override
    public boolean actualizarEstadoCita(LocalTime hora, Cita.EstadoCita nuevoEstado) {
        Cita cita = buscaCitaPorHora(hora);
        if (cita != null) {
            cita.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }

    @Override
    public void eliminarCita(Cita cita) {
        citasDAO.remove(cita);
    }

    @Override
    public int numCitas() {
        return citasDAO.size();
    }

    @Override
    public List<Cita> buscaCitasPorMedico(String subcadena) {
        return citasDAO.stream()
                .filter(cita -> {
                    Usuario medico = cita.getMedico();
                    String nombre = (medico != null && medico.getNombre() != null) ? medico.getNombre().toLowerCase() : "";
                    return nombre.contains(subcadena.toLowerCase());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> buscaCitasPorMedicoYFecha(String subcadena, String fecha) {
        return citasDAO.stream()
                .filter(cita -> {
                    Usuario medico = cita.getMedico();
                    String nombre = (medico != null && medico.getNombre() != null) ? medico.getNombre().toLowerCase() : "";
                    return nombre.contains(subcadena.toLowerCase()) && cita.getFecha().equals(LocalDate.parse(fecha));
                })
                .collect(Collectors.toList());
    }

}
