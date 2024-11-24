<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="css/nav.css">
    <script src="js/nav.js" defer></script>
    <script src="js/register.js" defer></script>
</head>

<body>
    <%@ include file="WEB-INF/jspf/nav.jspf" %>
    <main>
        <div class="form-container">
            <h2>¡Crea una cuenta!</h2>
            
            <div class="form-steps">
                <!-- Step 1 -->
                <form class="form-step active" id="step1">
                    <div class="input-group">
                        <label for="username" class="sr-only">Nombre de usuario</label>
                        <input type="text" id="username" placeholder="Nombre de usuario" required>
                    </div>
                    
                    <div class="input-group input-row">
                        <div>
                            <label for="nombre" class="sr-only">Nombre</label>
                            <input type="text" id="nombre" placeholder="Nombre" required>
                        </div>
                        <div>
                            <label for="genero" class="sr-only">Género</label>
                            <input type="text" id="genero" placeholder="Género" required>
                        </div>
                    </div>
                    
                    <div class="input-group">
                        <label for="email" class="sr-only">Email</label>
                        <input type="email" id="email" placeholder="Email" required>
                    </div>
                    
                    <div class="input-group input-row">
                        <div>
                            <label for="apellido-materno" class="sr-only">Apellido Materno</label>
                            <input type="text" id="apellido-materno" placeholder="Apellido Materno" required>
                        </div>
                        <div>
                            <label for="apellido-paterno" class="sr-only">Apellido Paterno</label>
                            <input type="text" id="apellido-paterno" placeholder="Apellido Paterno" required>
                        </div>
                    </div>

                    <div class="progress-dots" aria-hidden="true">
                        <span class="dot active"></span>
                        <span class="dot"></span>
                    </div>

                    <div class="buttons">
                        <button type="button" class="btn-secondary">Iniciar sesión</button>
                        <button type="button" class="btn-primary" onclick="nextStep()">Continuar</button>
                    </div>
                </form>

                <!-- Step 2 -->
                <form class="form-step" id="step2">
                    <div class="profile-image-container">
                        <div class="profile-image">
                            <img id="preview-image" src="" alt="Vista previa de la imagen de perfil">
                            <span id="image-placeholder">Imagen de perfil</span>
                        </div>
                        <div class="input-group" style="flex-grow: 1; margin-bottom: 0;">
                            <label for="profile-image" class="sr-only">Seleccionar imagen de perfil</label>
                            <input type="file" id="profile-image" accept="image/*" onchange="updateImagePreview(this)">
                        </div>
                    </div>
                    
                    <div class="input-group">
                        <label for="password" class="sr-only">Contraseña</label>
                        <input type="password" id="password" placeholder="Contraseña" required>
                    </div>
                    
                    <div class="input-group">
                        <label for="confirm-password" class="sr-only">Confirmar Contraseña</label>
                        <input type="password" id="confirm-password" placeholder="Confirmar Contraseña" required>
                    </div>

                    <div class="progress-dots" aria-hidden="true">
                        <span class="dot"></span>
                        <span class="dot active"></span>
                    </div>

                    <div class="buttons">
                        <button type="button" class="btn-secondary" onclick="previousStep()">Volver</button>
                        <button type="submit" class="btn-primary">Registrarme</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/jspf/footer.jspf" %>
    
</body>
</html>

