<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalles Publicacion</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="cssPrivate/publicacionEspecifica.css">
        <link rel="stylesheet" href="/css/nav.css">
        <script src="/js/nav.js" defer></script>
        <script src="jsPrivate/publicacionEspecifica.js" defer></script>
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/nav.jspf" %>
        <main>
            <article>
                <section class="article-header" >
                    <input type="hidden" id="codigoPost" name="codigoPost" value="${post.codigo}">
                    <div class="autor-header">
                        <img src="data:${iconoAutor.tipoImagen};base64,${iconoAutor.url}" alt="${iconoAutor.nombreArchivo}">
                        <h4 class="autor-name">${post.autor.username}</h4>
                    </div>

                    <div class="article-meta">
                        <time datetime="${fechaPost}"></time>
                        <span class="category">${post.categoria}</span>
                    </div>
                </section>
                <section class="article-main">
                    <div class="article-content">
                        <c:if test="${not empty parrafos}">
                            <c:forEach items="${parrafos}" var="parrafo">
                                <p id="parrafo" class="article-text active">${parrafo}</p>
                                <input id="input-text" class="article-text editable-text" value="${parrafo}">
                            </c:forEach>
                        </c:if>
                    </div>
                    <c:if test="${not empty imagenPost}">
                        <img src="data:${imagenPost.tipoImagen};base64,${imagenPost.url}" alt="${imagenPost.nombreArchivo}"
                             class="featured-image">
                    </c:if>
                    <c:if test="${post.autor.username eq sessionScope.usuario.username}">
                        <div class="options-menu">
                            <div class="options-menu-dots">
                                <div class="options-menu-dot"></div>
                                <div class="options-menu-dot"></div>
                                <div class="options-menu-dot"></div>
                            </div>
                            <div class="options-menu-content">
                                <div id="edit-btn" class="options-menu-item">Editar</div>
                                <div id="delete-btn" class="options-menu-item" >Eliminar</div>
                            </div>
                        </div>
                    </c:if> 
                        <div id="save" class="save">
                        <button id="save-btn" class="btn">Guardar cambios</button>
                        <button id="cancel-edit" class="btn">Cancelar</button>
                    </div>
                </section>
                <section class="interactions">
                    <div class="like-section">
                        <button id="likeButton" type="button" class="button">
                            <img id="likeIcon" src="imgPrivate/mano.png" alt="Like" class="interaction-icon like-icon">
                        </button>
                        <p id="cant-likes" class="interaction-count">${post.likes}<span class="interaction-label">likes</span></p>
                    </div>
                    <div class="comment-section">
                        <img id="comment-icon" src="imgPrivate/comentario.png" alt="Comentarios"
                             class="interaction-icon comment-icon">
                        <p id="cant-comments" class="interaction-count">${cantComentarios}<span class="interaction-label">comentarios</span>
                        </p>
                    </div>
                </section>
                <section class="comments-section">
                    <div class="comments-header">
                        <h2>Comentarios</h2>
                    </div>

                    <form class="comment-form" action="/private/Publicacion" method="post">
                        <input type="hidden" name="action" value="comentario">
                        <input type="hidden" name="codigoPost" value="${post.codigo}">
                        <textarea name="texto" maxlength="300" placeholder="Escribe algo..." required></textarea>
                        <button type="submit" class="">Publicar comentario</button>
                    </form>

                    <div id="comments-div" class="comments-list">
                        <c:if test="${not empty post.comentarios}">
                            <c:forEach items="${post.comentarios}" var="comentario">
                                <article class="comment comment-featured">
                                    <div class="comment-avatar">
                                        <img src="data:${comentario.imagenAutor.tipoImagen};base64,${comentario.imagenAutor.url}" alt="${comentario.imagenAutor.nombreArchivo}"/>
                                    </div>
                                    <div class="comment-content">
                                        <h3 class="comment-author">${comentario.autor}</h3>
                                        <time class="comment-date" datetime="${comentario.fechaCreacion}"></time>
                                        <p>${comentario.contenido}</p>
                                    </div>
                                    <c:if test="${comentario.autor.username eq sessionScope.usuario.username}">
                                        <div class="options-menu">
                                            <div class="options-menu-dots">
                                                <div class="options-menu-dot"></div>
                                                <div class="options-menu-dot"></div>
                                                <div class="options-menu-dot"></div>
                                            </div>
                                            <div class="options-menu-content">
                                                <div class="options-menu-item">Editar</div>
                                                <div class="options-menu-item">Eliminar</div>
                                            </div>
                                        </div>
                                    </c:if>
                                </article>
                            </c:forEach>
                        </c:if>
                    </div>

                </section>

            </article>
        </main>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
