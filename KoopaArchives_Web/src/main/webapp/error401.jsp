<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error 401</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/css/error.css">
    <link rel="stylesheet" href="/css/nav.css">
    <script src="/js/nav.js" defer></script>
    </head>
    <body>
        <div class="container">
         <%@ include file="WEB-INF/jspf/nav.jspf" %>
         <main class="error-content">
             <img src="/img/akainu.png" alt="Imagen de un Marine" class="error-image">
             <h3>Alto ahí pirata. Creo que vas por el camino equivocado</h3>
             <p class="error-description">Necesitas una autorización de nivel Marine o superior para acceder</p>
             <button onclick="">Regresar</button>
         </main>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>
        </div>
    </body>
</html>
