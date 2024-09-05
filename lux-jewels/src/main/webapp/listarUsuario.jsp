<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <form class="d-flex" role="search" action="lista-usuario" method="get">
            <input class="form-control me-2" type="search" name="nome" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
</nav>

<c:if test="${not empty users}">
    <p>Número de usuários encontrados: ${fn:length(users)}</p>
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
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.nome}</td>
                <td>${user.email}</td>
                <td>${user.status ? 'Ativo' : 'Inativo'}</td>
                <td>${user.grupo}</td>
                <td><a href="alterarUsuario?id=${user.idUsuario}">Alterar</a></td>
                <td><a href="alterarStatusUsuario?id=${user.idUsuario}">${user.status ? 'Inabilitar' : 'Habilitar'}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty users}">
    <p>Nenhum usuário encontrado.</p>
</c:if>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap5.min.js"></script>
<script>
    $(document).ready(function() {
        $('.table').DataTable();
    });
</script>

</body>
</html>