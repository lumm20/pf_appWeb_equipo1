<<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

                    <c:if test="${not empty sessionScope.usuario}">
                        <c:if test="${sessionScope.usuario.rol eq 'Normal'}">
                            <form class="comment-form" action="${pageContext.request.contextPath}/Noticia" method="post">
                                <input type="hidden" name="action" value="subirComentario">
                                <textarea placeholder="Escribe algo..." name="comentario"></textarea>
                                <div class="button-group">
                                    <button type="submit" class="btn btn-primary">Publicar</button>
                                </div>
                            </form>
                        </c:if>
                    </c:if>


                    <div class="comments-list">
                        <article class="comment comment-featured">
                            <div class="comment-avatar"></div>
                            <div class="comment-content">
                                <h3 class="comment-author">Link <span class="featured-label">Destacado</span></h3>
                                <time class="comment-date" datetime="2024-03-27">Hace 1 día</time>
                                <p>¡Increíble juego! Como fan de la saga Zelda, puedo decir que Echoes of Wisdom supera todas mis expectativas. La atención al detalle en el mundo de Hyrule es asombrosa, y las nuevas mecánicas de juego añaden una frescura que no sabía que necesitaba. Definitivamente, Nintendo ha demostrado una vez más por qué Zelda es una de las franquicias más queridas en la industria de los videojuegos.</p>
                            </div>
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
                        </article>

                        <c:if test="${not empty comentarioDestacado}">
                            <article class="comment comment-featured">
                                <div class="comment-avatar"></div>
                                <div class="comment-content">
                                    <h3 class="comment-author">Link <span class="featured-label">Destacado</span></h3>
                                    <time class="comment-date" datetime="2024-03-27">Hace 1 día</time>
                                    <p>¡Increíble juego! Como fan de la saga Zelda, puedo decir que Echoes of Wisdom supera todas mis expectativas. La atención al detalle en el mundo de Hyrule es asombrosa, y las nuevas mecánicas de juego añaden una frescura que no sabía que necesitaba. Definitivamente, Nintendo ha demostrado una vez más por qué Zelda es una de las franquicias más queridas en la industria de los videojuegos.</p>
                                </div>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.usuario}">
                                        <c:choose>
                                            <c:when test="${sessionScope.usuario.rol eq 'Admin'}">
                                                <div class="options-menu">
                                                    <div class="options-menu-dots">
                                                        <div class="options-menu-dot"></div>
                                                        <div class="options-menu-dot"></div>
                                                        <div class="options-menu-dot"></div>
                                                    </div>
                                                    <div class="options-menu-content">
                                                        <div class="options-menu-item">Destacar</div>
                                                        <div class="options-menu-item">Eliminar</div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${comentario.autor eq sessionScope.usuario}">
                                                    <div class="options-menu">
                                                        <div class="options-menu-dots">
                                                            <div class="options-menu-dot"></div>
                                                            <div class="options-menu-dot"></div>
                                                            <div class="options-menu-dot"></div>
                                                        </div>
                                                        <div class="options-menu-content">
                                                            <div class="options-menu-item">Eliminar</div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>  
                            </article>
                        </c:if>


                        <c:forEach items="${comentarios}" var="comentario">
                            <article class="comment">
                                <div class="comment-avatar"></div>
                                <div class="comment-content">
                                    <h3 class="comment-author">${comentario.autor}</h3>
                                    <p class="comment-date" datetime="2024-03-27">
                                        <fmt:formatDate value="${comentario.fechaCreacion}" 
                                                        pattern="dd/MM/yyyy HH:mm"/>
                                    </p>
                                    <p>${comentario.contenido}</p>
                                </div>

                                <c:choose>
                                    <c:when test="${not empty sessionScope.usuario}">
                                        <c:choose>
                                            <c:when test="${sessionScope.usuario.rol eq 'Admin'}">
                                                <div class="options-menu">
                                                    <div class="options-menu-dots">
                                                        <div class="options-menu-dot"></div>
                                                        <div class="options-menu-dot"></div>
                                                        <div class="options-menu-dot"></div>
                                                    </div>
                                                    <div class="options-menu-content">
                                                        <div class="options-menu-item">Destacar</div>
                                                        <div class="options-menu-item">Eliminar</div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${comentario.autor eq sessionScope.usuario}">
                                                    <div class="options-menu">
                                                        <div class="options-menu-dots">
                                                            <div class="options-menu-dot"></div>
                                                            <div class="options-menu-dot"></div>
                                                            <div class="options-menu-dot"></div>
                                                        </div>
                                                        <div class="options-menu-content">
                                                            <div class="options-menu-item">Eliminar</div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>  
                            </article>
                        </c:forEach>
 

                        <article class="comment">
                            <div class="comment-avatar"></div>
                            <div class="comment-content">
                                <h3 class="comment-author">John Lennon</h3>
                                <time class="comment-date" datetime="2024-03-27">Hace 2 horas</time>
                                <p>Todo tiene un lado 'B'. Y aunque como fan de la saga es complicado admitir las imperfecciones, también son parte de, aunque a mi parecer, son casi insignificantes al lado de lo bueno que hay para.</p>
                            </div>
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
                        </article>

                        <article class="comment">
                            <div class="comment-avatar"></div>
                            <div class="comment-content">
                                <h3 class="comment-author">John Lennon</h3>
                                <time class="comment-date" datetime="2024-03-27">Hace 3 horas</time>
                                <p>Todo tiene un lado 'B'. Y aunque como fan de la saga es complicado admitir las imperfecciones, también son parte de, aunque a mi parecer, son casi insignificantes al lado de lo bueno que hay para.</p>
                            </div>
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
                        </article>
                    </div>

                    <button class="load-more">Ver más comentarios</button>
                </section>
            </article>
        </main>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>
    </body>

</html>