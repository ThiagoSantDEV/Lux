<%--
  Created by IntelliJ IDEA.
  User: thiag
  Date: 28/08/2024
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Lista de Usuário</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="css/ListaUsuario.css">
</head>
<body>

<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
</nav>

<table class="table">
    <thead>
    <tr class="table-primary">
        <th scope="col">Nome</th>
        <th scope="col">Email</th>
        <th scope="col">Status</th>
        <th scope="col">Grupo</th>
        <th scope="col">Alterar</th>
        <th scope="col">Hab/Des</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.nome}</td>
                <td>${usuario.email}</td>
                <td>${usuario.status ? 'Ativo' : 'Inativo'}</td>
                <td>${usuario.grupo}</td>
                <td>
                    <a href="cadastrarUsuario.jsp?id=${usuario.idUsuario}" class="btn btn-primary">Alterar</a>
                </td>
                <td>
                    <form action="AlterarStatusServlet" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${usuario.idUsuario}">
                        <button type="submit" class="btn ${usuario.status ? 'btn-danger' : 'btn-success'}">
                            ${usuario.status ? 'Desabilitar' : 'Habilitar'}
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
