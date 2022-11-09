


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ricerca Utente</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina di ricerca informazioni su un utente">
        <meta name="keywords" content="ricerca, search, informazioni, utenti">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>


    <body>

        <c:set var="page" value="messaggi" scope="request"/>

        <!-- controlli sulla sessione-->
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
                
                <!-- barra di navigazione-->
                <nav>
                    <ul class="">
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
                
                <div>
                    <h1> Ricerca utente per nome e cognome</h1>

                    <div class="wrapper">
                        <div class="search-input">
                            <!-- input di ricerca dell'utente per nome  ecognome -->
                            <form class="" action="RicercaUtente" method="post" >
                                <input type="text" class="inp" id="ricerca" name="ricerca" placeholder="cerca qui">

                                <c:if test="${empty listaUtentiRicerca}">

                                    <c:redirect url="ElencoUtentiPerRicerca"/>

                                </c:if>


                                <!--Lista dei nomi e cognomi degli utenti-->
                                <c:if test="${not empty listaUtentiRicerca}">

                                    <div class="autocom-box">
                                        <ul id="listaNomiCognomi">
                                            <c:forEach items="${listaUtentiRicerca}" var="utente">

                                                <li>${utente.getNome()} ${utente.getCognome()}</li>


                                            </c:forEach>
                                        </ul>
                                    </div>

                                </c:if>

                                <input type="submit" id="inviaRicerca" name="inviaRicerca" value="Trova" >


                            </form>
                        </div>
                    </div>
                    
                    <!--lista delle informazioni dell'utente trovato-->
                    <c:if test="${not empty utenteTrovato}">

                        <div class=" col-, utenteBox">

                            <ul class="listUtenti">
                                <li>${utenteTrovato.getNome()} ${utenteTrovato.getCognome()}</li> 
                                <li>Username: ${utenteTrovato.getUsername()} </li>
                                <li>Data di nascita: ${utenteTrovato.getDataNascita()}</li>
                                <li>Genere: ${utenteTrovato.getSesso()}</li>
                                <li>Codice fiscale: ${utenteTrovato.getCodiceFiscale()}</li>
                                <li>Telefono: ${utenteTrovato.getTelefono()}</li>
                                <li>Email: ${utenteTrovato.getEmail()}</li>
                                <li>Fattura: ${utenteTrovato.getFattura()}</li>

                            </ul>

                        </div>


                        <!--Lista delle fatture dell'utente trovatocon relative informazioni-->
                        <c:if test="${not empty listaFattureTrovata}">
                            <c:forEach  items="${listaFattureTrovata}" var="fattura">

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





                </div>
            </c:if>
        </c:if>

        <script type="text/javascript" src="js/suggerimenti.js"></script>
        <script type="text/javascript" src="js/searchBar.js"></script>
    </body>

</html>
