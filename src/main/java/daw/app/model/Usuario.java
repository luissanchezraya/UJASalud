package daw.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre es obligatorio.")
    @Column(nullable = false)
    private String nombre;

    @Size(min = 3, max = 25, message = "El nombre de usuario debe tener entre {min} y {max} caracteres.")
    private String usuario;

    @Email(message = "El formato de correo es incorrecto. Ej: aaa@aaa.com")
    private String email;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String contrasena;

    @Pattern(regexp = "[0-9]{8}[A-Z]", message = "Formato DNI erróneo. Ej: 12345678X")
    private String dni;

    @Pattern(regexp = "[A-Z]{2}[0-9]{10}", message = "Formato tarjeta UJASALUD erróneo. Ej: XX1234567890")
    private String numeroDeTarjeta;

    @Pattern(regexp = "[0-9]{9}", message = "Número de teléfono erróneo. Ej: 123456789")
    private String telefono;

    private String direccion;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada.")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        PACIENTE,
        MEDICO,
        ADMINISTRADOR
    }

    public Usuario() {}

    public Usuario(String nombre, String usuario, String email, String contrasena,
                   String dni, String numeroDeTarjeta,
                   String telefono, String direccion, LocalDate fechaNacimiento, Rol rol) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.contrasena = contrasena;
        this.dni = dni;
        this.numeroDeTarjeta = numeroDeTarjeta;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }

    public void setNumeroDeTarjeta(String numeroDeTarjeta) {
        this.numeroDeTarjeta = numeroDeTarjeta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", usuario=" + usuario + ", email=" + email + ", dni=" + dni + ", rol=" + rol + "]";
    }
}