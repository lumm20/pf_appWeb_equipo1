<%-- 
    Document   : error404
    Created on : 23 nov 2024, 18:22:17
    Author     : karim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error 404</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="css/error.css">
    <link rel="stylesheet" href="css/nav.css">
    <script src="js/nav.js" defer></script>
    </head>
    <body>
        <div class="container">
         <%@ include file="WEB-INF/jspf/nav.jspf" %>
        
        <main class="error-content">
            <img src="img/zoro_perdido.jpg" alt="Imagen de algo perdido" class="error-image">
            <h3>¡Oops! Página no encontrada</h3>
            <p class="error-description">Lo sentimos, pero la página que estás buscando parece haberse perdido en el mar. ¿Quizás un pirata se la llevó?</p>
            <button onclick="">Regresar</button>
        </main>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>
        </div>
    </body>
</html>
