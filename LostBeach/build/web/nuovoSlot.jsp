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
        <title>Inserimento Slot Prenotazione</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina amministratore di inserimento di nuovi slot prenotazione">
        <meta name="keywords" content="slot, prenotazione, orario">
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

                <!--Recupera la lista dei bagnini-->
                <c:if test="${empty listaBagnini}">

                    <c:redirect url="ElencoBagnini"/>

                </c:if>

                <!--box di inserimento dei dati di un nuovo slot-->
                <c:if test="${not empty listaBagnini}">

                    <div id="newSlotBox">

                        <a href="index.jsp">
                            <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                        </a>

                        <h1>Inserimento nuovo slot di prenotazione</h1>
                        <form action="InserisciSlot" method="post">
                            <label for="dataSlot" >Inserisci la data</label>
                            <input type='date' id="dataSlot" name="dataSlot"/>
                            <select id='orario' name="orario">
                                <option value="Mattina">Mattina (8:00-14:00)</option>
                                <option value="Pomeriggio">Pomeriggio (14:00-20:00)</option>
                            </select>
                            <label for="posti">Numero di posti</label>
                            <input type="number" id="posti" min="1" name="posti" />
                            <label for="selBagnino">Bagnino</label>
                            <!--seleziona un bagnino dalla lista dei bagnini-->
                            <select id='selBagnino' name="selBagnino">

                                <c:forEach items="${listaBagnini}" var="bagnino">

                                    <option value="${bagnino.getNome()} ${bagnino.getCognome()}"> ${bagnino.getNome()} ${bagnino.getCognome()} </option>

                                </c:forEach>

                            </select>

                            <input type="submit" value="Pubblica"/>

                        </form>

                    </div>
                </c:if>
            </c:if>
        </c:if>

    </body>
</html>
