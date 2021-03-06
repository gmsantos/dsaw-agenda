<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" href="/favicon.ico">

    <title>DSAW - Agenda de compromissos</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">Agenda</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/index.jsp">Home</a></li>
            <li><a href="/compromissos/listar">Compromissos</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <c:if test="${cookie.containsKey('lastVisit')}">
              <li><a href="#">Último acesso: ${cookie.lastVisit.value}</a></li>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.authUserLogged}">
                  <li><a href="#">Bem vindo, ${sessionScope.authUserName}</a></li>
                  <li><a href="/logout">Logoff</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/login">Login</a></li>
                </c:otherwise>
            </c:choose>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
      <jsp:doBody/>
    </div><!-- /.container -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
  </body>
</html>
