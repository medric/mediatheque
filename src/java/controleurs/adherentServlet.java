/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.Utilitaire;

/**
 *
 * @author Epulapp
 */
@WebServlet(name = "adherentServlet", urlPatterns = {"/adherentServlet"})
public class adherentServlet extends HttpServlet {
    private String erreur;
    
    // Identifiants
    private Map<String, String> identifiants;
          
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
        
        // On initialise les identifiants de manière brute
        identifiants = new HashMap<String, String>();
        identifiants.put("admin", "mdp");
        
        // Action demandée
        String demande;
        
        // Page à injecter
        String pageReponse = "index.jsp";
        
        try {
            demande = Utilitaire.getDemande(request);
                
            if (demande.equalsIgnoreCase("login.adherent")) {
                pageReponse = connecter(request);
            } 
            else if (demande.equalsIgnoreCase("deconnecter.adherent")) {
                pageReponse = deconnecter(request);
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
    
    /**
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    private String connecter(HttpServletRequest request) throws Exception {
        // Valeur par défaut, si l'utilisateur ne saisit pas les bons identifiants
        String pageReponse = "index.jsp";
        String login, pwd;
        try {
            login = request.getParameter("txtLogin");
            pwd = request.getParameter("txtPwd");
            if(identifiants.containsKey(login)) {
                if (identifiants.get(login).equals(pwd)) {
                    pageReponse = "accueil.jsp";
                    // Mise en session
                    HttpSession session = request.getSession(true);
                    session.setAttribute("id", login);
                } else {
                    erreur = "Login ou mot de passe inconnu !";
                }
            } else {
                erreur = "Login ou mot de passe inconnu !";
            }
        } catch (Exception e) {
            erreur = e.getMessage();
        }
        finally{
            return (pageReponse);
        }
    }
    
    /**
     * Déconnecter l'utilisateur
     * @param request
     * @return
     * @throws Exception 
     */
    private String deconnecter(HttpServletRequest request) throws Exception {
        String pageReponse;
        try {
            HttpSession session = request.getSession(true);
            session.setAttribute("id", null);
            pageReponse = "/index.jsp";
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