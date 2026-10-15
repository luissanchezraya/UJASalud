package daw.app.model.dao;

import daw.app.model.Hospital;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import daw.app.model.Cita;
import daw.app.model.Usuario;
import daw.app.qualifiers.DAOJpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Dependent
@DAOJpa
@Transactional
public class CitaDAOJPA implements CitaDAO, Serializable {

    @PersistenceContext(unitName = "UJASaludPU")
    private EntityManager em;

    @Override
    public void agregaCita(Cita cita) {
        em.persist(cita);
    }

    @Override
    public List<Cita> listadoCitas() {
        return em.createQuery("SELECT c FROM Cita c", Cita.class).getResultList();
    }

    @Override
    public Cita buscaCitaPorHora(LocalTime hora) {
        return em.createQuery("SELECT c FROM Cita c WHERE c.hora = :hora", Cita.class)
                .setParameter("hora", hora)
                .getSingleResult();
    }

    @Override
    public List<Cita> buscaCitasPorPaciente(String usuario) {
        return em.createQuery("SELECT c FROM Cita c WHERE c.paciente.usuario = :usuario", Cita.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }

    @Override
    public List<Cita> buscaCitasPorMedico(Usuario medico) {
        return em.createQuery("SELECT c FROM Cita c WHERE c.medico = :medico", Cita.class)
                .setParameter("medico", medico)
                .getResultList();
    }


    @Override
    public List<Cita> buscarCitasFiltradas(String medico, LocalDate fecha, String nombreHospital) {
        String jpql = "SELECT c FROM Cita c WHERE c.paciente IS NULL";

        if (medico != null && !medico.isBlank()) {
            jpql += " AND c.medico.usuario LIKE :medico";
        }
        if (fecha != null) {
            jpql += " AND c.fecha = :fecha";
        }
        if (nombreHospital != null && !nombreHospital.isBlank()) {
            jpql += " AND c.hospital.nombre = :nombreHospital";
        }

        TypedQuery<Cita> query = em.createQuery(jpql, Cita.class);

        if (medico != null && !medico.isBlank()) {
            query.setParameter("medico", "%" + medico + "%");
        }
        if (fecha != null) {
            query.setParameter("fecha", fecha);
        }
        if (nombreHospital != null && !nombreHospital.isBlank()) {
            query.setParameter("nombreHospital", nombreHospital);
        }

        return query.getResultList();
    }

    @Override
    public void actualizarCita(Cita cita) {
        em.merge(cita);
    }

    @Override
    public void eliminaCitasPorUsuario(Usuario usuario) {
        List<Cita> citaspaciente = em.createQuery("SELECT c FROM Cita c WHERE c.paciente = :usuario", Cita.class)
                .setParameter("usuario", usuario)
                .getResultList();
        for (Cita cita : citaspaciente) {
            em.remove(cita);
        }

        List<Cita> citasmedico = em.createQuery("SELECT c FROM Cita c WHERE c.medico = :usuario", Cita.class)
                .setParameter("usuario", usuario)
                .getResultList();
        for (Cita cita : citasmedico ) {
            em.remove(cita);
        }
    }

    @Override
    public boolean actualizarEstadoCita(LocalTime hora, Cita.EstadoCita nuevoEstado) {
        Cita cita = buscaCitaPorHora(hora);
        if (cita != null) {
            cita.setEstado(nuevoEstado);
            em.merge(cita);
            return true;
        }
        return false;
    }

    @Override
    public void eliminaCitasPorHospital(Hospital hospital) {
        List<Cita> citas = em.createQuery("SELECT c FROM Cita c WHERE c.hospital = :hospital", Cita.class)
                .setParameter("hospital", hospital)
                .getResultList();
        for (Cita cita : citas) {
            em.remove(cita);
        }
    }

    @Override
    public void eliminarCita(Cita cita) {
        Cita citaEncontrada = em.find(Cita.class, cita.getID());
        if (citaEncontrada != null) {
            em.remove(citaEncontrada);
        }
        listadoCitas();
    }

    @Override
    public int numCitas() {
        return ((Number) em.createQuery("SELECT COUNT(c) FROM Cita c").getSingleResult()).intValue();
    }

    @Override
    public List<Cita> buscaCitasPorMedicoYFecha(String subcadena, String fecha) {
        return em.createQuery("SELECT c FROM Cita c WHERE LOWER(c.medico.usuario) LIKE LOWER(:subcadena) AND c.fecha = :fecha", Cita.class)
                .setParameter("subcadena", "%" + subcadena + "%")
                .setParameter("fecha", LocalDate.parse(fecha))
                .getResultList();
    }

    @Override
    public List<Cita> buscaCitasPorMedico(String subcadena) {
        return em.createQuery("SELECT c FROM Cita c WHERE LOWER(c.medico.usuario) LIKE LOWER(:subcadena)", Cita.class)
                .setParameter("subcadena", "%" + subcadena + "%")
                .getResultList();
    }
}
