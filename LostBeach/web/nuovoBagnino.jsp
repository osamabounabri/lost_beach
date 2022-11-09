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
        <title>Inserimento Bagnino</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina amministratore di inserimento di nuovi bagnini">
        <meta name="keywords" content="inserimento, bagnino, turno, dati">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">

    </head>

    <body>

        <!-- controlli sulla sessione-->
        <c:if test="${empty user}">

            <c:redirect url="login.jsp"/>

        </c:if>


        <c:if test="${not empty user}">

            <c:if test="${amministratore == true}">

                <div id="newSlotBox">

                    <a href="index.jsp">
                        <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                    </a>

                    <h1>Inserimento nuovo bagnino</h1>
                    
                    <!-- Inserimento bagnino-->
                    <form action="InserisciBagnino" method="post">
                        <input type='text' id="nomeBagnino" name="nomeBagnino" placeholder="Nome"/>
                        <input type='text' id="cognomeBagnino" name="cognomeBagnino" placeholder="Cognome"/>
                        <input type='email' id="e-mailBagnino" name="e-mailBagnino" placeholder="E-mail"/>
                        <input type='text' id="numBagnino" name="numBagnino" placeholder="Telefono"/>
                        <label for="attestati">Attestati</label>
                        <textarea style="width: 100%" class="col-" id="attestati" name="attestati"></textarea>
                        <input type="submit" value="Inserisci"/>

                    </form>

                </div>

            </c:if>
        </c:if>

    </body>
</html>
