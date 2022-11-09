<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>

        <title>Registrazione-Lost Beach</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina di Registrazione">
        <meta name="keywords" content="registrazione, sign-up, e-mail, password, dati personali">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">

    </head>



    <body>

        <!--box di inserimento di tutti i dati per la registrazione di un nuovo utente-->
        <div id="registrazioneBox">
            
            <!--logo del sito--> 
            <a href="index.jsp">
                <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
            </a>

            <h1>Registrazione</h1>
            <form action="Registrazione" method="post">
                <input type='text' id="nome" name="nome" placeholder="Nome"/>
                <input type='text' id="cognome" name="cognome" placeholder="Cognome"/>
                <div>
                    <label class="dataLabel" for="dataNascita" >Data di nascita</label>
                    <input type='date' id="dataNascita" name="dataNascita"/>
                </div>
                <input type='text' id="cf" name="cf" placeholder="Codice Fiscale"/>
                <label class="genereLabel" for="sesso">Sesso</label>
                <select id='sesso' name="sesso">
                    <option value="Maschio">Maschio</option>
                    <option value="Femmina">Femmina</option>
                    <option value="Non binary">Non binary</option>
                    <option value="Altro">Altro</option>
                </select>
                <input type='email' id="e-mail" name="e-mail" placeholder="E-mail"/>
                <input type='text' id="num" name="num" placeholder="Telefono"/>
                <input type='text' id="regUsername" name="regUsername" placeholder="Username"/>
                <input type="password" id="regPassword" name="regPassword" placeholder="password">
                <input type="password" id="confPassword" name="confPassword" placeholder="conferma password">


                <p id="fatt" class="fattLabel">Desidera la fattura?</p>
                <select id='fatturaSiNo' name="fatturaSiNo">
                    <option value="no">no</option>
                    <option value="si">si</option>
                </select>
                <br>
                <input type="submit" value="Invia"/>
            </form>

        </div>
        <script type="text/javascript" src="js/lostbeachJS.js"></script>
    </body>
</html>
