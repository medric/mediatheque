<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="container-fluid">
                <form action="login.adherent" method="post" name="frmLogin">
                    <fieldset>
                        <legend>Accès application Oeuvres</legend>
                        <label>Login :</label></br>
                        <input type="text" name="txtLogin" placeholder="Saisir login..." required="Veuillez compléter ce champ."></br></br>
                        <label>Mot de passe :</label></br>
                        <input type="password" name="txtPwd" placeholder="Saisir mot de passe..." required="Veuillez compléter ce champ."></br></br>
                        <input type="submit" value="Envoi"class="btn">
                    </fieldset>
                    <c:out value="${erreur}"/>  
                </form>
            </div>
        </div>
    </body>
</html>

