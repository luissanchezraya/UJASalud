# Changelog

Más información sobre cómo rellenar el fichero en https://keepachangelog.com/es-ES/1.0.0/

## Iteración inicial - 30/01/2025

### Added

- Página principal ``index.html`` (Francisco Javier Córdoba Castillo)
- Página de login ``login.html`` (Francisco Javier Córdoba Castillo)
- Página de registro ``registro.html`` (Francisco Javier Córdoba Castillo)

- Página de inicio ``pantallaInicio.html`` (Luis Sánchez Raya)
- Página de búsqueda de citas ``pantallaBusqueda.html`` (Luis Sánchez Raya)
- Página de seleccion de citas ``seleccionCita.html`` (Luis Sánchez Raya)
- Página de confirmar cita ``confirmarCita.html`` (Luis Sánchez Raya)

- Página de calendario de citas ``calendarioMedico.html`` (Pedro Morago Padilla)
- Página de citas del médico ``citasMedico.html`` (Pedro Morago Padilla)
- Página de edicion de citas ``edicionCitas.html`` (Pedro Morago Padilla)

### Changed

### Fixed

### Removed


## Iteración 1 - 06/03/2025

### Added

- Página principal ``index.xhtml``
- Página de login ``login.xhtml``
- Página de registro ``registro.xhtml``
- Página de inicio ``pantallaInicio.xhtml``
- Página de búsqueda de citas ``pantallaBusqueda.xhtml``
- Página de seleccion de citas ``seleccionCita.xhtml``
- Página de confirmar cita ``confirmarCita.xhtml``
- Página de calendario de citas ``calendarioMedico.xhtml``
- Página de citas del médico ``citasMedico.xhtml``
- Página de edicion de citas ``edicionCitas.xhtml``
- Template cabecera ``cabecera.xhtml``
- Template pie de página ``pie_pagina.xhtml``
- Template recursos de bootstrap ``recursos_bootstrap.xhtml``

- Página principal ``index.jsp``
- Página de login ``login.jsp``
- Página de registro ``registro.jsp``
- Página de inicio ``pantallaInicio.jsp``
- Página de búsqueda de citas ``pantallaBusqueda.jsp``
- Página de seleccion de citas ``seleccionCita.jsp``
- Página de confirmar cita ``confirmarCita.jsp``
- Página de calendario de citas ``calendarioMedico.jsp``
- Página de citas del médico ``citasMedico.jsp``
- Página de edicion de citas ``edicionCitas.jsp``
- Template cabecera ``cabecera.jspf``
- Template pie de página ``pie_pagina.jspf``
- Template recursos de bootstrap ``recursos_bootstrap.jspf``

- Pie de página personalizado ``pie_pagina.tag``

- busquedaBean.java
- ControladorCitas.java
- ControladorHospitales.java
- ControladorUsuarios.java
- EdicionCitaServlet.java
- editarCitaBean.java
- loginBean.java
- LoginServlet.java
- registroBean.java

- Entidades Usuario, Hospital y Cita
- DAOs CitaDAO, HospitalDAO y UsuarioDAO

- Validación de formularios.
- Flujo de navegación.
- Logo con link a ``pantalla_inicio.xhtml``
- Pie de página personalizado.

### Changed

- URLs de las imagenes arreglados.
- Nombres de los archivos con el formato calendario_medico en lugar de calendarioMedico.

### Fixed

### Removed

## Iteración 2 - 20/03/2025

### Added

- Operaciones básicas faltantes en los DAOs (Pedro Morago Padilla).
- Restricción de longitud del nombre de usuario con ``f:validate`` (Francisco Javier Córdoba Castillo).
- Buscar una cita por médico mediante una subcadena (Pedro Morago Padilla).
- Botón cancelar para eliminar una cita de la vista (Francisco Javier Córdoba Castillo).
- Actualización dinámica del listado de citas al buscar por una subcadena (Pedro Morago Padilla).
- Reconocimiento de tildes (Francisco Javier Córdoba Castillo).
- Componentes personalizados (Luis Sánchez Raya).
- Vista para editar los datos del usuario (Francisco Javier Córdoba Castillo).
- Actualización para mostrar los datos del usuario (Luis Sánchez Raya).
- Etiqueta ``AJAX`` para actualizar el listado de citas sin recargar la página (Pedro Morago Padilla).
- Mostrar datos del paciente y médico en la vista ``/cita/confirmar`` (Luis Sánchez Raya).
- Fecha de nacimiento al alta de usuario con input nativo del navegador y uso de conversión de datos (Pedro Morago Padilla).
- Visualización de fecha de nacimiento en los datos del usuario (Pedro Morago Padilla).
- Validación de datos con Bean Validation (Pedro Morago Padilla).
- Uso de ``Primefaces`` (Luis Sánchez Raya).
- Actualizaciones asíncronas de partes de vistas (Luis Sánchez Raya).
- ``calendar.js`` (Francisco Javier Córdoba Castillo).
- ``/WEB-INF/plantilla`` (Francisco Javier Córdoba Castillo).
- Visualizar datos de la cita en ``/cita/edicion`` (Luis Sánchez Raya).

### Changed

- Buscar cita por médico en lugar de por fecha (Pedro Morago Padilla).
- Refinamiento de vistas y formato en ``/cita/confirmar`` (Francisco Javier Córdoba Castillo).

### Fixed

- Cambio de etiquetas nativas ``HTML`` a ``XHTML`` (Francisco Javier Córdoba Castillo).
- Beans unificados dentro de los controladores (Pedro Morago Padilla).
- Reubicación de la carpeta ``META-INF`` (Francisco Javier Córdoba Castillo).
- Agrupadas algunas vistas por entidades (Pedro Morago Padilla).
- Extensiones de enlaces a páginas (Francisco Javier Córdoba Castillo).
- Espacio de nombres ``jakarta.faces.passthrough`` para identificar atributos nativos de html que pasarle a la etiqueta (Pedro Morago Padilla).
- Tipo enumerado para el estado de una cita (Francisco Javier Córdoba Castillo).
- Almacenar solamente el ID de usuario en lugar del usuario completo (Francisco Javier Córdoba Castillo).

### Removed
- Vistas ``JSP`` (Francisco Javier Córdoba Castillo).
- Vistas ``HTML`` (Francisco Javier Córdoba Castillo).
- Servlets: EdicionCitaServlet, HelloServlet, LoginServlet (Pedro Morago Padilla).


## Iteración 3 - 04/04/2025

### Added
- Anotaciones JPA en las entidades (Pedro Morago Padilla).
- Configuraciones ``web.xml`` y ``persistence.xml`` (Pedro Morago Padilla).
- ``DAOJpa``, ``UsuarioDAOJPA`` y ``CitaDAOJPA`` (Pedro Morago Padilla).
- Control de acceso a recursos web (Pedro Morago Padilla).
- Autenticación de usuario básica (Pedro Morago Padilla).
- Desconexión de usuarios de una aplicación web (Pedro Morago Padilla).
- Regla de acceso para que el rol ``ADMIN`` sea el único que acceda a la gestión de citas (Pedro Morago Padilla).
- Se muestran botones diferentes en ``pantalla_inicio.xhtml`` en función de si el rol es ``ADMIN`` o ``USER`` (Pedro Morago Padilla).
- Autenticación mediante formulario estándar (Pedro Morago Padilla).
- La pantalla de inicio al desplegar la aplicación es ``index.xhtml`` por defecto (Francisco Javier Córdoba Castillo).
- Interfaces de cada DAO (Francisco Javier Córdoba Castillo).
- Converter para evitar fecha tipo String (Francisco Javier Córdoba Castillo).
- Clases JPA (Francisco Javier Córdoba Castillo).
- Almacén de entidad personalizado (Francisco Javier Córdoba Castillo).
- IdentityStore obtiene usuario y clave de ``UsuarioDAO`` (Francisco Javier Córdoba Castillo).
- Creación clase ``UsuarioSession.java`` (Luis Sánchez Raya)
- Interfaz de ``HospitalDAO`` (Luis Sánchez Raya)
- Añadir Precio a ``Cita`` (Luis Sánchez Raya)
- Modificar todas las vistas que incluyan citas para mostrar el precio (Luis Sánchez Raya)
- Filtro para que el precio sea mayor igual a 0 (Luis Sánchez Raya)


### Changed

### Fixed
- Problema en la búsqueda y selección de citas ``pantalla_busqueda.xhtml`` y ``cita/seleccion.xhtml`` (Pedro Morago Padilla).
- No se eliminaba una cita al pulsar el botón ``eliminar`` (Pedro Morago Padilla).
- Inicialización de datos para que se almacenen en ``CitaDAO`` (Pedro Morago Padilla).
- Problema al editar la hora de una cita (Pedro Morago Padilla).
- Cita tiene nombrePaciente en lugar de usuarioID para que sea de tipo String (Francisco Javier Córdoba Castillo).
- BeanValidation cambiados de Controladores a Entidades (Francisco Javier Córdoba Castillo).
- Datos de AppConfig cambiados a Controladores (Francisco Javier Córdoba Castillo).
- Actualizar datos en ``editar.xhtml`` y ``pantalla_inicio.xhtml`` (Francisco Javier Córdoba Castillo).
- Hora con LocalTime en lugar de String (Francisco Javier Córdoba Castillo).
- Arreglo formato ``pantalla_inicio.xhtml`` (Luis Sánchez Raya)
- Cambios de sessionScope a usuarioSession (Luis Sánchez Raya)
- Parte del arreglo del problema de ``edicion.xhtml`` (Luis Sánchez Raya)
- Cambios de sessionScope a CitaSession en ``confirmar.xhtml`` (Luis Sánchez Raya)
- Arreglo vista ``seleccion.xhtml`` (Luis Sánchez Raya)
- 

### Removed


## Iteración 4 - 19/04/2025

### Added
- Roles de paciente y usuario en ``Usuario`` (Francisco Javier Córdoba Castillo).
- Qualifier ``DAOMap`` (Francisco Javier Córdoba Castillo).
- Desplegable en ``pantalla_busqueda.xhtml`` para seleccionar un hospital entre los disponibles (Pedro Morago Padilla).
- Filtrado completo al buscar una cita con todos los parámetros (Pedro Morago Padilla).
- Nueva vista para ver las citas propias de cada usuario (Luis Sánchez Raya)
- Logs personalizados en ``ControladorUsuarios`` al cerrar sesión, registrar usuario y editar usuario (Francisco Javier Córdoba Castillo).
- Filtro para que el médico gestione las citas según la fecha (Pedro Morago Padilla).
- Logs personalizados en ``ControladorCitas`` al seleccionar, editar y borrar una cita (Pedro Morago Padilla).
- Se actualiza la cita en la base de datos añadiendo el paciente que la confirma (Pedro Morago Padilla).
- Mensajes personalizados para cuando no está vacía la búsqueda o no se encontraron resultados (Francisco Javier Córdoba Castillo).
- Operación de borrado de citas por parte de paciente y médico (Pedro Morago Padilla).
- Vista para administrar usuarios y hospitales (Pedro Morago Padilla).
- Vistas de creación, edición y borrado de usuario y hospital (Francisco Javier Córdoba Castillo).
- Búsqueda de usuarios y hospitales por subcadena para el administrador (Pedro Morago Padilla).
- Registro de usuarios y hospitales nuevos (Francisco Javier Córdoba Castillo).
- Edición de usuarios y hospitales para el administrador (Pedro Morago Padilla).
- Vistas para el administrador protegidas (Pedro Morago Padilla).
- Funcionalidad de eliminar usuarios y eliminar hospitales como administrador (Luis Sánchez Raya)

### Changed

### Fixed
- Médico de tipo Usuario en lugar de String en ``Cita`` (Francisco Javier Córdoba Castillo).
- Anotación @PostConstruct para cargar los datos en los controladores (Pedro Morago Padilla).
- Llave primaria de ``Hospital`` en ``Cita`` (Francisco Javier Córdoba Castillo).
- Visualización de datos en la pantalla ``cita/seleccion.xhtml`` (Pedro Morago Padilla).
- Rol de usuario por atributo en ``UJASaludIdentityStore`` (Francisco Javier Córdoba Castillo).
- Llave primaria con el ID de ``Usuario`` en ``Cita`` (Francisco Javier Córdoba Castillo).
- Uso del conversor de fecha en ``pantalla_busqueda`` en lugar de usar la clase ``Fecha`` del package ``converter`` (Francisco Javier Córdoba Castillo).
- Cambio de String a LocalTime para el atributo Hora (Pedro Morago Padilla).
- Ya no creamos dos usuarios en el registro (Pedro Morago Padilla).
- Ya se muestran los datos del usuario al hacer login (Luis Sánchez Raya)
- Datos aparecen por defecto al ir a ``editar.xhtml`` (Luis Sánchez Raya)
- Edición, borrado y visualización de detalles de un hospital (Luis Sánchez Raya)
- Uso de DAOs JPA (Francisco Javier Córdoba Castillo).
- Ahora se muestra correctamente los datos del usuario logeado despues de registrarse previamente con otro usuario (Pedro Morago Padilla).
- Modificado médico solo pueda gestionar sus citas (Luis Sánchez Raya).
- Solo se muestran citas con paciente a null al buscar (Pedro Morago Padilla).

### Removed
- Restricciones de acceso en ``web.xml`` (Francisco Javier Córdoba Castillo).
- Package ``converter`` con su correspondiente ``Fecha`` (Francisco Javier Córdoba Castillo).