/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeles.Adherent;
import modeles.Oeuvre;
import modeles.Reservation;
import outils.Utilitaire;

/**
 *
 * @author Epulapp
 */
@WebServlet(name = "reservationServlet", urlPatterns = {"/reservationServlet"})
public class reservationServlet extends HttpServlet {

    private String erreur;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Si l'utilisateur n'est pas authentifié, il est redirigé sur l'index
        if(!Utilitaire.estConnecte(request)) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        // Action demandée
        String demande;
        // Page à injecter
        String pageReponse = "";
        
        HttpSession session = request.getSession(true);
       
        try {
            demande = Utilitaire.getDemande(request);
            
            if (demande.equalsIgnoreCase("liste.reservation")) {
                pageReponse = listerReservation(request);
            } else if (demande.equalsIgnoreCase("ajouter.reservation")) {
                pageReponse = ajouterReservation(request);
            } else if (demande.equalsIgnoreCase("enregistrer.reservation")) {
                pageReponse = enregistrerReservation(request);
                erreur = null;
            } else if (demande.equalsIgnoreCase("confirmer.reservation")) {
                pageReponse = confirmerReservation(request);
                erreur = null;
            } else if (demande.equalsIgnoreCase("supprimer.reservation")) {
                pageReponse = supprimerReservation(request);
                erreur = null;
            }
        } catch (Exception e) {
            // Si une erreur survient, on enregistre en session l'url d'origine de la requête, et on redirige l'utilisateur vers cette même URL (évite les boucles de redirection)
            if(session.getAttribute("redirect") == null) {
                session.setAttribute("redirect",  request.getHeader("Referer"));
            }
            
            pageReponse = session.getAttribute("redirect").toString();
            erreur = e.getMessage();
            request.setAttribute("erreur", erreur);
        } finally {
            if(erreur == null) {
                // Si il n y a pas d'erreur, on vide la variable de session
                session.setAttribute("redirect", null);
            }
           
            RequestDispatcher dsp = request.getRequestDispatcher(pageReponse);
            dsp.forward(request, response);
        }
    }

    /**
     * Ajouter une réservation
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String ajouterReservation(HttpServletRequest request) throws Exception {
        Oeuvre oeuvre;
        Adherent adherent;
        String pageReponse;
        int id_oeuvre;
        try {
            id_oeuvre = Integer.parseInt(request.getParameter("id"));
            oeuvre = new Oeuvre();
            adherent = new Adherent();
            oeuvre.lire_Id(id_oeuvre);
            request.setAttribute("oeuvreR", oeuvre);
            request.setAttribute("lAdhrentsR", adherent.liste());
            request.setAttribute("titre", "Réservation d'une oeuvre");
            pageReponse = "/reservation.jsp";
            return (pageReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Enregistrer une réservation
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String enregistrerReservation(HttpServletRequest request) throws Exception {
        Reservation reservation;
        String pageReponse;
        try {
            int idAdherent = Integer.parseInt(request.getParameter("lstAdherents"));
            int idOeuvre = Integer.parseInt(request.getParameter("idOeuvre"));
            String dateReservation = request.getParameter("txtDate");
            reservation = new Reservation(idOeuvre, idAdherent);
            reservation.setDate_reservation(Utilitaire.StrToDate(dateReservation, "dd/MM/yyyy"));
            reservation.ajouter();
            pageReponse = listerReservation(request);
            return (pageReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Confirmer une réservation
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String confirmerReservation(HttpServletRequest request) throws Exception {
        Reservation reservation;
        String pageReponse;
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("idOeuvre"));
            String date = request.getParameter("date");
            reservation = new Reservation();
            reservation.valider(idOeuvre, date);
            pageReponse = listerReservation(request);
            return (pageReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Supprimer une réservation
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String supprimerReservation(HttpServletRequest request) throws Exception {
        Reservation reservation;
        String pageReponse;
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("idOeuvre"));
            String date = request.getParameter("date");
            reservation = new Reservation();
            reservation.supprimer(idOeuvre, date);
            pageReponse = listerReservation(request);
            return (pageReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Lister les réservations
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String listerReservation(HttpServletRequest request) throws Exception {
        Reservation reservation;
        String pageReponse;
        try {
            reservation = new Reservation();
            request.setAttribute("lReservationsR", reservation.liste());
            request.setAttribute("titre", "Liste des réservations");
            pageReponse = "/listereservations.jsp";
            return (pageReponse);
        } catch (Exception e) {
            throw e;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
