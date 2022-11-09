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
public class MexFactory {

    private static MexFactory instance;

    private MexFactory() {
    }

    public static MexFactory getInstance() {

        if (instance == null) {

            instance = new MexFactory();

        }

        return instance;

    }

    /*recupera tutti i messaggi e l'autore degli stessi dal database e restituisce una lista di essi*/
    public List<Mex> getAllMessaggi() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<Mex> allMessaggi = new ArrayList<>();

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM messaggio";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            while (set.next()) {

                Mex messaggio = new Mex();
                messaggio.setUsername(set.getString("username"));
                messaggio.setMessaggio(set.getString("mex"));
                allMessaggi.add(messaggio);

            }

            return allMessaggi;

        } catch (SQLException e) {

            Logger.getLogger(MexFactory.class.getName()).log(Level.SEVERE, null, e);

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
