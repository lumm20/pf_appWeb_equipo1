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
        <link rel="stylesheet" href="../css/publicacionEspecifica.css">
        <link rel="stylesheet" href="../css/nav.css">
        <script src="../js/nav.js" defer></script>
        <script src="../js/publicacionEspecifica.js" defer></script>
    </head>
    <body>
        <%@ include file="WEB-INF/jspf/nav.jspf" %>
        <main>
            <article>
                <section class="article-header">
                    <div class="autor-header">
                        <img src="../img/sanji_cartel.jpg" alt="perfil">
                        <h4 class="autor-name">"${sessionScope.post.usernamePublicador}"</h4>
                    </div>

                    <div class="article-meta">
                        <time datetime="2024-03-27">Fecha: 27/03/2024</time>
                        <span class="category">Categoría: The Legend Zelda</span>
                    </div>

                    <div class="article-content">
                        <p class="article-text">
                            The Legend of Zelda: Echoes of Wisdom es el más reciente título de la 'saga más legendaria de
                            Nintendo', que plantea muchos cambios con relación a lo que popularmente es conocido como la
                            'línea
                            2D' y cuyo anuncio nos tomó a todos por sorpresa a mitad de año.
                        </p>
                        <p class="article-text">
                            Parecía que Nintendo ya había cerrado las filas para la Switch, y que las grandes novedades
                            llegarían para la nueva consola. Pero no podíamos estar más equivocados; como siempre, la 'Gran
                            N'
                            dio con el factor sorpresa para, no sólo lanzar un título como pensamos que ya lo veríamos sino
                            que
                            también por hacerlo acompañado por una revolución.
                        </p>
                    </div>
                    <img src="../img/noti_ejemplo.png" alt="The Legend of Zelda: Echoes of Wisdom - Imagen del juego"
                         class="featured-image">
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
                </section>

                <div class="interactions">
                    <div class="like-section">
                        <form action="Publicacion" method="post">
                            <input type="hidden" name="action" value="like">
                            <button id="likeButton" type="submit" class="button">
                                <img id="likeIcon" src="../img/mano.png" alt="Like" class="interaction-icon like-icon">
                            </button>
                            <p id="cant-likes" class="interaction-count">10<span class="interaction-label">likes</span></p>
                        </form>

                    </div>
                    <div class="comment-section">
                        <img id="comment-icon" src="../img/comentario (1).png" alt="Comentarios"
                             class="interaction-icon comment-icon">
                        <p id="cant-comments" class="interaction-count">10<span class="interaction-label">comentarios</span>
                        </p>
                    </div>
                </div>
                <section class="comments-section">
                    <div class="comments-header">
                        <h2>Comentarios <span>10</span></h2>
                    </div>

                    <form class="comment-form">
                        <textarea placeholder="Escribe algo..."></textarea>
                    </form>

                    <div id="comments-div" class="comments-list">
                        <article class="comment comment-featured">
                            <div class="comment-avatar"></div>
                            <div class="comment-content">
                                <h3 class="comment-author">Link <span class="featured-label">Destacado</span></h3>
                                <time class="comment-date" datetime="2024-03-27">Hace 1 día</time>
                                <p>¡Increíble juego! Como fan de la saga Zelda, puedo decir que Echoes of Wisdom supera
                                    todas mis expectativas. La atención al detalle en el mundo de Hyrule es asombrosa, y las
                                    nuevas mecánicas de juego añaden una frescura que no sabía que necesitaba.
                                    Definitivamente, Nintendo ha demostrado una vez más por qué Zelda es una de las
                                    franquicias más queridas en la industria de los videojuegos.</p>
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
                                <time class="comment-date" datetime="2024-03-27">Hace 2 horas</time>
                                <p>Todo tiene un lado 'B'. Y aunque como fan de la saga es complicado admitir las
                                    imperfecciones, también son parte de, aunque a mi parecer, son casi insignificantes al
                                    lado de lo bueno que hay para.</p>
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
                                <p>Todo tiene un lado 'B'. Y aunque como fan de la saga es complicado admitir las
                                    imperfecciones, también son parte de, aunque a mi parecer, son casi insignificantes al
                                    lado de lo bueno que hay para.</p>
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
