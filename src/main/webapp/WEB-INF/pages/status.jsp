<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="${!status.isEmpty() ? "alert alert-" += status : "alert"}" role="alert">
            ${output}
        </div>
    </jsp:body>
</t:master>