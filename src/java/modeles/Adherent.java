/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeles;

import dao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author alain
 */
public class Adherent {
    private int id_adherent;
    private String nom_adherent;
    private String prenom_adherent;

    public Adherent() {
    }

    /**
     * @return the id_adherent
     */
    public int getId_adherent() {
        return id_adherent;
    }

    /**
     * @param id_adherent the id_adherent to set
     */
    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }

    /**
     * @return the nom_adherent
     */
    public String getNom_adherent() {
        return nom_adherent;
    }

    /**
     * @param nom_adherent the nom_adherent to set
     */
    public void setNom_adherent(String nom_adherent) {
        this.nom_adherent = nom_adherent;
    }

    /**
     * @return the prenom_adherent
     */
    public String getPrenom_adherent() {
        return prenom_adherent;
    }

    /**
     * @param prenom_adherent the prenom_adherent to set
     */
    public void setPrenom_adherent(String prenom_adherent) {
        this.prenom_adherent = prenom_adherent;
    }

    /**
     * Lecture d'un adhérent dans la base de données
     * @param id Id de l'adhérent à lire
     * @throws Exception
     */
    public void lire_Id(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            String requete = "select * from adherent where id_adherent = ?";
            ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Construit l'objet courant à partir du ResulSet retourné
                constuire(this, rs);
            } else {
                throw new Exception("Adherent inconnue !");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Liste des adhérents
     * @return List<Adherent> Collection d'adhérents
     * @throws Exception
     */
    public List<Adherent> liste() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        Adherent adherent;
        List<Adherent> lAdherents = new ArrayList<Adherent>();
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            String requete = "select * from adherent";
            ps = connection.prepareStatement(requete);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                adherent = new Adherent();
                // Construit l'objet à partir du ResulSet retourné
                constuire(adherent, rs);
                lAdherents.add(adherent);
            }
         
            return(lAdherents);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void constuire(Adherent adherent, ResultSet rs) throws SQLException{
        adherent.setId_adherent(rs.getInt("id_adherent"));
        adherent.setNom_adherent(rs.getString("nom_adherent"));
        adherent.setPrenom_adherent(rs.getString("prenom_adherent"));
    }
}
