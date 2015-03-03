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
import modeles.Oeuvre;
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
        
        // Page à injecter
        String pageReponse = "index.jsp";
        
        try {
            demande = Utilitaire.getDemande(request);
            
            // Si l'utilisateur n'est pas authentifié, il est rediriger vers l'index
            if(!Utilitaire.estConnecte(request)) return;
            
            if (demande.equalsIgnoreCase("catalogue.oeuvre")) {
                pageReponse = listerOeuvres(request);
            } 
        } catch (Exception e) {
            erreur = e.getMessage();
        }
        finally {
            request.setAttribute("erreur", erreur);
            RequestDispatcher dsp = request.getRequestDispatcher(pageReponse);
            dsp.forward(request, response);
        }
    }
    
    private String listerOeuvres(HttpServletRequest request) throws Exception {
        Oeuvre oeuvre;
        String pageReponse;
        try {
            pageReponse = "";
            oeuvre = new Oeuvre();
            request.setAttribute("lOeuvresR", oeuvre.liste());
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
