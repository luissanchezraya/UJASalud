<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="mensaje" required="true" type="java.lang.String" %>

<footer class="bg-dark text-light text-center p-3" style="position: fixed; bottom: 0; width: 100%;">
    <div class="container">
        <p class="mb-1">&copy; 2025 UJASalud - Todos los derechos reservados</p>
        <p class="mb-1">Teléfono de contacto: 900 700 500</p>
        <p class="mb-1">Correo de soporte: soporte@ujasalud.com</p>
        <p class="mb-1">Ubicación: Campus Las Lagunillas s/n, 23071, Jaén</p>
        <p class="mb-0"><strong><%= mensaje %></strong></p>
    </div>
</footer>
