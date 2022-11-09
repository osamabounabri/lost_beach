
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compila fatture</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Compilazione ed emissione delle fatture ">
        <meta name="keywords" content="fattura, fatture, elenco, scontrino, amministratore, fiscale">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>


    <body>

        <!-- controlli sulla sessione -->
        <c:if test="${empty user}">

            <c:redirect url="login.jsp"/>

        </c:if>

        <c:if test="${not empty user}">

            <c:if test="${amministratore == true}">

                <!-- inserimento header e barra di navigazione -->
                <header
                    <a href="index.jsp">
                    <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                    </a>
                </header>

                <nav>
                    <ul class="">
                        <li class="col-"><a href="nuovoSlot.jsp">Inserisci slot</a></li>
                        <li class="col-"><a href="compilazioneFatture.jsp">Compila fatture</a></li>
                        <li class="col-"><a href="messaggiAmministratore.jsp"> Messaggi</a></li>
                        <li class="col-"><a href='homeAmministratore.jsp'> Amministratore</a></li>
                    </ul>
                </nav>

                <h1>Compilazione fatture</h1>

                <!-- menu di selezione della prenotazione da fatturare-->
                <div class="col-, slot" id="prenotaDiv">
                    <form action="Processa" method="post">

                        <label for="processaId">Inserisci il numero della prenotazione che vuoi processare</label>
                        <input type="number" class='prenotaPosti' id="compila" min="1" name="processaId" placeholder="0" />

                        <input type="submit" id="prenota" name="processa" value="Processa" > 
                    </form>
                </div>

                <!-- recupero la lista delle prenotazioni-->
                <c:if test="${empty listaPrenotazioni}">

                    <c:redirect url="ElencoPrenotazioni"/>

                </c:if>

                <c:if test="${not empty listaPrenotazioni}">

                    <!-- visualizzo tutte le prenotazioni-->
                    <c:forEach  items="${listaPrenotazioni}" var="prenotazione">
                        <div class="col-, utenteBox">

                            <ul class="listUtenti">
                                
                            <li>prenotazione: ${prenotazione.getId()} </li>
                            
                            <li>username cliente: ${prenotazione.getCliente()}</li>
                            
                            <li>orario: ${prenotazione.getOrario()}</li>
                            
                            <li>data: ${prenotazione.getData()}</li>
                            
                            <li>posti prenotati: ${prenotazione.getPosti()}</li>

                            </ul>
                            
                        </div>

                    </c:forEach>
                </c:if>

            </c:if>
        </c:if>
    </body>
</html>
