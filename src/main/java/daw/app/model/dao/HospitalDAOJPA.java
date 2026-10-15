package daw.app.model.dao;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import daw.app.model.Hospital;
import daw.app.qualifiers.DAOJpa;

import java.io.Serializable;
import java.util.List;

@Dependent
@DAOJpa
@Transactional
public class HospitalDAOJPA implements HospitalDAO, Serializable {

    @PersistenceContext(unitName = "UJASaludPU")
    private EntityManager em;

    @Override
    public void agregaHospital(Hospital hospital) {
        em.persist(hospital);
    }

    @Override
    public Hospital buscaPorId(int id) {
        return em.find(Hospital.class, id);
    }

    @Override
    public List<Hospital> buscaTodos() {
        return em.createQuery("SELECT h FROM Hospital h", Hospital.class).getResultList();
    }

    @Override
    public int numHospitales() {
        return (int) em.createQuery("SELECT COUNT(h) FROM Hospital h").getSingleResult();
    }

    @Override
    public void eliminaHospital(int id) {
        Hospital hospital = buscaPorId(id);
        if (hospital != null) {
            em.remove(hospital);
        }
    }

    @Override
    public void actualizaHospital(Hospital hospital) {
        em.merge(hospital);
    }

    public List<Hospital> buscaHospitalesPorSubcadena(String subcadena) {
        String query = "SELECT h FROM Hospital h WHERE LOWER(h.nombre) LIKE :subcadena";
        return em.createQuery(query, Hospital.class)
                .setParameter("subcadena", "%" + subcadena.toLowerCase() + "%")
                .getResultList();
    }

    @Override
    public void elimina(String nombre) {
        Hospital hospital = em.find(Hospital.class, nombre);
        if (hospital != null) {
            em.remove(hospital);
        }
    }

    @Override
    public Hospital buscarHospitalPorNombre(String nombre) {
        try {
            String query = "SELECT h FROM Hospital h WHERE LOWER(h.nombre) = :nombre";
            return em.createQuery(query, Hospital.class)
                    .setParameter("nombre", nombre.toLowerCase()) // Usamos .toLowerCase() para que sea insensible a mayúsculas/minúsculas
                    .getSingleResult(); // Usamos getSingleResult() porque esperamos un único resultado
        } catch (Exception e) {
            return null; // Si no se encuentra el hospital, devolvemos null
        }
    }
}
