package daw.app.model.dao;

import daw.app.model.Hospital;
import jakarta.enterprise.context.ApplicationScoped;
import daw.app.qualifiers.DAOMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@DAOMap
public class HospitalDAOMap implements HospitalDAO {
    private final Map<Integer, Hospital> hospitales;

    public HospitalDAOMap() {
        this.hospitales = new HashMap<>();
    }

    @Override
    public void agregaHospital(Hospital hospital) {
        hospitales.put(hospital.getId(), new Hospital(hospital));
    }

    @Override
    public Hospital buscaPorId(int id) {
        return hospitales.containsKey(id) ? new Hospital(hospitales.get(id)) : null;
    }

    @Override
    public List<Hospital> buscaTodos() {
        return new ArrayList<>(hospitales.values());
    }

    @Override
    public int numHospitales() {
        return hospitales.size();
    }

    @Override
    public void eliminaHospital(int id) {
        hospitales.remove(id);
    }

    @Override
    public void actualizaHospital(Hospital hospital) {
        if (hospitales.containsKey(hospital.getId())) {
            hospitales.put(hospital.getId(), new Hospital(hospital));
        }
    }

    @Override
    public List<Hospital> buscaHospitalesPorSubcadena(String subcadena) {
        List<Hospital> resultado = new ArrayList<>();
        String sub = subcadena.toLowerCase();

        for (Hospital hospital : hospitales.values()) {
            if (hospital.getNombre() != null && hospital.getNombre().toLowerCase().contains(sub)) {
                resultado.add(new Hospital(hospital)); // Clonamos para no exponer el objeto interno
            }
        }

        return resultado;
    }

    @Override
    public Hospital buscarHospitalPorNombre(String nombre) {
        for (Hospital hospital : hospitales.values()) {
            if (hospital.getNombre() != null && hospital.getNombre().equalsIgnoreCase(nombre)) {
                return new Hospital(hospital);
            }
        }
        return null;
    }

    @Override
    public void elimina(String nombre) {
        Hospital hospitalAEliminar = null;
        for (Hospital hospital : hospitales.values()) {
            if (hospital.getNombre().equalsIgnoreCase(nombre)) {
                hospitalAEliminar = hospital;
                break;
            }
        }
        if (hospitalAEliminar != null) {
            hospitales.remove(hospitalAEliminar.getId()); // Elimina el hospital del mapa
        }
    }


}