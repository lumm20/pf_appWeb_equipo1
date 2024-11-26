<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalles Noticia</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/noticiaEspecifica.css">
        <link rel="stylesheet" href="css/nav.css">
    </head>
    <body>
        <%@ include file="WEB-INF/jspf/nav.jspf" %>
        <main>
            <article>
                <section class="article-header">
                    <h4 class="article-title">The Legend of Zelda: Echoes of Wisdom, el sorpresivo último baile de Hyrule en la Nintendo Switch</h4>
                    <div class="article-meta">
                        <time datetime="2024-03-27">Fecha: 27/03/2024</time>
                        <span class="category">Categoría: The Legend Zelda</span>
                    </div>
                    <img src="../img/noti_ejemplo.png" alt="The Legend of Zelda: Echoes of Wisdom - Imagen del juego" class="featured-image">
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

                <div class="article-content">
                    <p class="article-text">
                        The Legend of Zelda: Echoes of Wisdom es el más reciente título de la 'saga más legendaria de Nintendo', que plantea muchos cambios con relación a lo que popularmente es conocido como la 'línea 2D' y cuyo anuncio nos tomó a todos por sorpresa a mitad de año.
                    </p>
                    <p class="article-text">
                        Parecía que Nintendo ya había cerrado las filas para la Switch, y que las grandes novedades llegarían para la nueva consola. Pero no podíamos estar más equivocados; como siempre, la 'Gran N' dio con el factor sorpresa para, no sólo lanzar un título como pensamos que ya lo veríamos sino que también por hacerlo acompañado por una revolución.
                    </p>

                    <section class="article-section">
                        <h2 class="section-title">Los realmente pocos problemas del juego</h2>
                        <p class="article-text">
                            Todo tiene un lado 'B'. Y aunque como fan de la saga es complicado admitir las imperfecciones, también son parte de, aunque a mi parecer, son casi insignificantes al lado de lo bueno que hay para decir. Uno es técnico, y una cantaleta ya conocida en la Switch: el rendimiento.
                        </p>
                        <div class="article-image-container">
                            <img src="../img/noti_ejemplo.png" alt="Gameplay de The Legend of Zelda: Echoes of Wisdom" class="article-image">
                            <p class="article-image-caption">Escena de juego mostrando los gráficos detallados de Echoes of Wisdom</p>
                        </div>
                        <p class="article-text">
                            Por momentos, especialmente cuando exploras las zonas abiertas, parece que la consola no termina por renderizar los mapas el tiempo real, y se vuelven comunes y un poco molestos los bajones de fps, algo que realmente no entorpece en términos generales la experiencia.
                        </p>
                        <p class="article-text">
                            Independientemente de eso, el aspecto gráfico, retomado del remake de Link's Awakening, es hermoso, aunque ciertamente algunos escenarios pueden parecer repetitivos y sin tanto por ofrecer.
                        </p>
                    </section>
                </div>

                <section class="comments-section">
                    <div class="comments-header">
                        <h2>Comentarios <span>10</span></h2>
                    </div>

                    <form class="comment-form">
                        <textarea placeholder="Escribe algo..."></textarea>
                    </form>

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

                <section class="related-news">
                    <h2>Noticias relacionadas...</h2>
                    <div class="related-news-grid">
                        <article class="related-news-card">
                            <img src="../img/noti_ejemplo.png" alt="The Legend of Zelda: Echoes of Wisdom">
                            <div class="related-news-content">
                                <h3 class="related-news-title">El sorpresivo último baile de Hyrule en la...</h3>
                                <span class="related-news-category">The Legend of Zelda</span>
                            </div>
                        </article>

                        <article class="related-news-card">
                            <img src="../img/noti_ejemplo.png" alt="The Legend of Zelda: Echoes of Wisdom">
                            <div class="related-news-content">
                                <h3 class="related-news-title">El sorpresivo último baile de Hyrule en la...</h3>
                                <span class="related-news-category">The Legend of Zelda</span>
                            </div>
                        </article>

                        <article class="related-news-card">
                            <img src="../img/noti_ejemplo.png" alt="The Legend of Zelda: Echoes of Wisdom">
                            <div class="related-news-content">
                                <h3 class="related-news-title">El sorpresivo último baile de Hyrule en la...</h3>
                                <span class="related-news-category">The Legend of Zelda</span>
                            </div>
                        </article>
                    </div>
                </section>
            </article>
        </main>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>
        <script src="../js/nav.js" defer></script>
    </body>
</html>
