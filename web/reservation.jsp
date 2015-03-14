<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réservation</title>
        <link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="static/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="container-fluid">
                <form action="enregistrer.reservation" method="post" name="frmModif">
                    <fieldset>
                        <legend>${titre}</legend>

                        <label>Titre :</label></br>
                        <input type="text" name="txtTitre" value="${oeuvreR.titre}" required="Veuillez compléter ce champ."><br><br>

                        <label>Prix :</label></br>
                        <input type="text" name="txtPrix" value="${oeuvreR.prix}" required="Veuillez compléter ce champ."></br></br>

                        <label>Date réservation :</label></br>
                        <input type="text" name="txtDate" placeholder="JJ/MM/AAAA"></br></br>

                        <label>Adhérent :</label>
                        <select name="lstAdherents"  class="form-control">
                            <c:forEach var="adherent" items="${lAdhrentsR}">
                                <option value="${adherent.id_adherent}">${adherent.nom_adherent}</option>
                            </c:forEach>
                        </select>

                        </br></br>
                        <input type="hidden" name="idOeuvre" value="${oeuvreR.id_oeuvre}">
                        <input type="submit" value="Réserver" class="btn">
                    </fieldset>
                    <c:if test="${erreur != null}">
                        <div class="alert alert-danger" role="alert">
                             <c:out value="${erreur}"/>  
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </form>
</body>
</html>
