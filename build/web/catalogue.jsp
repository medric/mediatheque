<%-- 
    Document   : 
    Created on : 24 nov. 2010, 10:04:11
    Author     : arsane
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil</title>
    </head>
    <body>
        <table border="1">

</table>
        <h1 align='center'>Catalogue des oeuvres</h1>
            <p>
            <table border="1">
                <tr>
                    <td>Titre</td>
                    <td>Prix</td>
                    <td>Prénom propriétaire</td>
                    <td>Nom propriétaire</td>
                    <td>Réserver</td>
                    <td>Modifier</td>
                </tr>                
                <c:forEach var="oeuvre" items="${lOeuvresR}">
                    <tr>
                        <td>${oeuvre.titre}</td>
                        <td>${oeuvre.prix}</td>
                        <td>${oeuvre.proprietaire.prenom_proprietaire }</td>
                        <td>${oeuvre.proprietaire.nom_proprietaire }</td>
                        <td><a href="ajouter.reservation?id=${oeuvre.id_oeuvre}">Réserver</a></td>
                        <td><a href="modifier.oeuvre?id=${oeuvre.id_oeuvre}">Modifier</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a href="">Accueil</a>
            <p>

            </p>
    </body>
</html>
