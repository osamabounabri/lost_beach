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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class FatturaFactory {
    
    private static FatturaFactory instance;
    
    private FatturaFactory() {
    }
    
    public static FatturaFactory getInstance() {
        
        if (instance == null) {
            
            instance = new FatturaFactory();
            
        }
        
        return instance;
        
    }
    
    /*Recupera tutte le fatture dal database*/
    public List<Fattura> getAllFatture() {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<Fattura> fatture = new ArrayList<>();
        
        try {
            
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "SELECT * FROM fattura";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            while (set.next()) {
                
                Fattura fattura = new Fattura();
                fattura.setId(set.getInt("id"));
                fattura.setPrezzo(set.getFloat("prezzo"));
                fattura.setData(set.getDate("data"));
                fattura.setDescrizione(set.getString("descrizione"));
                fattura.setPosti(set.getInt("posti"));
                fattura.setUtente_id(set.getString("utente_id"));
                fattura.setBagnino(set.getString("bagnino_id"));
                fatture.add(fattura);
                
            }
            
            return fatture;
            
        } catch (SQLException e) {
            
            Logger.getLogger(FatturaFactory.class.getName()).log(Level.SEVERE, null, e);
            
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
    
    /*Prende una prenotazione e genera la fattura associata inserendola nel database*/
    public int generaFattura(Prenotazione prenotazione) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        int res = 0;
        Fattura fattura = new Fattura();
        
        try {
            
            conn = DatabaseManager.getInstance().getDbConnection();
            String desc = "prenotazione di postazioni sdraio + ombrellone in orario: " + prenotazione.getOrario()
                    + " e data " + prenotazione.getData();
            
            String insertFattQuery = "INSERT INTO fattura VALUES (default,?,?,?,?,?,?)";
            
            stmt = conn.prepareStatement(insertFattQuery);
            
            stmt.setInt(1, prenotazione.getPosti() * 5);
            
            java.sql.Date sqlData = new java.sql.Date(prenotazione.getData_di_prenotazione().getTime());
            
            stmt.setDate(2, sqlData);
            stmt.setInt(3, prenotazione.getPosti());
            stmt.setString(4, desc);
            stmt.setString(5, prenotazione.getCliente());
            stmt.setString(6, SlotFactory.getInstance().getSlotById(prenotazione.getSlot()).getBagnino());
            
            res = stmt.executeUpdate();
            
            return res;
            
        } catch (SQLException e) {
            
            Logger.getLogger(FatturaFactory.class.getName()).log(Level.SEVERE, null, e);
            
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
      prende una prenotazione e recupera la decisione dell'utente
      sulla scelta di ricevere fatture,restituendo "si" oppure "no"
    */
    public String recuperaSceltaFattura(Prenotazione prenotazione) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        String fattura = null;
        
        try {
            
            /*Interrogazione del database*/
            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "SELECT scelta_fattura FROM prenotazione JOIN utente ON cliente = username where id = ? ";
            
            stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, prenotazione.getId());
            
            set = stmt.executeQuery();
            
            while (set.next()) {
                
                fattura = set.getString("scelta_fattura");
                
            }
            
            return fattura;
            
        } catch (SQLException e) {
            
            Logger.getLogger(FatturaFactory.class.getName()).log(Level.SEVERE, null, e);
            
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
        
        return fattura;
        
    }
    
    /*prende un utente e restituisce una lista delle sue fatture*/
    public List<Fattura> getFattureByUtente(Utente utente) {
    
     List<Fattura> allFatture = FatturaFactory.getInstance().getAllFatture(); //recupera tutte le fatture dal database
        
     int i;

        List<Fattura> fatturaUtente = new ArrayList<>(); //crea una nuova lista di fatture per ora vuota

        /*controllo che la fattura sia associata all'utente in sessione e la aggiungo alla nuova lista di fatture*/
        for (i = 0; i < allFatture.size(); i++) {

            //controllo username e metti nella nuova lista
            if (allFatture.get(i).getUtente_id().equals(utente.getUsername())) {

                fatturaUtente.add(allFatture.get(i));

            }

        }
    
    if(fatturaUtente.size() > 0){
    
    return fatturaUtente;
    
    }else{
    
    return null;
    
    
    }
        
    }
    
}
