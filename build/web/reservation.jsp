<%-- 
    Document   : reserver
    Created on : 4 déc. 2010, 23:19:03
    Author     : alain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réservation</title>
    </head>
    <body>
        <h1 align='center'>Réservation d'une oeuvre</h1>
        <form action="enregistrer.reservation" method="post" name="frmModif">
            <p>
            Titre : <input type="text" name="txtTitre" value="${oeuvreR.titre}">
            <br><br>
            Prix : <input type="text" name="txtPrix" value="${oeuvreR.prix}">
            <br><br>
            </p>
            <p>
            Date réservation : <input type="text" name="txtDate"> Format : JJ/MM/AAAA
            <br><br>
            </p>
            Adhérent : <select name="lstAdherents">
                <c:forEach var="adherent" items="${lAdhrentsR}">
                    <option value="${adherent.id_adherent}">${adherent.nom_adherent}</option>
                </c:forEach>
            </select>
            <br><br>
            <input type="hidden" name="idOeuvre" value="${oeuvreR.id_oeuvre}">
            <input type="submit" value="Envoi">
            <p>
                <c:out value="${erreur}"/> 
            </p>
        </form>
    </body>
</html>
