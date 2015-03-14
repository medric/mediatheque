/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeles.Oeuvre;
import modeles.Proprietaire;
import outils.Utilitaire;

/**
 *
 * @author Epulapp
 */
@WebServlet(name = "oeuvreServlet", urlPatterns = {"/oeuvreServlet"})
public class oeuvreServlet extends HttpServlet {

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
        // Action demandée
        String demande;

        // Page à injecter par défaut
        String pageReponse = request.getHeader("referer");

        try {
            demande = Utilitaire.getDemande(request);

            // Si l'utilisateur n'est pas authentifié, il est redirigé vers l'index
            if (!Utilitaire.estConnecte(request)) {
                pageReponse = "/";
                return;
            }
            
            if (demande.equalsIgnoreCase("catalogue.oeuvre")) {
                pageReponse = listerOeuvres(request);
            } else if (demande.equalsIgnoreCase("creer.oeuvre")) {
                pageReponse = creerOeuvre(request);
            } else if (demande.equalsIgnoreCase("modifier.oeuvre")) {
                pageReponse = modifierOeuvre(request);
            } else if (demande.equalsIgnoreCase("enregistrer.oeuvre")) {
                pageReponse = enregistrerOeuvre(request);
            }
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            request.setAttribute("erreur", erreur);
            RequestDispatcher dsp = request.getRequestDispatcher(pageReponse);
            dsp.forward(request, response);
        }
    }

    /**
     * Lister les oeuvres
     * @param request
     * @return les oeuvres
     * @throws Exception 
     */
    private String listerOeuvres(HttpServletRequest request) throws Exception {
        Oeuvre oeuvre;
        String pageReponse;
        try {
            pageReponse = "";
            oeuvre = new Oeuvre();
            request.setAttribute("lOeuvresR", oeuvre.liste());
            request.setAttribute("titre", "Catalogue des oeuvres");
            pageReponse = "/catalogue.jsp";
            return (pageReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Création d'une oeuvre
     * @param request
     * @return
     * @throws Exception 
     */
    private String creerOeuvre(HttpServletRequest request) throws Exception {

        Oeuvre oeuvre;
        List<Proprietaire> proprietaires;
        Proprietaire proprietaire;
        String pageReponse;

        try {
            oeuvre = new Oeuvre();
            request.setAttribute("oeuvreR", oeuvre);
            HttpSession session = request.getSession(true);
            session.setAttribute("id", 0);            
            proprietaire = new Proprietaire();
            proprietaires = proprietaire.liste();
            request.setAttribute("lstProprietairesR", proprietaires);
            request.setAttribute("titre", "Créer une oeuvre");
            pageReponse = "/oeuvre.jsp";
            return (pageReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Modification d'une oeuvre
     * @param request
     * @return
     * @throws Exception 
     */
    private String modifierOeuvre(HttpServletRequest request) throws Exception {

        Oeuvre oeuvre;
        List<Proprietaire> proprietaires;
        Proprietaire proprietaire;
        String pageReponse;
        int idOeuvre;

        try {
            pageReponse = "/catalogue.jsp";
            idOeuvre = Integer.parseInt(request.getParameter("id"));
            if (idOeuvre > 0) {
                oeuvre = new Oeuvre();
                proprietaire = new Proprietaire();
                proprietaires = proprietaire.liste();
                oeuvre.lire_Id(idOeuvre);
                request.setAttribute("oeuvreR", oeuvre);
                request.setAttribute("lstProprietairesR", proprietaires);
                request.setAttribute("titre", "Modifier une oeuvre");
                pageReponse = "/oeuvre.jsp";
            } else {
                erreur = "Utilisateur inconnu !";
            }
            return (pageReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Enregistrement d'une oeuvre par mise à jour ou ajout
     * @param request
     * @return
     * @throws Exception 
     */
    private String enregistrerOeuvre(HttpServletRequest request) throws Exception {
        Oeuvre oeuvre;
        String pageReponse;
        int idOeuvre;
        try {
            oeuvre = new Oeuvre();
            idOeuvre = Integer.parseInt(request.getParameter("id"));
            oeuvre.setId_oeuvre(idOeuvre);
            oeuvre.setTitre(request.getParameter("txtTitre"));
            oeuvre.setPrix(Double.parseDouble(request.getParameter("txtPrix")));
            oeuvre.setId_proprietaire(Integer.parseInt(request.getParameter("lstProprietaires")));
            if (idOeuvre > 0) {
                oeuvre.modifier();
            } else {
                oeuvre.ajouter();
            }
            request.setAttribute("lOeuvresR", oeuvre.liste());
            request.setAttribute("titre", "Oeuvre");
            pageReponse = "/catalogue.jsp";
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
