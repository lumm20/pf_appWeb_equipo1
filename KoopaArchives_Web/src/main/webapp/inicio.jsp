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
        <link rel="stylesheet" href="css/destacadas.css">
        <link rel="stylesheet" href="css/nav.css">
        <script src="js/nav.js" defer></script>
        <title>Noticias</title>
    </head>
    <body>
        <%@ include file="WEB-INF/jspf/nav.jspf" %>
        <c:choose>
            <%-- Verifica si el usuario es nulo en la sesión --%>
            <c:when test="${empty sessionScope.usuario}">
                <h2>Bienvenido</h2>
                <p>No hay usuario registrado. Por favor, inicie sesión.</p>

            </c:when>
            <%-- Si existe un usuario en la sesión --%>
            <c:otherwise>
                <!--form action="Usuario" method="get">
                    <input type="hidden" name="action" value="cerrarSesion">
                    <button class="item button especial2 logout" type="submit">Cerrar Sesión</button>
                </form-->

                <main>
                    <h3 class="destacadas_titulo">Noticias destacadas</h3>
                    <section class="news-grid">
                        <c:forEach items="${destacadas}" var="destacada">
                            <article class="news-item">
                                <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                                <div class="news-content">
                                    <span class="news-category">The Legend of Zelda</span>
                                    <h2 class="news-title">El sorpresivo último baile de Hyrule en la Nintendo Switch</h2>
                                    <p class="news-description">The Legend of Zelda: Echoes of Wisdom es el más reciente título de la
                                        saga más legendaria de Nintendo</p>
                                </div>
                            </article>
                        </c:forEach>
                        <article class="news-item">
                            <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                            <div class="news-content">
                                <span class="news-category">The Legend of Zelda</span>
                                <h2 class="news-title">El sorpresivo último baile de Hyrule en la Nintendo Switch</h2>
                                <p class="news-description">The Legend of Zelda: Echoes of Wisdom es el más reciente título de la
                                    saga más legendaria de Nintendo</p>
                            </div>
                        </article>
                        <article class="news-item">
                            <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                            <div class="news-content">
                                <span class="news-category">Gameplay</span>
                                <h2 class="news-title">Nuevas mecánicas de juego en Echoes of Wisdom</h2>
                                <p class="news-description">Descubre las innovadoras mecánicas que traerá el nuevo Zelda a la
                                    franquicia</p>
                            </div>
                        </article>
                        <article class="news-item">
                            <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                            <div class="news-content">
                                <span class="news-category">Personajes</span>
                                <h2 class="news-title">El regreso de personajes icónicos</h2>
                                <p class="news-description">Link y Zelda vuelven a protagonizar una nueva aventura llena de
                                    misterios</p>
                            </div>
                        </article>
                        <article class="news-item">
                            <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                            <div class="news-content">
                                <span class="news-category">Tecnología</span>
                                <h2 class="news-title">Gráficos mejorados para la Switch</h2>
                                <p class="news-description">Echoes of Wisdom aprovecha al máximo las capacidades de la consola de
                                    Nintendo</p>
                            </div>
                        </article>
                        <article class="news-item">
                            <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                            <div class="news-content">
                                <span class="news-category">Jugabilidad</span>
                                <h2 class="news-title">Nuevo sistema de combate revelado</h2>
                                <p class="news-description">El juego introduce un sistema de combate renovado que promete emocionar
                                    a los fans</p>
                            </div>
                        </article>
                        <article class="news-item">
                            <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                            <div class="news-content">
                                <span class="news-category">Mundo Abierto</span>
                                <h2 class="news-title">Exploración de un vasto mundo abierto</h2>
                                <p class="news-description">Hyrule se expande con nuevas áreas para explorar y secretos por
                                    descubrir</p>
                            </div>
                        </article>
                        <article class="news-item">
                            <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                            <div class="news-content">
                                <span class="news-category">Lanzamiento</span>
                                <h2 class="news-title">Fecha de lanzamiento y ediciones especiales</h2>
                                <p class="news-description">Conoce cuándo podrás jugar Echoes of Wisdom y las ediciones
                                    coleccionistas disponibles</p>
                            </div>
                        </article>
                    </section>

                    <!-- Añadir esto después de la sección del grid anterior -->
                    <section class="latest-news">
                        <div class="latest-news-header">
                            <h2>Últimas noticias</h2>
                            <div class="category-filter">
                                <label for="categoria">Categoría</label>
                                <!-- <input type="text" id="categoria" name="categoria"> -->
                                <select id="categoria" name="videojuegos">
                                    <option value="ultimas" selected>Últimas</option>
                                    <option value="mario-bros">Mario Bros</option>
                                    <option value="zelda">The Legend Of Zelda</option>
                                    <option value="kirby">Kirby</option>
                                    <option value="smash-bros">Smash Bros</option>
                                    <option value="metroid">Metroid</option>
                                </select>
                            </div>
                        </div>

                        <div class="news-list">
                            <c:forEach items="${noticias}" var="noticia">
                                <article class="news-list-item">
                                    <img src="data:${noticia.tipoArchivo};base64,${noticia.imgNoticia}" alt="${noticia.nombreArchivo}">
                                    <div class="news-list-content">
                                        <h3>${noticia.titulo}</h3>
                                        <p>${noticia.contenido}</p>
                                        <span class="category">${noticia.categoria}</span>
                                    </div>
                                </article>
                            </c:forEach>
                            <article class="news-list-item">
                                <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                                <div class="news-list-content">
                                    <h3>El sorpresivo último baile de Hyrule en la Nintendo Switch...</h3>
                                    <p>The Legend of Zelda: Echoes of Wisdom es el más reciente título de la saga más legendaria de
                                        Nintendo' ...</p>
                                    <span class="category">The Legend of Zelda</span>
                                </div>
                            </article>

                            <article class="news-list-item">
                                <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                                <div class="news-list-content">
                                    <h3>El sorpresivo último baile de Hyrule en la Nintendo Switch...</h3>
                                    <p>The Legend of Zelda: Echoes of Wisdom es el más reciente título de la saga más legendaria de
                                        Nintendo' ...</p>
                                    <span class="category">The Legend of Zelda</span>
                                </div>
                            </article>

                            <article class="news-list-item">
                                <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                                <div class="news-list-content">
                                    <h3>El sorpresivo último baile de Hyrule en la Nintendo Switch...</h3>
                                    <p>The Legend of Zelda: Echoes of Wisdom es el más reciente título de la saga más legendaria de
                                        Nintendo' ...</p>
                                    <span class="category">The Legend of Zelda</span>
                                </div>
                            </article>

                            <article class="news-list-item">
                                <img src="../img/noti_ejemplo.png" alt="Portada Juego The Legend Of Zelda: Echo of Wisdom">
                                <div class="news-list-content">
                                    <h3>El sorpresivo último baile de Hyrule en la Nintendo Switch...</h3>
                                    <p>The Legend of Zelda: Echoes of Wisdom es el más reciente título de la saga más legendaria de
                                        Nintendo' ...</p>
                                    <span class="category">The Legend of Zelda</span>
                                </div>
                            </article>
                        </div>

                        <button class="load-more">Ver más...</button>
                    </section>
                </main>
            </c:otherwise>
        </c:choose>
        <%@ include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>