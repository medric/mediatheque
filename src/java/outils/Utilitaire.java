/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alain
 */
public class Utilitaire {

    public Utilitaire() {
    }

    /**
     * Transforme une chaîne en date selon le format passé en paramètre
     * @param strDate Chaîne contenant la date
     * @param formatDate Chaîne contenant le format
     * @return Date
     * @throws Exception
     */
    public static java.util.Date StrToDate(String strDate, String formatDate) throws Exception {
        java.util.Date date_retour = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatDate);
            date_retour = format.parse(strDate);
        } catch (Exception e) {
            throw e;
        }
        return date_retour;
    }

    /**
     * Transforme une Date en chaîne
     * @param uneDate Date à transformer
     * @param formatDate Format de la date
     * @return Chaine correpondant à la date
     * @throws Exception
     */
    public static String DateToStr(java.util.Date uneDate, String formatDate) throws Exception {
        String date_retour = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatDate);
            date_retour = format.format(uneDate);
        } catch (Exception e) {
            throw e;
        }
        return date_retour;
    }
    
    /**
     * Retourne vrai si l'utilisateur est authentifié
     * @param request
     * @return 
     */
    public static boolean estConnecte(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return session.getAttribute("id") != null;
    }
    
    public static void authRedirect(HttpServletRequest request, HttpServletResponse response) {
        if(!estConnecte(request)) {
            try {
                response.sendRedirect("/");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
    }
}
