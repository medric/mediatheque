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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import outils.Utilitaire;

/**
 *
 * @author alain
 */
public class Reservation {

    private int id_oeuvre;
    private int id_adherent;
    private java.util.Date date_reservation;
    private String statut;
    private Adherent adherent;
    private Oeuvre oeuvre;

    public Reservation() {
    }

    /**
     * Initialise l'Adhérent et l'Oeuvre de la Reservation
     * @param id_oeuvre Id de l'oeuvre réservée
     * @param id_adherent Id de l'adhérent réservant
     * @throws Exception
     */
    public Reservation(int id_oeuvre, int id_adherent) throws Exception {
        Oeuvre oeuvre = new Oeuvre();
        oeuvre.lire_Id(id_oeuvre);
        Adherent adherent = new Adherent();
        adherent.lire_Id(id_adherent);
        
        setId_oeuvre(id_oeuvre);
        setId_adherent(id_adherent);
        // Set oeuvre et adhérent
        setOeuvre(oeuvre);
        setAdherent(adherent);
    }
    
    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    
    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public int getId_oeuvre() {
        return id_oeuvre;
    }

    public void setId_oeuvre(int id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }

    public int getId_adherent() {
        return id_adherent;
    }

    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }

    public java.util.Date getDate_reservation() {
        return date_reservation;
    }

    public String formatDateReservation(String format) throws Exception {
        return Utilitaire.DateToStr(date_reservation, format);
    }
    
    /**
     * Convertit une chaîne en date avant de l'affecter
     * @param date_reservation
     * @throws Exception
     */
    public void setDate_reservation(Date date_reservation) throws Exception {
        this.date_reservation = date_reservation;
    }
   
    /**
     * Liste des Réservations en Attente
     * @return List<Reservation> Collection de Réservations
     * @throws Exception
     */
    public List<Reservation> liste() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        Reservation reservation;
        List<Reservation> lReservations = new ArrayList<Reservation>();
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            String requete = "select * from reservation where statut = 'ATTENTE'";
            ps = connection.prepareStatement(requete);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                reservation = new Reservation(rs.getInt("id_oeuvre"), rs.getInt("id_adherent"));
                // Construit l'objet à partir du ResulSet retourné
                constuire(reservation, rs);
                lReservations.add(reservation);
            }
         
            return(lReservations);
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
     * Confirme une Réservation dans la base de données
     * @throws Exception
     */
    public void valider(int idOeuvre, String date) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);
            String requete = "update reservation set statut = 'Confirmée' where id_oeuvre = ? and date_reservation = ?";
            ps = connection.prepareStatement(requete);
            ps.setInt(1, idOeuvre);
            ps.setDate(2, new java.sql.Date(Utilitaire.StrToDate(date, "yyyy-MM-dd").getTime()));
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
   
    /**
     * Modifie une Réservation dans la base de données
     * @throws Exception
     */
    public void ajouter() throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);
            String requete = "insert into reservation(date_reservation, id_oeuvre, id_adherent, statut)";
            requete += " values (?, ?, ?, ?)";
            ps = connection.prepareStatement(requete);
            ps.setDate(1, new java.sql.Date(getDate_reservation().getTime()));
            ps.setInt(2, getId_oeuvre());
            ps.setInt(3, getId_adherent());
            ps.setString(4, "ATTENTE");
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
    
    /**
    * Modifie une Réservation dans la base de données
    * @throws Exception
    */
    public void supprimer(int idOeuvre, String date) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);
            String requete = "delete from reservation where id_oeuvre = ? and date_reservation = ?";
            ps = connection.prepareStatement(requete);
            ps.setInt(1, idOeuvre);
            ps.setDate(2, new java.sql.Date(Utilitaire.StrToDate(date, "yyyy-MM-dd").getTime()));
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
            }
        }
    }

    /**
     * Construction d'une reservation à partir des setters
     * @param reservation
     * @param rs
     * @throws SQLException
     * @throws Exception 
     */
    private void constuire(Reservation reservation, ResultSet rs) throws SQLException, Exception{
        reservation.setId_adherent(rs.getInt("id_adherent"));
        reservation.setId_oeuvre(rs.getInt("id_oeuvre"));
        reservation.setStatut(rs.getString("statut"));
        reservation.setDate_reservation(rs.getDate("date_reservation"));
    }
}
