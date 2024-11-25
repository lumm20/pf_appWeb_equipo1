<%-- 
    Document   : login
    Created on : 23 nov 2024, 21:26:20
    Author     : karim
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="/img/logo.png" type="image/x-icon">
        <link rel="stylesheet" href="css/nav.css">
        <link rel="stylesheet" href="css/login.css">
        <script src="js/nav.js" defer></script>
        <title>Noticias</title>
    </head>
    <body>
        <%@ include file="WEB-INF/jspf/nav.jspf" %>
        <main>
            <div class="login-form">
                <h3>Iniciar sesión</h3>
                <form action="Usuario" method="post">
                    <input type="hidden" name="action" value="iniciarSesion">
                    <c:if test="${not empty error}">
                        <!-- Código que se ejecuta si la condición es verdadera -->
                        <div class="form-group">
                            <label class="error" for="error">Error: ${error}</label>
                        </div>
                    </c:if>
                    
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <div class="button-group">
                        <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
                    </div>
                </form>
                <div class="register-prompt">
                    <p>¿Nuevo por aquí? <a href="register.jsp">te invitamos a registrarte</a></p>
                </div>
            </div>
        </main>


        <%@ include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
