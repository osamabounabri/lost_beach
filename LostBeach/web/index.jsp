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
        <title>Lost Beach</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina principale dello tabilimento balneare Lost Beach a Villasimius">
        <meta name="keywords" content="beach, spiaggia, vacanza, mare, piscina">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>
    <body>

        <c:set var="page" value="index" scope="request"/>

        <!-- creazione header con logo-->
        <div>
            <header >

                <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" >
                </a>

                <!-- inserisco la barra di navigazione-->
                <jsp:include page="header.jsp"/>

            </header>
        </div>



        <h1>Promozione 30%, prenota subito!</h1>

        <!-- menu di selezione dello slot ed i posti da prenotare-->
        <div class="col-, slot" id="prenotaDiv">
            <form action="prenotazione" method="post">

                <label for="prenotaSlot">Quale slot vuoi prenotare?</label>
                <input type="number" class="prenotaPosti" id="prenotaSlot" min="1" name="prenotaSlot" placeholder="0" />
                <label for="prenotaPosti">Inserisci il numero di posti che vuoi prenotare</label>
                <input type="number" class="prenotaPosti" id="prenotaPosti" min="1" name="prenotaPosti" placeholder="0" />
                <input type="submit" id="prenota" name="prenota" value="Prenota" > 
            </form>
        </div>

        <!-- controlli sulla sessione-->
        <c:if test="${empty listaSlot}">

            <c:redirect url="ElencoSlotHome"/>

        </c:if>

        <c:if test="${not empty listaSlot}">

            <!-- stampa tutti gli slot-->
            <c:forEach  items="${listaSlot}" var="slot">
                <div class="col-, utenteBox">

                    <ul class="listUtenti">
                        
                        <li>slot: ${slot.getId()}</li>
                    
                    <li>data: ${slot.getData()}</li>
                    
                    <li>orario: ${slot.getOrario()}</li>
                    
                    <li>Numero di posti disponibili: ${slot.getPosti()}</li>
                    
                    <li>Bagnino di turno: ${slot.getBagnino()}</li>
                  
                    </ul>  
                    
                </div>

            </c:forEach>
        </c:if>

        <script type="text/javascript" src="js/lostbeachJS.js"></script>

    </body>

</html>
