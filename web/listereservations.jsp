<%-- 
    Document   : listereservations
    Created on : 5 déc. 2010, 17:12:15
    Author     : alain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste réservations</title>
    </head>
    <body>
        <h1 align='center'>Liste des réservations</h1>
            <p>
            <table border="1">
                <tr>
                    <td>Titre</td>
                    <td>Date</td>
                    <td>Statut</td>
                    <td>Prénom adhérent</td>
                    <td>Nom adhérent</td>
                    <td>Confirmer</td>
                </tr>
                 <c:forEach var="reservation" items="${lReservationsR}">
                    <tr>
                        <td>FOO</td>
                        <td>${reservation.date_reservation}</td>
                        <td>${reservation.statut}</td>
                        <td>${reservation.adherent.prenom_adherent}</td>
                        <td>${reservation.adherent.nom_adherent}</td>
                        <td><a href="confirmer.reservation?idOeuvre=${reservation.oeuvre.id_oeuvre}&&date=${reservation.date_reservation}">Confirmer</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a href="xxxxxxxx">Accueil</a>
            <p>

            </p>
    </body>
</html>
