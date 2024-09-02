<%--
  Created by IntelliJ IDEA.
  User: Fideles
  Date: 31/08/2024
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.com.lux.dao.CreateUsuarioDao" %>
<%@ page import="br.com.lux.model.Usuario" %>

<%
    // Captura o ID do usuário da URL, se disponível
    String id = request.getParameter("id");
    Usuario usuario = null;

    if (id != null && !id.isEmpty()) {
        CreateUsuarioDao userDao = new CreateUsuarioDao();
        usuario = userDao.buscarUsuarioPorId(Integer.parseInt(id));
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${usuario == null ? "Cadastro de Usuário" : "Alteração de Usuário"}</title>
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
                sum = sum + parseInt(cpf.substring(i-1, i)) * (11 - i);

            remainder = (sum * 10) % 11;

            if ((remainder === 10) || (remainder === 11)) remainder = 0;
            if (remainder !== parseInt(cpf.substring(9, 10))) return false;

            sum = 0;
            for (let i = 1; i <= 10; i++)
                sum = sum + parseInt(cpf.substring(i-1, i)) * (12 - i);

            remainder = (sum * 10) % 11;

            if ((remainder === 10) || (remainder === 11)) remainder = 0;
            if (remainder !== parseInt(cpf.substring(10, 11))) return false;

            return true;
        }
    </script>

    <link rel="stylesheet" type="text/css" href="css/CadastrarUsuario.css">
</head>
<body>
<h2>${usuario == null ? "Cadastro de Usuário" : "Alteração de Usuário"}</h2>
<form action="CadastroUsuarioServlet" method="post" onsubmit="return validateForm();">

    <c:if test="${usuario != null}">
        <input type="hidden" name="id" value="${usuario.id}">
    </c:if>

    <label for="nome">Nome:</label><br>
    <input type="text" id="nome" name="nome" value="${usuario != null ? usuario.getNome() : ''}" required><br><br>

    <label for="cpf">CPF:</label><br>
    <input type="text" id="cpf" name="cpf" value="${usuario != null ? usuario.getCpf() : ''}" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" value="${usuario != null ? usuario.getEmail() : ''}" ${usuario != null ? 'readonly' : ''} required><br><br>

    <label for="senha">Senha:</label><br>
    <input type="password" id="senha" name="senha" ${usuario == null ? "required" : ""}><br><br>

    <label for="confirmaSenha">Confirme a Senha:</label><br>
    <input type="password" id="confirmaSenha" name="confirmaSenha" ${usuario == null ? "required" : ""}><br><br>

    <label for="grupo">Grupo:</label><br>
    <select id="grupo" name="grupo" required>
        <option value="admin" ${usuario != null && "admin".equals(usuario.getGrupo()) ? "selected" : ""}>Admin</option>
        <option value="estoquista" ${usuario != null && "estoquista".equals(usuario.getGrupo()) ? "selected" : ""}>Estoquista</option>
    </select><br><br>

    <input type="submit" value="${usuario == null ? "Cadastrar" : "Salvar Alterações"}">
</form>

<c:if test="${not empty mensagem}">
    <p style="color: red;">${mensagem}</p>
</c:if>
</body>
</html>
