<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:master>
    <h1>Atualizar Compromisso</h1>
    <form action="/compromissos/atualizar" method="POST">
        <input type="hidden" name="id" value="${compromisso.id}"/>
        <input type="hidden" name="userId" value="${sessionScope.authUserId}">
        <div class="form-group">
            <label for="titulo">Título</label>
            <input name="titulo" type="text" class="form-control" required="required" size="45" value="${compromisso.titulo}"/>
        </div>

        <div class="form-group">
            <label for="tipo">Tipo</label>
            <select name="tipo" class="form-control">
                <option value="Reuniao" ${compromisso.tipo.equals("Reunião") ? "selected=\"selected\"" : ""}>Reunião</option>
                <option value="Encontro" ${compromisso.tipo.equals("Encontro") ? "selected=\"selected\"" : ""}>Encontro</option>
                <option value="Palestra" ${compromisso.tipo.equals("Palestra") ? "selected=\"selected\"" : ""}>Palestra</option>
                <option value="Conferencia" ${compromisso.tipo.equals("Conferência") ? "selected=\"selected\"" : ""}>Conferência</option>
                <option value="Outro" ${compromisso.tipo.equals("Outro") ? "selected=\"selected\"" : ""}>Outro</option>
            </select>
        </div>
        <div class="form-group">
            <label for="data">Data</label>
            <input name="data" type="text" size="16" required="required" class="form-control" placeholder="07/05/2017 02:00" value="<fmt:formatDate value="${compromisso.data}" pattern="dd/MM/yyyy HH:mm"/>"/>
        </div>
        <div class="form-group">
            <label for="local">Local</label>
            <input name="local" type="text" size="45" required="required" class="form-control" value="${compromisso.local}"/>
        </div>
        <div class="form-group">
            <label for="duracao">Duração</label>
            <input name="duracao" type="number" required="required" class="form-control" value="${compromisso.duracao}" />
        </div>
        <div class="form-group">
            <label for="observacao">Observação</label>
            <textarea name="observacao" cols="50" rows="3" required="required" class="form-control">${compromisso.observacao}</textarea>
        </div>

        <input type="submit" class="btn btn-primary" value="Atualizar">
    </form>
</t:master>