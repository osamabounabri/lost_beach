<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>

        <title>Home Amministratore</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina principale dell'amministratore di Lost Beach">
        <meta name="keywords" content="amministratore, gestione, controllo, fatture, slot">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>


    <body>

        <!--controlli sulla sessione-->
        <c:if test="${empty user}">

            <c:redirect url="login.jsp"/>

        </c:if>

        <c:if test="${not empty user}">

            <c:if test="${amministratore == true}">

                <!-- header e barra di navigazione -->
                <header>

                    <a href="index.jsp">
                        <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                    </a>
                </header>

                <div>
                    <nav>
                        <ul>

                            <li class="col-"><a href="nuovoSlot.jsp">Inserisci Slot</a></li>
                            <li class="col-"><a href="compilazioneFatture.jsp">Compila fatture</a></li>
                            <li class="col-"><a href="messaggiAmministratore.jsp"> Messaggi</a></li>
                            <li class="col-"><a href='homeAmministratore.jsp'> Amministratore</a></li>
                            <li class="col-"><a href='nuovoBagnino.jsp'> Inserisci Bagnino</a></li>
                            <li class="col-"><a href='ricercaUtente.jsp'> Ricerca Utente</a></li>
                            <li>  <form action="logout" method="get" class="logoutAmministratore">

                                    <input type="submit" id="logoutAmministratore"  name="logout" value="Logout" >

                                </form></li>

                        </ul>
                    </nav>
                </div>

                <div>

                    <h1>Elenco Clienti</h1>

                    <!-- menu di ordinamento degli utenti -->
                    <div  style="text-align: center" class="utenteBox">

                        <form action="ElencoUtenti" method="post">

                            <label for="ordina">Ordina per:</label>
                            <select name="ordina" id="ordina">

                                <optgroup label="Crescente">

                                    <option value="CC">Cognome</option>
                                    <option value="NC">Numero posti</option>

                                </optgroup>

                                <optgroup label="Decrescente">

                                    <option value="CD">Cognome</option>
                                    <option value="ND">Numero posti</option>

                                </optgroup>

                            </select>

                            <input type="submit" value="Ordina"/>

                        </form>

                    </div>

                    <!--Recuoero la lista degli utenti e delle prenotazioni-->
                    <c:if test="${empty listaUtenti}">

                        <c:redirect url="ElencoUtenti"/>

                    </c:if>

                    
                    <c:if test="${not empty listaUtenti}">

                        <c:if test="${not empty listaPrenotazioni}">

                            <!--visualizzo tutti gli utenti e i loro posti prenotati-->
                            <c:forEach items="${listaUtenti}" var="utente">

                                <div class="col-, utenteBox">

                                    <ul class="listUtenti">
                                        <li>${utente.getNome()} ${utente.getCognome()}</li> 
                                        <li>Username: ${utente.getUsername()} </li>
                                        <li>Data di nascita: ${utente.getDataNascita()}</li>
                                        <li>Genere: ${utente.getSesso()}</li>
                                        <li>Codice fiscale: ${utente.getCodiceFiscale()}</li>
                                        <li>Telefono: ${utente.getTelefono()}</li>
                                        <li>Email: ${utente.getEmail()}</li>
                                        <li>Fattura: ${utente.getFattura()}</li>

                                        <c:forEach items="${listaPrenotazioni}" var="prenotazione">

                                            <c:if test="${utente.getUsername().equals(prenotazione.getCliente())}">

                                                <li> Data: ${prenotazione.getData()} / Orario: ${prenotazione.getOrario()} / Posti: ${prenotazione.getPosti()} </li>

                                            </c:if>

                                        </c:forEach>

                                    </ul>
                                </div>
                            </c:forEach>

                        </c:if>
                    </c:if>

                </div>


            </c:if>
        </c:if>

    </body>

</html>
