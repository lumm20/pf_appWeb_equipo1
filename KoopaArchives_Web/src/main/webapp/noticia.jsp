<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalles Noticia</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/noticia.css">
        <link rel="stylesheet" href="css/nave.css">
        <script src="js/nav.js" defer></script>
        <script src="js/comentariosNoticia.js" defer></script>
        <script src="js/notician.js" defer></script>
    </head>

    <body>
        <%@ include file="WEB-INF/jspf/nav.jspf" %>
        <main>
        <article id="news-details">
            <!-- News details will be dynamically inserted here -->
            <section class="article-header">
                <h4 class="article-title"></h4>
                <div class="article-meta">
                    <p id="article-fecha" class="publication-date"></p>
                    <span class="category"></span>
                </div>
                <div class="article-image-container">
                    <img class="article-image" src="" alt="">
                    <p class="article-image-caption"></p>
                </div>
            </section>

            <div class="article-content">
                <!-- Paragraphs will be dynamically inserted -->
            </div>

            <section class="comments-section">
                <!-- Comments section structure remains similar -->
                <div class="comments-header">
                    <h2>Comentarios <span id="numero-comentarios"></span></h2>
                </div>
            </section>
        </article>
    </main>
        <%@ include file="WEB-INF/jspf/modal.jspf" %>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>
    </body>

</html>