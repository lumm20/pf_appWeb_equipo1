<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Perfil</title>
        <link rel="icon" href="img/logo.png" type="image/x-icon">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="cssPrivate/perfil.css">
        <link rel="stylesheet" href="../css/nav.css">
    </head>
    <body>
        <%@ include file="../WEB-INF/jspf/nav.jspf" %>
        <main class="profile-container">
            <div class="profile-content">
                <div class="profile-form">
                    <h3>Tu perfil</h3>
                    <form>
                        <div class="form-group">
                            <label for="usuario">Usuario</label>
                            <input type="text" id="usuario" name="usuario" value="${sessionScope.usuario.username}" required>
                        </div>

                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" id="nombre" name="nombre" value="${sessionScope.usuario.nombre}" required>
                        </div>

                        <div class="form-group">
                            <label for="apellido">Apellido</label>
                            <input type="text" id="apellido" name="apellido" value="${sessionScope.usuario.apellidoPaterno}"required>
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" value="${sessionScope.usuario.email}"required>
                        </div>

                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" id="password" name="password" required>
                        </div>

                        <div class="button-group">
                            <button type="button" class="btn btn-secondary">Editar perfil</button>
                            <button type="submit" class="btn btn-primary">Guardar cambios</button>
                        </div>
                    </form>
                </div>

                <div class="profile-avatar">
                    <div class="avatar-circle">
                        <img id="avatar-preview" src="../img/noti_ejemplo.png" alt="Avatar del usuario">
                    </div>
                    <label for="avatar-upload" class="btn btn-purple">Elegir icon</label>
                    <input type="file" id="avatar-upload" name="avatar-upload" accept="image/*" style="display: none;">
                </div>
            </div>
        </main>
        <%@ include file="../WEB-INF/jspf/footer.jspf" %>
        <script src="../js/nav.js" defer></script>
    </body>
</html>
