<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="/img/logo.png" type="image/x-icon">
        <link rel="stylesheet" href="css/nav.css">
        <script src="js/nav.js" defer></script>
        <title>Noticias</title>
    </head>
    <body>
        <%@ include file="WEB-INF/jspf/nav.jspf" %>
        <h2>Bienvenido</h2>
        <c:choose>
            <%-- Verifica si el usuario es nulo en la sesión --%>
            <c:when test="${empty sessionScope.usuario}">
                <p>No hay usuario registrado. Por favor, inicie sesión.</p>
                
            </c:when>
            <%-- Si existe un usuario en la sesión --%>
            <c:otherwise>
                <p>¡Bienvenido, ${sessionScope.usuario.username}!</p>
                <p>¡Tiene el rol de, ${sessionScope.usuario.rol}!</p>
                <form action="Usuario" method="get">
                    <input type="hidden" name="action" value="cerrarSesion">
                    <button type="submit">Cerrar Sesión</button>
                </form>
            </c:otherwise>
        </c:choose>



        <%@ include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>