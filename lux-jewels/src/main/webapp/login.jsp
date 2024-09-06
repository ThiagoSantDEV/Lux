<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>

    <form action="/login" method="post">

        <div class="box">
            <% String message = (String) request.getSession().getAttribute("message"); %>
            <% if (message != null) { %>
                <div class="erro"><%= message %></div>
                <% request.getSession().removeAttribute("message"); %>
            <% } %>

            <div class="credenciais">
                <div class="img-credenciais">
                    <img src="/img/perfil.png" alt="">
                </div>
                <input type="text" id="email" name="email" placeholder="e-mail">
            </div>

            <div class="credenciais">
                <div class="img-credenciais">
                    <img src="/img/chave.png" alt="">
                </div>
                <input type="password" id="senha" name="senha" placeholder="senha">
            </div>

            <button type="submit">Login</button>

           <%-- <div>Ainda não tem cadastro? <a href="cadastrarUsuario.jsp" class="cadastro">Crie sua conta</a></div> --%>

        </div>

    </form>

</body>
</html>