<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Publicaciones</title>
        <link rel="icon" href="img/logo.png" type="image/x-icon">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <link rel="icon" href="img/logo.png" type="image/x-icon">
        <link rel="stylesheet" href="css/publicaciones.css">
        <link rel="stylesheet" href="css/nav.css">
        <script src="js/publicaciones.js" defer></script>
        <script src="js/nav.js" defer></script>
    </head>
    <body>
        <%@ include file="WEB-INF/jspf/nav.jspf" %>
        <main>
            <h3>Publicaciones</h3>

            <div class="category-buttons">
                <button id="todos-btn" class="category-button active" data-categoria="todos">Todos</button>
                <button id="mario-btn" class="category-button" data-categoria="mario-bros" >Mario Bros</button>
                <button id="pokemon-btn" class="category-button" data-categoria="pokemon">Pokemon</button>
                <button id="animalx-btn" class="category-button" data-categoria="animal-crossing">Animal crossing</button>
                <button id="zelda-btn" class="category-button" data-categoria="zelda">Zelda</button>
            </div>

            <div id="posts-container">
                <c:choose>
                    <c:when test="${not empty listaPublicaciones}">
                        <c:forEach items="${listaPublicaciones}" var="publicacion">
                            <div class="post-card" data-categoria="${publicacion.post.categoria}">
                                <div class="post-header">
                                    <div class="post-author">
                                        <div class="author-avatar">
                                            <img src="data:${publicacion.tipoArchivoIcon};base64,${publicacion.iconoPublicador}" alt="${publicacion.nombreArchivoIcon}" alt="perfil">
                                        </div>
                                        <div class="author-info">
                                            <span class="author-name">${publicacion.post.autor.username}</span>
                                            <span class="post-tag">Destacado</span>
                                            <div class="post-date">Hace 1 día</div>
                                        </div>
                                    </div>
                                    <div class="options-menu">
                                        <div class="options-menu-dots">
                                            <div class="options-menu-dot"></div>
                                            <div class="options-menu-dot"></div>
                                            <div class="options-menu-dot"></div>
                                        </div>
                                        <div class="options-menu-content">
                                            <div class="options-menu-item">Destacar noticia</div>
                                            <div class="options-menu-item">Editar</div>
                                            <div class="options-menu-item">Eliminar</div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${not empty publicacion.imagenPost}">
                                    <img src="data:${publicacion.tipoArchivoPost};base64,${publicacion.imagenPost}" alt="${publicacion.nombreArchivoPost}" class="post-image">
                                </c:if>
                                <p class="post-content">${publicacion.post.texto}</p>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>No hay publicaciones disponibles</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <button class="load-more" id="load-more-btn">Ver más</button>
        </main>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>

    </body>
</html>
