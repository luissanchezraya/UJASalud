package daw.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.enterprise.inject.Default;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import daw.app.model.Cita;
import daw.app.model.Hospital;
import daw.app.model.Usuario;
import daw.app.model.dao.CitaDAO;
import daw.app.model.dao.HospitalDAO;
import daw.app.model.dao.UsuarioDAO;
import daw.app.qualifiers.DAOJpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import static java.util.logging.Logger.*;

//@EmbeddedIdentityStoreDefinition({
//        @Credentials(callerName = "admin", password = "admin", groups = {"ADMINISTRADORES"}),
//        @Credentials(callerName = "usuario", password = "usuario", groups = {"USUARIOS"})
//})
//@BasicAuthenticationMechanismDefinition( realmName = "UsuariosAutenticados" )

@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login.xhtml?error",
                useForwardToLogin = false
        )
)

@FacesConfig
@Named("app")
@Default
@ApplicationScoped
public class AppConfig {

    private final Logger log = getLogger(AppConfig.class.getName());

    @Inject
    //@DAOMap
    @DAOJpa
    private HospitalDAO hospitalDAO;

    @Inject
    //@DAOMap
    @DAOJpa
    private CitaDAO citaDAO;

    @Inject
    //@DAOMap
    @DAOJpa
    private UsuarioDAO usuarioDAO;

    public AppConfig() {
        log.info(">>> Application starting...");
    }

    public void onStartup(@Observes Startup event) {
        log.info(">>> Application ready");
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        usuarioDAO.agrega(new Usuario("administrador","administrador", "admin@email.com", "administrador", "12345678X", "XX1234567891", "123456789", "Calle Falsa 001", LocalDate.of(1991, 2, 22), Usuario.Rol.ADMINISTRADOR));
        usuarioDAO.agrega(new Usuario("usuario","usuario", "usuario@email.com", "usuario", "22345678X", "XX1234567892", "323456789", "Calle Falsa 002", LocalDate.of(1992, 3, 23), Usuario.Rol.PACIENTE));

        Usuario medico1 = new Usuario("Dr. Pepe Botella","pepebotella", "pepebotella@email.com", "pepebotella", "32345678X", "XX1234567893", "423456789", "Calle Falsa 003", LocalDate.of(1993, 4, 24), Usuario.Rol.MEDICO);
        Usuario medico2 = new Usuario("Dr. Pepe Litronas","pepelitronas", "pepelitronas@email.com", "pepelitronas", "42345678X", "XX1234567894", "523456789", "Calle Falsa 004", LocalDate.of(1994, 5, 25), Usuario.Rol.MEDICO);
        Usuario medico3 = new Usuario("Dr. Pepe Bombilla","pepebombilla", "pepebombilla@email.com", "pepebombilla", "52345678X", "XX1234567895", "623456789", "Calle Falsa 005", LocalDate.of(1995, 6, 26), Usuario.Rol.MEDICO);
        Usuario medico4 = new Usuario("Dr. Luis Trompeta","luistrompeta", "luistrompeta@email.com", "luistrompeta", "62345678X", "XX1234567896", "723456789", "Calle Falsa 006", LocalDate.of(1996, 7, 27), Usuario.Rol.MEDICO);
        Usuario medico5 = new Usuario("Dra. Carmen Ordoñez","carmenordonez", "carmenordonez@email.com", "carmenordonez", "72345678X", "XX1234567897", "823456789", "Calle Falsa 007", LocalDate.of(1997, 8, 28), Usuario.Rol.MEDICO);
        Usuario medico6 = new Usuario("Dra. Carmen Jurado","carmenjurado", "carmenjurado@email.com", "carmenjurado", "82345678X", "XX1234567898", "923456789", "Calle Falsa 008", LocalDate.of(1998, 9, 29), Usuario.Rol.MEDICO);

        usuarioDAO.agrega(medico1);
        usuarioDAO.agrega(medico2);
        usuarioDAO.agrega(medico3);
        usuarioDAO.agrega(medico4);
        usuarioDAO.agrega(medico5);
        usuarioDAO.agrega(medico6);

        Hospital hospital1 = new Hospital("Hospital Central", "Calle Primera 111");
        Hospital hospital2 = new Hospital("Hospital Descentral", "Calle Segunda 222");
        Hospital hospital3 = new Hospital("Hospital Chispas", "Calle Tercera 333");
        Hospital hospital4 = new Hospital("Hospital San Juan", "Calle Cuarta 444");
        Hospital hospital5 = new Hospital("Hospital Norte", "Calle Quinta 555");
        Hospital hospital6 = new Hospital("Hospital Sur", "Calle Sexta 666");

        hospitalDAO.agregaHospital(hospital1);
        hospitalDAO.agregaHospital(hospital2);
        hospitalDAO.agregaHospital(hospital3);
        hospitalDAO.agregaHospital(hospital4);
        hospitalDAO.agregaHospital(hospital5);
        hospitalDAO.agregaHospital(hospital6);

        Usuario paciente1 = new Usuario("Pedro Morago", "pedromorago", "pedromorago@email.com", "pedromorago", "12345678B", "BB2345678901", "123456789", "Calle Sol 123", LocalDate.of(1985, 3, 15), Usuario.Rol.PACIENTE);
        Usuario paciente2 = new Usuario("Luis Sánchez", "luissanchez", "luissanchez@email.com", "luissanchez", "23456789C", "CC3456789012", "234567890", "Av. del Río 456", LocalDate.of(1990, 7, 20), Usuario.Rol.PACIENTE);
        Usuario paciente3 = new Usuario("Francisco Javier Córdoba", "fjcordoba", "fjcordoba@email.com", "fjcordoba", "34567890D", "DD4567890123", "345678901", "Plaza Mayor 789", LocalDate.of(1978, 12, 5), Usuario.Rol.PACIENTE);

        usuarioDAO.agrega(paciente1);
        usuarioDAO.agrega(paciente2);
        usuarioDAO.agrega(paciente3);

        LocalDate fechaGenerica = LocalDate.of(2026, 4, 15);
        LocalTime horaGenerica = LocalTime.of(9,30);

        citaDAO.agregaCita(new Cita(fechaGenerica, paciente1, medico1, hospital1, horaGenerica, hospital1.getDireccion(), "Consulta general", Cita.EstadoCita.CONFIRMADA, "General", 50.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, paciente2, medico2, hospital2, horaGenerica, hospital2.getDireccion(), "Chequeo anual", Cita.EstadoCita.PENDIENTE, "Cardiología", 100.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, paciente3, medico3, hospital3, horaGenerica, hospital3.getDireccion(), "Chequeo anual", Cita.EstadoCita.CONFIRMADA, "Pediatría", 80.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, paciente1, medico4, hospital4, horaGenerica, hospital4.getDireccion(), "Chequeo anual", Cita.EstadoCita.PENDIENTE, "Cardiología", 120.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, paciente2, medico5, hospital5, horaGenerica, hospital5.getDireccion(), "Consulta general", Cita.EstadoCita.PENDIENTE, "Pediatría", 60.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, paciente3, medico6, hospital6, horaGenerica, hospital1.getDireccion(), "Consulta general", Cita.EstadoCita.CONFIRMADA, "Cardiología", 75.0));

        citaDAO.agregaCita(new Cita(fechaGenerica, null, medico1, hospital1, horaGenerica, hospital1.getDireccion(), null, Cita.EstadoCita.PENDIENTE, "General", 50.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, null, medico2, hospital2, horaGenerica, hospital2.getDireccion(), null, Cita.EstadoCita.PENDIENTE, "Cardiología", 100.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, null, medico3, hospital3, horaGenerica, hospital3.getDireccion(), null, Cita.EstadoCita.PENDIENTE, "Pediatría", 90.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, null, medico4, hospital4, horaGenerica, hospital4.getDireccion(), null, Cita.EstadoCita.PENDIENTE, "Cardiología", 80.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, null, medico5, hospital5, horaGenerica, hospital5.getDireccion(), null, Cita.EstadoCita.PENDIENTE, "Pediatría", 120.0));
        citaDAO.agregaCita(new Cita(fechaGenerica, null, medico6, hospital6, horaGenerica, hospital6.getDireccion(), null, Cita.EstadoCita.PENDIENTE, "General", 50.0));

        log.info(">>> Datos de prueba inicializados");
    }
}