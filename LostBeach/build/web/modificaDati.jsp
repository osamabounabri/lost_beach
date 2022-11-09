
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica dati</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina di modifica dei dati personali dell'utente">
        <meta name="keywords" content="dati, modifica, modificare, informazioni, personale, personali">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>


    <body>

        <c:if test="${empty user}">

            <c:redirect url="login.jsp"/>

        </c:if>

        <c:if test="${not empty user}">


            <header
                <a href="index.jsp">
                <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                </a>
            </header>

            <nav>
                <ul class="">
                    <li class="col-"><a href="index.jsp"><b>Home</b></a></li>
                    <li class="col-"><a href="chiSiamo.jsp">About</a></li>
                    <li class="col-"><a href="contatti.jsp">Contatti</a></li>
                    <li class="col-"><a href='FatturaUtente.jsp'> Fatture</a></li>
                </ul>
            </nav>
            <h1>Modifica i tuoi dati</h1>

        </c:if>
    </body>
</html>
