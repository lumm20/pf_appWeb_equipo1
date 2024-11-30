<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Crear publicacion</title>
        <link rel="icon" href="../img/logo.png" type="image/x-icon">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <link rel="icon" href="/img/logo.png" type="image/x-icon">
        <link rel="stylesheet" href="/private/cssPrivate/crearPublicacion.css">
        <link rel="stylesheet" href="/css/nav.css">
        <script src="/js/nav.js" defer></script>
        <script src="/private/jsPrivate/crearPublicacion.js" defer></script>
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/nav.jspf" %>
                <main>
                    <h3>Subir Nueva Publicación</h3>
                    <form class="upload-form" id="upload-form" action="/private/Publicacion" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="publicar">
                               <div class="form-group">
                            <label for="image-upload">Imagen:</label>
                            <input type="file" id="image-upload" name="image-post" accept="image/*" required>
                            <div class="image-preview" id="image-preview">
                                <span>Vista previa de la imagen</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="category">Categoría:</label>
                            <select id="category" name="category" required>
                                <option value="">Selecciona una categoría</option>
                                <option value="mario-bros">Mario Bros</option>
                                <option value="pokemon">Pokemon</option>
                                <option value="animal-crossing">Animal Crossing</option>
                                <option value="zelda">Zelda</option>
                                <option value="otros">Otros</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="description">Descripción:</label>
                            <textarea id="description" name="description" required></textarea>
                        </div>
                        <button type="submit" id="post-btn" class="submit-btn">Publicar</button>
                    </form>
                </main>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
