# Sistema de Gestión de Citas Médicas

## Información del Proyecto

### - **Nombre del Proyecto:** UJASalud
### - **Autores:** Luis Sánchez Raya, Pedro Morago Padilla, Francisco Javier Córdoba Castillo
### - **Repositorio Git:** [Repositorio](https://github.com/DAWUJA-2025/g2-equipo-205)

---

## Descripción del Proyecto

El Sistema de Gestión de Citas Médicas es una aplicación web que permite a los pacientes reservar citas médicas con especialistas, mientras que los médicos pueden gestionar su disponibilidad y controlar sus consultas. También se incluye un panel administrativo para gestionar médicos, especialidades y horarios.

---

## Requisitos Funcionales / Historias de Usuario

### **Gestión de Usuarios**
- Como usuario, quiero registrarme y acceder a mi cuenta para gestionar mis citas.  
- Como administrador, quiero gestionar los médicos y especialidades disponibles.  
- Como médico, quiero ver las citas que tengo asignadas.  

### **Gestión de Citas**
- Como usuario, quiero reservar una cita con un médico disponible.  
- Como usuario, quiero cancelar una cita en caso de no poder asistir.  
- Como sistema, quiero enviar notificaciones de confirmación de cita.  

### **Validaciones y Seguridad**
- Como sistema, quiero restringir el acceso a ciertas funcionalidades según el rol del usuario.  
- Como usuario, quiero recibir validaciones al ingresar mis datos en los formularios.  

---

## Diagrama Entidad-Relación (E/R)

![Diagrama Entidad-Relacion.png](Diagrama%20Entidad-Relacion.png)
### **Entidades Principales:**
- **Usuario (Paciente/Médico)** *(DNI, nombre, apellidos, email, telefono, contraseña, f_nacimiento, rol, direccion)*
- **CentroMedico** *(ID_centro, nombre, direccion, telefono, email, tipo)*
- **Cita** *(ID_cita, fechaHora, estado, tipo)*

---

## Storyboard

Puedes visualizar el **Storyboard** del proyecto en el siguiente enlace:  
[Storyboard](https://marvelapp.com/prototype/33f67816)

---

## URLs públicas
- Francisco Javier Córdoba Castillo: http://35.192.124.103
- Pedro Morago Padilla: http://34.56.139.99
- Luis Sánchez Raya: http://35.238.102.205