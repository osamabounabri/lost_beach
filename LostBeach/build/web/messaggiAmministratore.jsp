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

        <title>Messaggi</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina di messaggistica dell'amministratore di Lost Beach">
        <meta name="keywords" content="amministratore, gestione, controllo, messaggi">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>


    <body>

        <c:set var="page" value="messaggiAmm" scope="request"/>

        <c:if test="${empty user}">

            <c:redirect url="login.jsp"/>

        </c:if>

        <c:if test="${not empty user}">

            <c:if test="${amministratore == true}">

                <header>

                    <a href="index.jsp">
                        <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                    </a>
                </header>

                <div> <!--Barra di navigazione-->
                    <nav>
                        <ul>

                            <li class="col-"><a href="nuovoSlot.jsp">Inserisci slot</a></li>
                            <li class="col-"><a href="compilazioneFatture.jsp">Compila fatture</a></li>
                            <li class="col-"><a href="messaggiAmministratore.jsp"> Messaggi</a></li>
                            <li class="col-"><a href='homeAmministratore.jsp'> Amministratore</a></li>
                            <li>  <form action="logout" method="get" class="logoutAmministratore">

                                    <input type="submit" id="logoutAmministratore"  name="logout" value="Logout" >

                                </form></li>

                        </ul>
                    </nav>
                </div>

                <div>

                    <h1>Elenco Messaggi</h1>

                    <!--controllo di avere dei messaggi-->
                    <c:if test="${empty listaMessaggi}">

                        <c:redirect url="ElencoMessaggi"/>

                    </c:if>

                    <c:if test="${not empty listaMessaggi}">

                        <!-- stampo tutti i messaggi-->
                        <c:forEach items="${listaMessaggi}" var="messaggio">

                            <div class="col-, utenteBox">

                                <ul class="listUtenti"> 
                                    <li>Username: ${messaggio.getUsername()} </li>
                                    <li>Messaggio: ${messaggio.getMessaggio()}</li>

                                </ul>
                            </div>


                        </c:forEach>

                    </c:if>

                </div>
            </c:if>

        </c:if>
    </body>
</html>
