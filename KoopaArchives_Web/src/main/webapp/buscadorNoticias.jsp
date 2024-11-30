<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Buscador de noticias - Koopa Archives</title>
        <link rel="icon" href="img/logo.png" type="image/x-icon">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/buscadorNoticia.css">
        <link rel="stylesheet" href="css/nav.css">
        <script src="js/nav.js" defer></script>
        <script src="js/buscadorNoticias.js" defer></script>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/nav.jspf" %>
        <main class="search-container">
            <h3>Buscador de noticias</h3>
            <section class="search-form">
                <form id="newsSearchForm">
                    <div class="form-group">
                        <label for="titulo">Titular</label>
                        <input type="text" id="titulo" name="titulo">
                    </div>

                    <div class="form-group">
                        <label for="categoria">Categoría</label>
                        <select id="categoria" name="categoria">
                            <option value="">Seleccionar categoría</option>
                            <option value="The Legend of Zelda">The Legend of Zelda</option>
                            <option value="Mario">Mario</option>
                            <option value="Pokemon">Pokemon</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Periodo de fechas:</label>
                        <div class="date-range">
                            <input type="date" id="fechaInicio" name="fechaInicio">
                            <input type="date" id="fechaFin" name="fechaFin">
                        </div>
                    </div>

                    <div class="button-group">
                        <button type="reset" class="btn btn-secondary">Limpiar</button>
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </form>
            </section>

            <section class="search-results">
                <h2>Resultados <span id="resultCount">0</span></h2>
                <div id="resultsContainer" class="results-container">
                    <!-- Los resultados se cargarán aquí dinámicamente -->
                </div>
            </section>
        </main>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
