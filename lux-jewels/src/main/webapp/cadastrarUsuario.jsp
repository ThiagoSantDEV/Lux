<%@ page import="br.com.lux.dao.CreateUsuarioDao" %>
<%@ page import="br.com.lux.model.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getParameter("id") != null ? "Alterar Usuário" : "Cadastrar Usuário" %></title>
    <link rel="stylesheet" type="text/css" href="css/CadastrarUsuario.css">
    <script>
        function validateForm() {
            const senha1 = document.getElementById("senha").value;
            const senha2 = document.getElementById("confirmaSenha").value;
            const cpf = document.getElementById("cpf").value;

            if (senha1 !== senha2) {
                alert("As senhas não coincidem!");
                return false;
            }

            if (!validateCPF(cpf)) {
                alert("CPF inválido!");
                return false;
            }

            return true;
        }

        function validateCPF(cpf) {
            cpf = cpf.replace(/[^\d]+/g, '');
            if (cpf.length !== 11) return false;

            let sum = 0;
            let remainder;

            for (let i = 1; i <= 9; i++)
                sum = sum + parseInt(cpf.substring(i - 1, i)) * (11 - i);

            remainder = (sum * 10) % 11;

            if ((remainder === 10) || (remainder === 11)) remainder = 0;
            if (remainder !== parseInt(cpf.substring(9, 10))) return false;

            sum = 0;
            for (let i = 1; i <= 10; i++)
                sum = sum + parseInt(cpf.substring(i - 1, i)) * (12 - i);

            remainder = (sum * 10) % 11;

            if ((remainder === 10) || (remainder === 11)) remainder = 0;
            if (remainder !== parseInt(cpf.substring(10, 11))) return false;

            return true;
        }
    </script>
</head>
<body>

<%
    String idUsuario = request.getParameter("id");
    Usuario usuario = null;
    if (idUsuario != null && !idUsuario.isEmpty()) {
        CreateUsuarioDao usuarioDao = new CreateUsuarioDao();
        usuario = usuarioDao.buscarUsuarioPorId(Integer.parseInt(idUsuario));
    }
%>

<div><h2><%= idUsuario != null ? "Alterar Usuário" : "Cadastrar Usuário" %></h2></div>

<div>

<form action="CadastroUsuarioServlet" method="post" onsubmit="return validateForm();">
    <input type="hidden" name="idUsuario" value="<%= usuario != null ? usuario.getIdUsuario() : "" %>">

    <label for="nome">Nome:</label><br>
    <input type="text" id="nome" name="nome" value="<%= usuario != null ? usuario.getNome() : "" %>" required><br><br>

    <label for="cpf">CPF:</label><br>
    <input type="text" id="cpf" name="cpf" value="<%= usuario != null ? usuario.getCpf() : "" %>" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" value="<%= usuario != null ? usuario.getEmail() : "" %>" required><br><br>

    <label for="senha">Senha:</label><br>
    <input type="password" id="senha" name="senha" required><br><br>

    <label for="confirmaSenha">Confirme a Senha:</label><br>
    <input type="password" id="confirmaSenha" name="confirmaSenha" required><br><br>

    <label for="grupo">Grupo:</label><br>
    <select id="grupo" name="grupo" required>
        <option value="admin" <%= usuario != null && "admin".equals(usuario.getGrupo()) ? "selected" : "" %>>Admin</option>
        <option value="estoquista" <%= usuario != null && "estoquista".equals(usuario.getGrupo()) ? "selected" : "" %>>Estoquista</option>
    </select><br><br>

    <input type="submit" value="<%= idUsuario != null ? "Alterar" : "Cadastrar" %>">
</form>

</div>

</body>
</html>
