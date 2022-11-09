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

        <title>Lost Beach-Contatti</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina dei contatti di Lost Beach">
        <meta name="keywords" content="contatti, informazioni, e-mail, telefono, indirizzo, recapiti">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">

    </head>
    <body>

        <c:set var="page" value="contatti" scope="request"/>

        <!-- inserimento header e barra di navigazione -->
        <header>
            <a href="index.jsp">
                <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
            </a>

            <jsp:include page="header.jsp"/>

        </header>

        <!-- informazioni sui contatti dello stabilimento -->
        <div class="info">
            <div>
                <h1>Contatti</h1>
                <p>Se avete bisogno di contattarci potete farlo tramite i seguenti indirizzi:</p>
            </div>
            <div>
                <p>
                    <b>Richieste varie:</b> lostbeach@gmail.com
                    <br>
                    <b>Prenotazioni:</b> 3334445566
                    <br>
                    <b>Posta cartacea:</b> LostBeach S.p.A.
                    <br>
                    <b>indirizzo</b>: Via Spiaggia 4
                    <br>
                    09049 - Villasimius (SU)
                </p>
            </div>
        </div>

    </body>
</html>
