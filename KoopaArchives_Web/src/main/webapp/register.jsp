
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
    <link rel="icon" href="img/logo.png" type="image/x-icon">
    <script src="js/nav.js" defer></script>
    <script src="js/register.js" defer></script>
</head>

<body>
    <%@ include file="WEB-INF/jspf/nav.jspf" %>
    <main>
        <div class="form-container">
            <h2>¡Crea una cuenta!</h2>
            
            <div class="form-steps">
                <form action="${pageContext.request.contextPath}/Usuario" method="post" id="form-registro" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="registrar">
                    
                    <!-- Step 1 -->
                    <div class="form-step active" id="step1">
                        <div id='invalid-character-error' class="error">
                            Se ingreso un caracter invalido
                        </div>
                        <div id="input-username" class="input-group">
                            <c:if test="${not empty usernameExistente}">
                                <!-- Código que se ejecuta si la condición es verdadera -->
                                <div class="form-group">
                                    <label class="error" for="usernameExistente">${fn:escapeXml(usernameExistente)}</label>
                                </div>
                            </c:if>
                            <c:if test="${not empty success}">
                                <!-- Código que se ejecuta si la condición es verdadera -->
                                <div class="form-group">
                                    <label class="error" for="usernameExistente">${success}</label>
                                </div>
                            </c:if>
                            <label for="username" class="sr-only">Nombre de usuario</label>
                            <input type="text" id="username" name="username" placeholder="Nombre de usuario" required>
                        </div>

                        <div id="input-nombre" class="input-group input-row">
                            <div>
                                <label for="nombre" class="sr-only">Nombre</label>
                                <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>
                            </div>
                            <div>
                                <label for="genero" class="sr-only">Género</label>
                                <input type="text" id="genero" name="genero" placeholder="Género" required>
                            </div>
                        </div>

                        <div id="input-email" class="input-group">
                            <c:if test="${not empty emailExistente}">
                                <!-- Código que se ejecuta si la condición es verdadera -->
                                <div class="form-group">
                                    <label class="error" for="emailExistente">${fn:escapeXml(emailExistente)}</label>
                                </div>
                            </c:if>
                            <label for="email" class="sr-only">Email</label>
                            <input type="email" id="email" name="email" placeholder="Email" required>
                        </div>

                        <div class="input-group input-row">
                            <div id="input-apellido-paterno" >
                                <label for="apellido-paterno" class="sr-only">Apellido Paterno</label>
                                <input type="text" id="apellido-paterno" name="apellido-paterno" placeholder="Apellido Paterno" required>
                            </div>
                            <div id="input-apellido-materno" >
                                <label for="apellido-materno" class="sr-only">Apellido Materno</label>
                                <input type="text" id="apellido-materno" name="apellido-materno" placeholder="Apellido Materno" required>
                            </div>
                        </div>

                        <div id="input-apM" class="progress-dots" aria-hidden="true">
                            <span class="dot active"></span>
                            <span class="dot"></span>
                        </div>

                        <div class="buttons">
                            <button id="btn-login" type="button" class="btn-secondary">Iniciar sesión</button>
                            <button type="button" class="btn-primary" onclick="nextStep()">Continuar</button>
                        </div>
                    </div>

                    <!-- Step 2 -->
                    <div class="form-step" id="step2">
                        <div class="profile-image-container">
                            <div class="profile-image">
                                <img id="preview-image" src="" alt="Vista previa de la imagen de perfil">
                                <span id="image-placeholder">Imagen de perfil</span>
                            </div>
                            <div class="input-group" style="flex-grow: 1; margin-bottom: 0;">
                                <label for="profile-image" class="sr-only">Seleccionar imagen de perfil</label>
                                <input type="file" id="profile-image" name="img-perfil" accept="image/*" onchange="updateImagePreview(this)" required>
                            </div>
                        </div>

                        <div id="input-pass" class="input-group">
                            <label for="password" class="sr-only">Contraseña</label>
                            <input type="password" id="password" name="password" placeholder="Contraseña" required>
                        </div>

                        <div id="input-confirmPass" id='confirm-pass' class="input-group">
                            <label for="confirm-password" class="sr-only">Confirmar Contraseña</label>
                            <input type="password" id="confirm-password" placeholder="Confirmar Contraseña" required>
                        </div>
                        <div id='unmatched-pass-error' class="error">
                            Las contraseñas no coinciden
                        </div>
                        <div class="progress-dots" aria-hidden="true">
                            <span class="dot"></span>
                            <span class="dot active"></span>
                        </div>

                        <div class="buttons">
                            <button type="button" class="btn-secondary" onclick="previousStep()">Volver</button>
                            <button id="btn-registro" type="submit" class="btn-primary">Registrarme</button>
                        </div>
                    </div>
                </form>
                
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>

