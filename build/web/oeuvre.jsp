<%-- 
    Document   : oeuvre
    Created on : 2 déc. 2010, 16:35:33
    Author     : arsane
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Oeuvre</title>
    </head>
    <body>
        <h1 align='center'>${titre}</h1>
        <form action="enregistrer.oeuvre?id=${oeuvreR.id_oeuvre}" method="post" name="frmModif">
            <p>
                Titre : <input type="text" name="txtTitre" value="${oeuvreR.titre}">
                <br><br>
                Prix : <input type="text" name="txtPrix" value="${oeuvreR.prix}">
                <br><br>
                Propriétaire : <SELECT name="lstProprietaires">
                    <c:forEach var="proprietaire" items="${lstProprietairesR}">
                        <option value="${proprietaire.id_proprietaire}"<c:if test="${proprietaire.id_proprietaire == oeuvreR.proprietaire.getId_proprietaire()}"> SELECTED</c:if> >${proprietaire.nom_proprietaire} ${proprietaire.prenom_proprietaire}</option>
                    </c:forEach>
                </SELECT>
                <br><br>
                <input type="submit" value="Envoi">
            </p>
        </form>
    </body>
</html>
