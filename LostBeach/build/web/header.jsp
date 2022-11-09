

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- barra di navigazione nel caso non sia avviata una sessione -->
<c:if test="${page == 'index' || page =='chiSiamo' || page =='contatti'}">
    <c:if test="${empty user}">


        <nav>
            <ul class="">
                <li class="col-"><c:if test="${page == 'index'}"><div class="active"></c:if><a href="index.jsp">Home</a><c:if test="${page == 'index'}"></div></c:if></li>
                <li class="col-"><c:if test="${page == 'chiSiamo'}"><div class="active"></c:if><a href="chiSiamo.jsp">About</a><c:if test="${page == 'chiSiamo'}"></div></c:if></li>
                <li class="col-"><c:if test="${page == 'contatti'}"><div class="active"></c:if><a href="contatti.jsp">Contatti</a><c:if test="${page == 'contatti'}"></div></c:if></li>
                    <li class="col-"><a <a href="login.jsp">Login</a></li>
                </ul>
            </nav>

    </c:if>

    <!-- barra di navigazione nel caso la sessione sia di un utente semplice -->
    <c:if test="${not empty user}">
        <c:if test="${amministratore != true}">

            <nav>
                <ul class="">
                    <li class="col-"><c:if test="${page == 'index'}"><div class="active"></c:if><a href="index.jsp">Home</a><c:if test="${page == 'index'}"></div></c:if></li>
                    <li class="col-"><c:if test="${page == 'chiSiamo'}"><div class="active"></c:if><a href="chiSiamo.jsp">About</a><c:if test="${page == 'chiSiamo'}"></div></c:if></li>
                    <li class="col-"><c:if test="${page == 'contatti'}"><div class="active"></c:if><a href="contatti.jsp">Contatti</a><c:if test="${page == 'contatti'}"></div></c:if></li>
                    <li class="col-"><c:if test="${page == 'messaggi'}"><div class="active"></c:if><a href="messaggistica.jsp">Messaggi</a><c:if test="${page == 'messaggi'}"></div></c:if></li>
                        <li class="col-"><a href="areaPersonale.jsp">Area Personale</a></li>
                    </ul>
                </nav>
        </c:if>
    </c:if>

    <!-- barra di navigazione nel caso la sessione sia dell'amministratore -->
    <c:if test="${not empty user}">
        <c:if test="${amministratore == true}">

            <nav>
                <ul class="">
                    <li class="col-"><c:if test="${page == 'index'}"><div class="active"></c:if><a href="index.jsp">Home</a><c:if test="${page == 'index'}"></div></c:if></li>
                    <li class="col-"><c:if test="${page == 'chiSiamo'}"><div class="active"></c:if><a href="chiSiamo.jsp">About</a><c:if test="${page == 'chiSiamo'}"></div></c:if></li>
                    <li class="col-"><c:if test="${page == 'contatti'}"><div class="active"></c:if><a href="contatti.jsp">Contatti</a><c:if test="${page == 'contatti'}"></div></c:if></li>
                    <li class="col-"><c:if test="${page == 'messaggiAmm'}"><div class="active"></c:if><a href="messaggiAmministratore.jsp">Messaggi</a><c:if test="${page == 'messaggiAmm'}"></div></c:if></li>
                        <li class="col-"><a href="homeAmministratore.jsp">Amministratore</a></li>
                    </ul>
                </nav>
        </c:if>
    </c:if>
</c:if>