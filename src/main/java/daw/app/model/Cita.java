package daw.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable; import java.time.LocalDate;
import java.time.LocalTime;

@Entity public class Cita implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn
    private Usuario paciente;

    @NotNull
    @Column(nullable = false)
    private LocalTime hora;

    private String mensajeDelMedico;

    @Min(value = 0)
    private double precio;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado;

    @FutureOrPresent
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario medico;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Hospital hospital;

    private String ubicacion;
    private String tipoDeConsulta;

    public Cita() {
        this.mensajeDelMedico = "";
        this.estado = EstadoCita.PENDIENTE;
        this.fecha = LocalDate.now();
    }

    public Cita(LocalDate fecha, Usuario paciente, Usuario medico, Hospital hospital, LocalTime hora, String ubicacion, String mensajeDelMedico, EstadoCita estado, String tipoDeConsulta, double precio) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }

        this.fecha = fecha;
        this.paciente = paciente;
        this.medico = medico != null ? medico : new Usuario();
        this.hospital = hospital != null ? hospital : new Hospital();
        this.hora = hora;
        this.ubicacion = ubicacion != null ? ubicacion : "No especificada";
        this.mensajeDelMedico = mensajeDelMedico;
        this.estado = estado;
        this.tipoDeConsulta = tipoDeConsulta != null ? tipoDeConsulta : "General";
        this.precio = precio;
    }


    public Long getID() {
        return ID;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMensajeDelMedico() {
        return mensajeDelMedico;
    }

    public void setMensajeDelMedico(String mensajeDelMedico) {
        this.mensajeDelMedico = mensajeDelMedico;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipoDeConsulta() {
        return tipoDeConsulta;
    }

    public void setTipoDeConsulta(String tipoDeConsulta) {
        this.tipoDeConsulta = tipoDeConsulta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Cita [id=" + ID + ", pacienteId=" + paciente.getId() + ", hora=" + hora + ", estado=" + estado + ", precio=" + precio + ", medico=" + medico.getNombre() + ", hospital=" + hospital.getNombre() + "]";
    }

    public enum EstadoCita {
        PENDIENTE("Pendiente"),
        CONFIRMADA("Confirmada");

        private final String nombre;

        EstadoCita(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
}