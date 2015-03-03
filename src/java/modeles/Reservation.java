/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

import dao.*;



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

    /**
     * Convertit une chaîne en date avant de l'affecter
     * @param date_reservation
     * @throws Exception
     */
    /*public void setDate_reservation(String date_reservation) throws Exception {
        this.date_reservation = Utilitaire.StrToDate(date_reservation, "dd/MM/yyyy");
    }*/


    /**
     * Liste des Réservations en Attente
     * @return List<Reservation> Collection de Réservations
     * @throws Exception
     */
    /*public List<Reservation> liste() throws Exception {


        try {


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
    }*/

    /**
     * Met à jour une Réservation dans la base de données
     * @throws Exception
     */
   /* public void modifier() throws Exception {


        try {


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
*/
    /**
     * Insert une Réservation dans la base de données
     * @throws Exception
     */
    /*public void ajouter() throws Exception {


        try {


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
*/
}
