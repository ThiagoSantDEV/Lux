<%--
  Created by IntelliJ IDEA.
  User: thiag
  Date: 28/08/2024
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Usuário</title>
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

<table class="table-primary">
    <thead>
    <tr>
        <th scope="col">Nome</th>
        <th scope="col">Email</th>
        <th scope="col">Status</th>
        <th scope="col">Alterar</th>
        <th scope="col">Hab/Des</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Thiago</td>
        <td>thi@gmail.com</td>
        <td>Ativo</td>
        <td>Alterar</td>
        <td>Inabilitar</td>
    </tr>
    </tbody>
</table>
</body>
</html>
