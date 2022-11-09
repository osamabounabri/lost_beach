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
public class BagninoFactory {

    private static BagninoFactory instance;

    private BagninoFactory() {
    }

    public static BagninoFactory getInstance() {

        if (instance == null) {

            instance = new BagninoFactory();

        }

        return instance;

    }

    /*recupera tutti i bagnini dal database e restituisce una lista con gli stessi*/
    public List<Bagnino> getAllBagnini() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<Bagnino> allBagnini = new ArrayList<>();

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM bagnino";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            while (set.next()) {

                Bagnino bagnino = new Bagnino();
                bagnino.setNome(set.getString("nome"));
                bagnino.setCognome(set.getString("cognome"));
                bagnino.setTelefono(set.getString("telefono"));
                bagnino.setEmail(set.getString("email"));
                bagnino.setAttestati(set.getString("attestati"));
                allBagnini.add(bagnino);

            }

            return allBagnini;

        } catch (SQLException e) {

            Logger.getLogger(BagninoFactory.class.getName()).log(Level.SEVERE, null, e);

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
