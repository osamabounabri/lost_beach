

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Operazione avvenuta con Successo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Osama Bounabri" >
        <meta name="description" content="Pagina di informazione del successo dell'operazione effettuata ">
        <meta name="keywords" content="operazione, successo, info, avvenuta">
        <link rel="stylesheet" type="text/css"  href="lostBeachStyle.css" media="screen">
    </head>
    <body>
        
        <header>

                <a href="index.jsp">
                    <img title="Logo" alt="Logo di LostBeach" src="img/lostBeach.png" width="450" height="130">
                </a>

            </header>
        
        <!-- messaggio di successo dell'operazione effettuata -->
        <h1>${mexSuccesso}</h1> 
        
        
        <a href="index.jsp">Torna alla home</a>
        
    </body>
</html>
