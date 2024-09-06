<%@ page import="br.com.lux.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Usuários</title>
    <style>
        body {
            background-color: #004d40;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
        }

        .box {
            background-color: #f0fff0;
            width: 80%;
            max-width: 1000px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
        }

        h1 {
            text-align: center;
            color: #004d40;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #004d40;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: #004d40;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f0fff0;
        }

        tr:hover {
            background-color: #e0f2f1;
        }

        .actions {
            text-align: center;
        }

        .btn {
            padding: 8px 16px;
            background-color: #004d40;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
            text-decoration: none;
        }

        .btn:hover {
            background-color: #003d33;
        }

        .top-links {
            text-align: right;
            margin-bottom: 20px;
        }

        .top-links a {
            padding: 10px 20px;
            background-color: #004d40;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s, box-shadow 0.3s;
        }

        .top-links a:hover {
            background-color: #003d33;
        }

        .erro {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="box">
    <h1>Lista de Usuários</h1>

    <div class="top-links">
        <a href="cadastrarUsuario.jsp" class="btn">Cadastrar Novo Usuário</a>
    </div>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Grupo</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("users");
            if (usuarios != null && !usuarios.isEmpty()) {
                for (Usuario usuario : usuarios) {
        %>
        <tr>
            <td><%= usuario.getIdUsuario() %></td>
            <td><%= usuario.getNome() %></td>
            <td><%= usuario.getEmail() %></td>
            <td><%= usuario.getGrupo() %></td>
            <td><%= usuario.getStatus() ? "Ativo" : "Inativo" %></td>
            <td class="actions">
                <a href="cadastrarUsuario.jsp?id=<%= usuario.getIdUsuario() %>" class="btn">Alterar</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhum usuário encontrado.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
