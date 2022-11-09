

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fatture</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Elenco delle fatture del cliente">
        <meta name="keywords" content="fatture, fattura, scontrino, fiscale, elenco, prenotazioni, pagamenti">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>

    <body>

        <!-- controlli sulla sessione -->
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
                </ul>
            </nav>

            <h1> Elenco fatture</h1>

            <!-- controllo se l'utente desidera la fattura-->
            <c:if test="${scelta_fattura.equals('si')}"> 

                <!-- recupero le fatture -->
                <c:if test="${empty listaDiFatture}">

                    <c:redirect url="ElencoFatture"/>

                </c:if>

                <c:if test="${not empty listaDiFatture}">

                    <!-- Stampo le fatture -->
                    <c:forEach  items="${listaDiFatture}" var="fattura">
                        
                        <div class=" col-, utenteBox">


                            <ul class="listUtenti">

                                <li> fattura n°: ${fattura.getId()}</li> 

                                <li> data di acquisto: ${fattura.getData()} </li>

                                <li> posti prenotati: ${fattura.getPosti()} </li>
                                
                                <li> bagnino di turno: ${fattura.getBagnino()}</li>

                                <li> Descrizione servizio: ${fattura.getDescrizione()}</li>

                                <li> Prezzo totale: ${fattura.getPrezzo()}€</li>


                            </ul>


                        </div>

                    </c:forEach>
                </c:if>

            </c:if>

            <!-- gestisco il caso in cui l'utente non voglia le fatture-->
            <c:if test="${scelta_fattura.equals('no')}"> 

                <h4> Hai scelto di non ricevere fatture</h4>

            </c:if>


        </c:if>


    </body>

</html>


