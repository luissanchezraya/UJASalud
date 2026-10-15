package daw.app.model.dao;

import daw.app.model.Cita;
import daw.app.model.Hospital;
import daw.app.model.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaDAO {
    void agregaCita(Cita cita);
    List<Cita> listadoCitas();
    List<Cita> buscaCitasPorMedico(Usuario medico);
    Cita buscaCitaPorHora(LocalTime hora);
    List<Cita> buscaCitasPorPaciente(String paciente);
    void actualizarCita(Cita nuevaCita);
    void eliminaCitasPorUsuario(Usuario usuario);
    boolean actualizarEstadoCita(LocalTime hora, Cita.EstadoCita nuevoEstado);
    void eliminaCitasPorHospital(Hospital hospital);
    void eliminarCita(Cita cita);
    int numCitas();
    List<Cita> buscaCitasPorMedicoYFecha(String subcadena, String fecha);
    List<Cita> buscaCitasPorMedico(String subcadena);
    List<Cita> buscarCitasFiltradas(String medico, LocalDate fecha, String hospital);
}