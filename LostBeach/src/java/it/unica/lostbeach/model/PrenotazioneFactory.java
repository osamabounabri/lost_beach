/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.model;

import it.unica.lostbeach.db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class PrenotazioneFactory {

    private static PrenotazioneFactory instance;

    private PrenotazioneFactory() {
    }

    public static PrenotazioneFactory getInstance() {

        if (instance == null) {

            instance = new PrenotazioneFactory();

        }

        return instance;

    }

    /*recupera tutte le prenotazioni dal database*/
    public List<Prenotazione> getAllPrenotazioni() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<Prenotazione> prenotazioni = new ArrayList<>();

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM prenotazione";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            while (set.next()) {

                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setId(set.getInt("id"));
                prenotazione.setCliente(set.getString("cliente"));
                prenotazione.setOrario(set.getString("orario"));
                prenotazione.setData(set.getDate("data"));
                prenotazione.setPosti(set.getInt("posti"));
                prenotazione.setSlot(set.getInt("slot"));
                prenotazione.setData_di_prenotazione(set.getDate("data_di_prenotazione"));
                prenotazioni.add(prenotazione);

            }

            return prenotazioni;

        } catch (SQLException e) {

            Logger.getLogger(PrenotazioneFactory.class.getName()).log(Level.SEVERE, null, e);

        } finally {

            try {
                set.close();
            } catch (Exception e) {
            }
            try {
                stmt.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }

        }

        return null;

    }

    /*elimina la prenotazione passata in input dal database*/
    public int eliminaPrenotazione(Prenotazione prenotazione) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        int res = 0;

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "Delete FROM prenotazione where (id = ?)";
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, prenotazione.getId());

            res = stmt.executeUpdate();

            return res;

        } catch (SQLException e) {

            Logger.getLogger(PrenotazioneFactory.class.getName()).log(Level.SEVERE, null, e);

        } finally {

            try {
                set.close();
            } catch (Exception e) {
            }
            try {
                stmt.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }

        }

        return 0;

    }

    /*
      modifica i posti della prenotazione se ne esiste gia una con la stessa data,
      orario, e id utente (username)
    */
    public int modificaPrenotazione(Slot slot, int postiDaAggiungere, String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        int res = 0;

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            java.sql.Date sqlData = new java.sql.Date(slot.getData().getTime());

            //seleziona la prenotazione
            String query = "SELECT * FROM prenotazione where cliente = ? and orario = ? and data = ?";
            stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, slot.getOrario());
            stmt.setDate(3, sqlData);

            set = stmt.executeQuery();

            if (set.next()) {

                //modifica la prenoazione selezionata
                String query2 = "UPDATE prenotazione SET posti = (? + ?) where id = ?";
                stmt = conn.prepareStatement(query2);

                stmt.setInt(1, set.getInt("posti"));
                stmt.setInt(2, postiDaAggiungere);
                stmt.setInt(3, set.getInt("id"));

                res = stmt.executeUpdate();

                return res;

            }

        } catch (SQLException e) {

            Logger.getLogger(PrenotazioneFactory.class.getName()).log(Level.SEVERE, null, e);

        } finally {

            try {
                set.close();
            } catch (Exception e) {
            }
            try {
                stmt.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }

        }

        return res;

    }

}
