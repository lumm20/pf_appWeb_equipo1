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
                <button class="category-button active">Todos</button>
                <button class="category-button">Mario Bros</button>
                <button class="category-button">Pokemon</button>
                <button class="category-button">Animal crossing</button>
                <button class="category-button">Zelda</button>
            </div>

            <div id="posts-container">
                <!-- Post 1 -->
                <div class="post-card">
                    <div class="post-header">
                        <div class="post-author">
                            <div class="author-avatar"></div>
                            <div class="author-info">
                                <span class="author-name">Link</span>
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
                    <img src="../img/noti_ejemplo.png" alt="Imagen de Link" class="post-image">
                    <p class="post-content">¡Increíble juego! Como fan de la saga Zelda, puedo decir que Echoes of Wisdom supera todas mis expectativas. La atención al detalle en el mundo de Hyrule es asombrosa, y las nuevas mecánicas de juego añaden una frescura que no sabía que necesitaba. Definitivamente, Nintendo ha demostrado una vez más por qué Zelda es una de las franquicias más queridas en la industria de los videojuegos.</p>
                </div>

                <!-- Post 2 -->
                <div class="post-card">
                    <div class="post-header">
                        <div class="post-author">
                            <div class="author-avatar"></div>
                            <div class="author-info">
                                <span class="author-name">Mario</span>
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
                    <img src="../img/noti_ejemplo.png" alt="Imagen de Mario" class="post-image">
                    <p class="post-content">Super Mario Wonder es una explosión de creatividad. Cada nivel es una sorpresa, y las nuevas transformaciones son hilarantes. ¡No puedo dejar de sonreír mientras juego!</p>
                </div>

                <!-- Post 3 -->
                <div class="post-card">
                    <div class="post-header">
                        <div class="post-author">
                            <div class="author-avatar"></div>
                            <div class="author-info">
                                <span class="author-name">Isabelle</span>
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
                    <img src="../img/noti_ejemplo.png" alt="Imagen de Isabelle" class="post-image">
                    <p class="post-content">La última actualización de Animal Crossing: New Horizons trae tantas cosas nuevas para hacer en la isla. ¡Estoy emocionada de poder cultivar más vegetales y tener nuevos vecinos!</p>
                </div>
            </div>

            <button class="load-more" id="load-more-btn">Ver más</button>
        </main>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>
        
    </body>
</html>
