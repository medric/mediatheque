<%-- 
    Document   : accueil
    Created on : 3 nov. 2010, 15:03:01
    Author     : alain
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Accueil</title>
    </head>
    <body>
        <h1 align="center">Gestion des oeuvres</h1>
        <p align="center">
            <a href="xxxxxxx">Ajouter une oeuvre</a><br>
            <a href="liste.reservation">Confirmer une réservation</a><br>
            <a href="catalogue.oeuvre">Consulter le catalogue</a><br>
            <a href="adherent.deconnecter">Se déconnecter</a>
        </p>
        <p>
            <c:out value="${erreur}"/>      
        </p>
    </body>
</html>
