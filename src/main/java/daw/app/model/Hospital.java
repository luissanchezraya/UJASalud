package daw.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
public class Hospital implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El nombre del hospital es obligatorio.")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre {min} y {max} caracteres.")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "La dirección es obligatoria.")
    @Size(min = 5, max = 200, message = "La dirección debe tener entre {min} y {max} caracteres.")
    @Column(nullable = false)
    private String direccion;

    public Hospital() {
    }

    public Hospital(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Hospital(Hospital hospital) {
        this.id = hospital.id;
        this.nombre = hospital.nombre;
        this.direccion = hospital.direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Hospital{id='" + id + "', nombre='" + nombre + "', direccion='" + direccion + "'}";
    }
}