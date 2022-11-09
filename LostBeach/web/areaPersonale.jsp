

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Area Personale</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Area personale del cliente">
        <meta name="keywords" content="area personale, dati, informazioni personali">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>


    <body>
        
        <!-- controlli sulla sessione-->
        <c:if test="${empty user}">

            <c:redirect url="login.jsp"/>

        </c:if>

        <c:if test="${not empty user}">

            <!-- header e barra di navigazione -->
            <header
                <a href="index.jsp">
                <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                </a>
            </header>

            <nav>
                <ul class="">
                    <li class="col-"><a href="index.jsp"><b>Home</b></a></li>
                    <li class="col-"><a href="chiSiamo.jsp">About</a></li>
                    <li class="col-"><a href="contatti.jsp">Contatti</a></li>
                    <li class="col-"><a href='fatturaUtente.jsp'> Fatture</a></li>
                    <li class="col-"><a href='messaggistica.jsp'> Messaggi</a></li>
                </ul>
            </nav>
            
            <!-- stampo i dati dell'utente in sessione -->
            <div id="areaPersonale">
                <h1> Account</h1>
                <p>Ultimo Accesso: ${lastLogin}</p>



                <p>Username: ${user}</p>
                <p>Nome: ${nome}</p>
                <p>Cognome: ${cognome}</p>
                <p>Data di nascita: ${dataNascita}</p>
                <p>Genere: ${sesso}</p>
                <p>Codice Fiscale: ${cf}</p>
                <p>Telefono: ${telefono}</p>
                <p>Fattura: ${scelta_fattura}</p>
                <p>Email: ${email}</p>
                <p>Password: ${pass}</p>

                <!-- tasto di modifica dei dati -->
                <a href="modificaDati.jsp">
                    <button id="modificaDati" name="modificaDati">Modifica</button>
                </a>
                
                <!-- tasto di logout -->
                <form action="logout" method="get">

                    <input type="submit" id="logout" name="logout" value="Logout" >

                </form>

            </div>


        </c:if>

    </body>

</html>
