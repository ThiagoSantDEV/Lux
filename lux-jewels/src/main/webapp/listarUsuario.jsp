<%@ page import="br.com.lux.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Lista de Usuários</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>CPF</th>
        <th>Email</th>
        <th>Grupo</th>
        <th>Status</th>
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
        <td><%= usuario.getCpf() %></td>
        <td><%= usuario.getEmail() %></td>
        <td><%= usuario.getGrupo() %></td>
        <td><%= usuario.getStatus() ? "Ativo" : "Inativo" %></td>
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
</body>
</html>
