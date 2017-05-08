<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:master>
    <form action="/login" method="POST">
        <c:if test="${sessionScope.wrongCredentials}">
            <div class="alert alert-danger" role="alert">
                Você informou uma credencial errada. Tente novamente.
            </div>
        </c:if>
        <c:remove var="wrongCredentials" scope="session" />
        <div class="form-group">
            <label for="username">Login</label>
            <input name="username" type="text" class="form-control" required="required" size="45"/>
        </div>
        <div class="form-group">
            <label for="password">Senha</label>
            <input name="password" type="password" class="form-control" required="required" size="45"/>
        </div>

        <input type="submit" class="btn btn-primary" value="Entrar">
    </form>
</t:master>