<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste réservations</title>
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
                            <td>Date</td>
                            <td>Statut</td>
                            <td>Prénom adhérent</td>
                            <td>Nom adhérent</td>
                            <td>Confirmer</td>
                            <td>Supprimer</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="reservation" items="${lReservationsR}">
                            <tr>
                                <td>${reservation.oeuvre.titre}</td>
                                <td>${reservation.formatDateReservation('dd/MM/yyyy')}</td>
                                <td>${reservation.statut}</td>
                                <td>${reservation.adherent.prenom_adherent}</td>
                                <td>${reservation.adherent.nom_adherent}</td>
                                <td><a href="confirmer.reservation?idOeuvre=${reservation.oeuvre.id_oeuvre}&date=${reservation.date_reservation}">Confirmer</a></td>
                                <td><a href="supprimer.reservation?idOeuvre=${reservation.oeuvre.id_oeuvre}&date=${reservation.date_reservation}">Supprimer</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
            </div>
        </div>
    </div>
</body>
</html>
