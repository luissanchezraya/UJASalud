package daw.app.model.dao;

import daw.app.model.Hospital;
import java.util.List;

public interface HospitalDAO {
    void agregaHospital(Hospital hospital);
    Hospital buscaPorId(int id);
    List<Hospital> buscaTodos();
    int numHospitales();
    void eliminaHospital(int id);
    void actualizaHospital(Hospital hospital);
    List<Hospital> buscaHospitalesPorSubcadena(String subcadena);
    void elimina(String nombre);
    Hospital buscarHospitalPorNombre(String nombre);
}
