<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Oeuvre</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="container-fluid">
                <form action="enregistrer.oeuvre?id=${oeuvreR.id_oeuvre}" method="post" name="frmModif">
                    <fieldset>
                        <legend>${titre}</legend>

                        <label>Titre :</label></br>
                        <input type="text" name="txtTitre" value="${oeuvreR.titre}" required="Veuillez compl�ter ce champ."><br><br>

                        <label>Prix :</label></br>
                        <input type="text" name="txtPrix" value="${oeuvreR.prix}" required="Veuillez compl�ter ce champ."></br></br>

                        <label>Propri�taire :</label>
                        <select name="lstProprietaires"  class="form-control">
                            <c:forEach var="proprietaire" items="${lstProprietairesR}">
                                <option value="${proprietaire.id_proprietaire}"<c:if test="${proprietaire.id_proprietaire == oeuvreR.proprietaire.getId_proprietaire()}"> SELECTED</c:if> >${proprietaire.nom_proprietaire} ${proprietaire.prenom_proprietaire}</option>
                            </c:forEach>
                        </select>

                        </br></br>
                        <input type="submit" value="Envoi"class="btn">
                    </fieldset>
                    <c:out value="${erreur}"/>  
                </form>
            </div>
        </div>
    </body>
</html>
