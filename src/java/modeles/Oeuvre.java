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
 * @author arsane
 */
public class Oeuvre {

    private int id_oeuvre;
    private int id_proprietaire;
    private String titre;
    private double prix;
    private Proprietaire proprietaire;

    public Oeuvre() {
    }

    /**
     * Initialise le Propriétaire d'une oeuvre
     * @param id_proprietaire Id du propriétaire de l'oeuvre
     * @throws Exception
     */
    public Oeuvre(int id_proprietaire) throws Exception {
        Proprietaire prop = new Proprietaire();
        prop.lire_Id(id_proprietaire);
        setProprietaire(prop);
    }

    public int getId_oeuvre() {
        return id_oeuvre;
    }

    public void setId_oeuvre(int id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }

    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    /**
     * @return the proprietaire
     */
    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Lecture d'une Oeuvre dans la base de données
     * @param id Id de l'oeuvre à lire
     * @throws Exception
     */
    public void lire_Id(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            String requete = "select * from oeuvre where id_oeuvre = ?";
            ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Construit l'objet courant à partir du ResulSet retourné
                constuire(this, rs);
            } else {
                throw new Exception("Oeuvre inconnue !");
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
     * Liste des oeuvre
     * @return List<Oeuvre> Collection d'oeuvres
     * @throws Exception
     */
    public List<Oeuvre> liste() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        Oeuvre oeuvre;
        List<Oeuvre> lOeuvres = new ArrayList<Oeuvre>();
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareStatement("select * from oeuvre");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                oeuvre = new Oeuvre(rs.getInt("id_proprietaire"));
                // Construit l'objet à partir du ResulSet retourné
                constuire(oeuvre, rs);
                lOeuvres.add(oeuvre);
            }
         
            return(lOeuvres);
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
     * Met à jour une oeuvre dans la base de données
     * @throws Exception
     */
    public void modifier() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            String requete = "update oeuvre set id_proprietaire = ?, titre = ?, prix = ? where id_oeuvre = ?";
            ps = connection.prepareStatement(requete);
            ps.setInt(1, getId_proprietaire());
            ps.setString(2, getTitre());
            ps.setDouble(3, getPrix());
            ps.setInt(4, getId_oeuvre());
            ps.executeUpdate();
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
     * Insert une oeuvre dans la base de données
     * @throws Exception
     */
    public void ajouter() throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        Db_outils db_outils;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);
            db_outils = new Db_outils();
            setId_oeuvre(db_outils.getIdentifiant(connection, "OEUVRE"));
            String requete = "insert into oeuvre (id_oeuvre, id_proprietaire, titre, prix)";
            requete += " values (?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(requete);
            ps.setInt(1, getId_oeuvre());
            ps.setInt(2, getId_proprietaire());
            ps.setString(3, getTitre());
            ps.setDouble(4, getPrix());
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
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
    
    private void constuire(Oeuvre oeuvre, ResultSet rs) throws SQLException{
        oeuvre.setId_oeuvre(rs.getInt("id_oeuvre"));
        oeuvre.setId_proprietaire(rs.getInt("id_proprietaire"));
        oeuvre.setPrix(rs.getDouble("prix"));
        oeuvre.setTitre(rs.getString("titre"));
    }
}
