<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="/img/logo.png" type="image/x-icon">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/destacadass.css">
        <link rel="stylesheet" href="css/nave.css">
        <script src="js/nav.js" defer></script>
        <title>Noticias</title>
    </head>
    <body>
        <%@ include file="WEB-INF/jspf/nav.jspf" %>
        <main>
            <h3 class="destacadas_titulo">Noticias destacadas</h3>
            <section class="news-grid">
                <c:forEach items="${destacadas}" var="destacada">
                    <a class="news-item" href="/CargarNoticia?id=${destacada.codigo}">

                        <img src="data:${destacada.imagen.tipoImagen};base64,${destacada.imagen.url}" alt="${destacada.imagen.nombreArchivo}">
                        <div class="news-content">
                            <span class="news-category">${destacada.categoria}</span>
                            <h2 class="news-title">${destacada.titulo}</h2>
                            <p class="news-description">${destacada.texto}</p>
                        </div>
                    </a>
                </c:forEach>

                </section>
                <!-- Añadir esto después de la sección del grid anterior -->
                <section class="latest-news">
                    <div class="latest-news-header">
                        <h2>Últimas noticias</h2>
                        
                    </div>
                <div class="news-list">
                    <c:forEach items="${normales}" var="noticia">
                        <a class="news-list-item" href="/CargarNoticia?id=${noticia.codigo}">
                            <img src="data:${noticia.imagen.tipoImagen};base64,${noticia.imagen.url}" alt="${noticia.imagen.nombreArchivo}">
                            <div class="news-list-content">
                                <h3>${noticia.titulo}</h3>
                                <p>${noticia.texto}</p>
                                <span class="category">${noticia.categoria}</span>
                            </div>
                        </a>
                    </c:forEach>

                </div>
            </section>


        </main>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>