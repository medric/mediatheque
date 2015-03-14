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
public class Proprietaire {
    private int id_proprietaire;
    private String nom_proprietaire;
    private String prenom_proprietaire;

    public Proprietaire() {
    }

    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    public String getNom_proprietaire() {
        return nom_proprietaire;
    }

    public void setNom_proprietaire(String nom_proprietaire) {
        this.nom_proprietaire = nom_proprietaire;
    }

    public String getPrenom_proprietaire() {
        return prenom_proprietaire;
    }

    public void setPrenom_proprietaire(String prenom_proprietaire) {
        this.prenom_proprietaire = prenom_proprietaire;
    }

    /**
     * Lecture d'un Propriétaire dans la base de données
     * @param id Id du Propriétaire à lire
     * @throws Exception
     */
    public void lire_Id(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareStatement("select * from proprietaire where id_proprietaire = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Construit l'objet courant à partir du ResulSet retourné
                constuire(this, rs);
            } else {
                throw new Exception("Propriétaire inconnu !");
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
     * Liste des Propriétaires
     * @return List<Proprietaire> Collection de Propriétaires
     * @throws Exception
     */
    public List<Proprietaire> liste() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        Proprietaire proprietaire;
        List<Proprietaire> lProprietaires = new ArrayList<Proprietaire>();
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareStatement("select * from proprietaire");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                proprietaire = new Proprietaire();
                // Construit l'objet à partir du ResulSet retourné
                constuire(proprietaire, rs);
                lProprietaires.add(proprietaire);
            }
         
            return(lProprietaires);
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
    
    /**
     * Construction d'un proprietaire à partir des setters
     * @param proprietaire
     * @param rs
     * @throws SQLException 
     */
    private void constuire(Proprietaire proprietaire, ResultSet rs) throws SQLException{
       proprietaire.setId_proprietaire(rs.getInt("id_proprietaire"));
       proprietaire.setNom_proprietaire(rs.getString("nom_proprietaire"));
       proprietaire.setPrenom_proprietaire(rs.getString("prenom_proprietaire"));
    }
}
