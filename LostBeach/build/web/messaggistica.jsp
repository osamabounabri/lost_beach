


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messaggi</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina di invio e ricezione messaggi con l'amministratore">
        <meta name="keywords" content="messaggi, messaggistica, invio, scambio, informazioni">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>


    <body>

        <c:set var="page" value="messaggi" scope="request"/>

        <!-- controlli sulla sessione-->
        <c:if test="${empty user}">

            <c:redirect url="login.jsp"/>

        </c:if>

        <c:if test="${not empty user}">


            <header>
                <a href="index.jsp">
                    <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                </a>
            </header>
              <!-- barra di navigazione-->
            <nav>
                <ul class="">
                    <li class="col-"><a href="index.jsp"><b>Home</b></a></li>
                    <li class="col-"><a href="chiSiamo.jsp">About</a></li>
                    <li class="col-"><a href="contatti.jsp">Contatti</a></li>
                    <li class="col-"><a href='fatturaUtente.jsp'> Fatture</a></li>
                    <li class="col-"><a href='messaggistica.jsp'> Messaggi</a></li>
                </ul>
            </nav>
            <div>
                <h1> Messaggistica</h1>

                <!-- invio messaggio -->
                <form class="col-" action="Messaggio" method="post" >

                    <textarea class="col-" id="messaggio" name="messaggio"></textarea>

                    <input type="submit" id="inviaMessaggio" name="inviaMessaggio" value="Invia" >


                </form>


            </form>

        </div>


    </c:if>

</body>

</html>
