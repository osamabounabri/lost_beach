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
        <title>Login Lost Beach</title>
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina di Login e Registrazione">
        <meta name="keywords" content="login, sign-up, accedi, e-mail, password">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">

    </head>

    <body>




        <!--Box di login-->
        <div id="loginBox">

            <a href="index.jsp">
                <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
            </a>

            <h1>Login</h1>
            <form action="Login" method="post">
                <input type='text' id="user" name="user" placeholder="Username"/>
                <input type='password' id="pass" name="pass" placeholder="Password"/>
                <input type="submit" value="Accedi"/>

            </form>

            <!--link alla pagina di registrazione-->
            <p>Non sei ancora registrato? <a href="registrazione.jsp">Registrati</a></p>

        </div>



    </body>
</html>

