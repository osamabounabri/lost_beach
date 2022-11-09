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
public class SlotFactory {

    private static SlotFactory instance;

    private SlotFactory() {
    }

    public static SlotFactory getInstance() {

        if (instance == null) {

            instance = new SlotFactory();

        }

        return instance;

    }

    /*restituisce una lista con tutti gli slot recuperati dal database*/
    public List<Slot> getAllSlots() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<Slot> slots = new ArrayList<>();

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM slot";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            while (set.next()) {

                Slot slot = new Slot();
                slot.setId(set.getInt("id"));
                slot.setOrario(set.getString("orario"));
                slot.setData(set.getDate("data"));
                slot.setPosti(set.getInt("posti"));
                slot.setBagnino(set.getString("bagnino"));
                slots.add(slot);

            }

            return slots;

        } catch (SQLException e) {

            Logger.getLogger(SlotFactory.class.getName()).log(Level.SEVERE, null, e);

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

    /*prende uno slot e un numero di posti e li aggiunge allo slot aggiornandolo nel database,*/
    public int aggiornaSlot(Slot slot, int postiPiu) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        int res = 0;

        int postiNuovi = postiPiu + slot.getPosti();

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "UPDATE slot SET posti = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, postiNuovi);
            stmt.setInt(2, slot.getId());

            res = stmt.executeUpdate();

            return res;

        } catch (SQLException e) {

            Logger.getLogger(SlotFactory.class.getName()).log(Level.SEVERE, null, e);

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

    /*recupera lo slot che ha l'id passato in input*/
    public Slot getSlotById(int id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM slot where id = ?";
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            set = stmt.executeQuery();

            if (set.next()) {

                Slot slot = new Slot();
                slot.setId(set.getInt("id"));
                slot.setOrario(set.getString("orario"));
                slot.setData(set.getDate("data"));
                slot.setPosti(set.getInt("posti"));
                slot.setBagnino(set.getString("bagnino"));
                return slot;

            } else {

                return null;

            }

        } catch (SQLException e) {

            Logger.getLogger(SlotFactory.class.getName()).log(Level.SEVERE, null, e);

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
}
