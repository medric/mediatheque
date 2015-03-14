<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!doctype html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Accueil</title>
        <link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="static/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <a href="accueil.jsp"><h1 align='center'>Gestion des oeuvres</h1> </a>
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="creer.oeuvre">Ajouter une oeuvre</a></li>
                <li role="presentation"><a href="liste.reservation">Confirmer une réservation</a></li>
                <li role="presentation"><a href="catalogue.oeuvre">Consulter le catalogue</a></li>
                <li role="presentation"><a href="deconnecter.adherent">Se déconnecter</a></li>
            </ul>
        </div>
    </body>
</html>
