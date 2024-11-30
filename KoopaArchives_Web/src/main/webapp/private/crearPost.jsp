<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Crear Post</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/crearPost.css">
        <link rel="stylesheet" href="../css/nav.css">
    </head>
    <body>
        <%@ include file="../WEB-INF/jspf/nav.jspf" %>
        <main>
            <h1 class="titulo_crear">Crear Post</h1>
            <form id="createPostForm">
                <div class="form-group">
                    <label for="titulo">Título:</label>
                    <input type="text" id="titulo" name="titulo" required>
                </div>

                <div class="form-group">
                    <label for="categoria">Categoría:</label>
                    <select id="categoria" name="categoria" required>
                        <option value="">Seleccionar categoría</option>
                        <option value="The Legend of Zelda">The Legend of Zelda</option>
                        <option value="Mario">Mario</option>
                        <option value="Pokemon">Pokemon</option>
                    </select>
                </div>

                <div class="form-group switch-container">
                    <label for="destacado">Post destacado</label>
                    <label class="switch">
                        <input type="checkbox" id="destacado" name="destacado">
                        <span class="slider"></span>
                    </label>
                </div>

                <div class="form-group">
                    <label for="imagenPrincipal">Imagen principal</label>
                    <div class="file-input-container">
                        <div class="file-input-button">Seleccionar imagen</div>
                        <input type="file" id="imagenPrincipal" name="imagenPrincipal" accept="image/*" onchange="previewImage(this, 'imagenPrincipalPreview')">
                    </div>
                    <div id="imagenPrincipalPreview" class="image-preview"></div>
                </div>

                <div class="form-group">
                    <label for="contenido">Contenido:</label>
                    <textarea id="contenido" name="contenido" required></textarea>
                </div>

                <div id="subtemas"></div>

                <button type="button" class="add-subtema" onclick="agregarSubtema()">
                    <span>+</span> Añadir nuevo subtema
                </button>

                <button type="submit" class="submit-button">Publicar post</button>
            </form>
        </main>
        <%@ include file="../WEB-INF/jspf/footer.jspf" %>
        <script src="../js/nav.js" defer></script>
        <script src="../js/crearPost.js" defer></script>
    </body>
</html>
