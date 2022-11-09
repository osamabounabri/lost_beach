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

        <title>Lost Beach-Chi siamo</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina delle informazioni di Lost Beach">
        <meta name="keywords" content="informazioni, posizione, storia, chi siamo">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">

    </head>
    <body>

        <c:set var="page" value="chiSiamo" scope="request"/>

        <!-- inserisco l'header e la barra di navigaione -->
        <header>
            <a href="index.jsp">
                <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
            </a>



        </header>

        <div> 

            <jsp:include page="header.jsp"/>

        </div>
            
        <!-- informazioni sullo stabilimento -->    
        <div class="info">
            <div>
                <h1>Chi siamo</h1>
                
                <p>Lost Beach è uno stabilimento balneare situato a Villasimius</p>
                
            </div>
            
            <div>
                
                <p>
                    Lost Beach è per tutti: grandi, bambini, coppie e famiglie<br>
                    <a href="index.jsp"> <b>Prenota</b> </a> ora!
                </p>
                
            </div>
        </div>

    </body>
</html>