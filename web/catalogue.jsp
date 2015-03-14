<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container"></div>
        <a href="accueil.jsp"><h1 align='center'>${titre}</h1> </a>
        <div class="row"> 
            <div class="col-xs-12 col-sm-10 col-md-6"> 
                <table class="table table-bordered table-striped table-hover"> 
                    <thead> 
                        <tr> 
                            <td>Titre</td>
                            <td>Prix</td>
                            <td>Prénom propriétaire</td>
                            <td>Nom propriétaire</td>
                            <td>Réserver</td>
                            <td>Modifier</td>
                        </tr>
                    </thead>
                    <tbody>
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
                    </tbody>
            </div>
        </div>
    </div>
</body>
</html>
