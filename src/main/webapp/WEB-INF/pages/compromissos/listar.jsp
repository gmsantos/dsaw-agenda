<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="today" class="java.util.Date" />
<t:master>
    <h1>Listagem de Compromissos</h1>
    <table class="table table-hover">
        <tr>
            <th>Título</th>
            <th>Tipo</th>
            <th>Data</th>
            <th>Local</th>
            <th>Duração</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="compromisso" items="${compromissos}">
            <tr <c:if test="${compromisso.isToday()}">class="warning"</c:if> >
                <td>${compromisso.titulo}</td>
                <td>${compromisso.tipo}</td>
                <td><fmt:formatDate value="${compromisso.data}" pattern="dd/MM/yyyy HH:mm"/></td>
                <td>${compromisso.local}</td>
                <td>${compromisso.duracao}</td>
                <td>
                    <a href="/compromissos/atualizar?id=${compromisso.id}">Editar</a> -
                    <a href="/compromissos/excluir?id=${compromisso.id}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a class="btn btn-primary" href="/compromissos/inserir">Novo Compromisso</a></p>
</t:master>