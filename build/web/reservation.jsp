<%-- 
    Document   : reserver
    Created on : 4 déc. 2010, 23:19:03
    Author     : alain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réservation</title>
    </head>
    <body>
        <h1 align='center'>Réservation d'une oeuvre</h1>
        <form action="xxxxxxxxxxxxxxxxx" method="post" name="frmModif">
            <p>
            Titre : <input type="text" name="txtTitre" value="xxxxxxxxx">
            <br><br>
            Prix : <input type="text" name="txtPrix" value="xxxxxxxxx">
            <br><br>
            </p>
            <p>
            Date réservation : <input type="text" name="txtDate" value=""> Format : JJ/MM/AAAA
            <br><br>
            </p>
            Adhérent : <SELECT name="lstAdherents">


            </SELECT>
            <br><br>
            <input type="submit" value="Envoi">
            <p>

            </p>
        </form>
    </body>
</html>
