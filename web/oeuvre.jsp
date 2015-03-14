<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Oeuvre</title>
        <link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="static/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="container-fluid">
                <form action="enregistrer.oeuvre?id=${oeuvreR.id_oeuvre}" method="post" name="frmModif">
                    <fieldset>
                        <legend>${titre}</legend>
                        <input type="text" name="txtTitre" value="${oeuvreR.titre}" required="Veuillez compléter ce champ."><br><br>

                        <label>Prix :</label></br>
            
                        <input type="text" name="txtPrix" value="${oeuvreR.prix}" required="Veuillez compléter ce champ."></br></br>

                        <label>Propriétaire :</label>
                        <select name="lstProprietaires"  class="form-control">
                            <c:forEach var="proprietaire" items="${lstProprietairesR}">
                                <option value="${proprietaire.id_proprietaire}"<c:if test="${proprietaire.id_proprietaire == oeuvreR.proprietaire.getId_proprietaire()}"> SELECTED</c:if> >${proprietaire.nom_proprietaire} ${proprietaire.prenom_proprietaire}</option>
                            </c:forEach>
                        </select>

                        </br></br>
                        <input type="submit" value="Confirmer" class="btn">
                    </fieldset>
                    <c:if test="${erreur != null}">
                        <div class="alert alert-danger" role="alert">
                             <c:out value="${erreur}"/>  
                        </div>
                    </c:if>
                </form>
               <a href="accueil.jsp">Accueil</a>
            </div>
        </div>
    </body>
</html>
