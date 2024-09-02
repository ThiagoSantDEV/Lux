<%--
  Created by IntelliJ IDEA.
  User: Fideles
  Date: 31/08/2024
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
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
<h2>Cadastro de Usuário</h2>
<form action="CadastroUsuarioServlet" method="post" onsubmit="return validateForm();">
    <label for="nome">Nome:</label><br>
    <input type="text" id="nome" name="nome" required><br><br>

    <label for="cpf">CPF:</label><br>
    <input type="text" id="cpf" name="cpf" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="senha">Senha:</label><br>
    <input type="password" id="senha" name="senha" required><br><br>

    <label for="confirmaSenha">Confirme a Senha:</label><br>
    <input type="password" id="confirmaSenha" name="confirmaSenha" required><br><br>

    <label for="grupo">Grupo:</label><br>
    <select id="grupo" name="grupo" required>
        <option value="admin">Admin</option>
        <option value="estoquista">Estoquista</option>
    </select><br><br>

    <input type="submit" value="Cadastrar">
</form>

<c:if test="${not empty mensagem}">
    <p style="color: red;">${mensagem}</p>
</c:if>
</body>
</html>
