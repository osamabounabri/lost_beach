/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.model;

import java.sql.Connection;
import it.unica.lostbeach.db.DatabaseManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author fpw
 */
public class UtenteFactory {

    private static UtenteFactory instance;

    private UtenteFactory() {
    }

    public static UtenteFactory getInstance() {

        if (instance == null) {

            instance = new UtenteFactory();

        }

        return instance;

    }

    /*recupera l'utente dal database in base all'username e la password passati in input*/
    public Utente getUtenteByUsernamePassword(String username, String password) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM utente WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            set = stmt.executeQuery();

            if (set.next()) {

                Utente utente = new Utente();
                utente.setUsername(set.getString("username"));
                utente.setPassword(set.getString("password"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                utente.setDataNascita(set.getDate("data_di_nascita"));
                utente.setCodiceFiscale(set.getString("codice_fiscale"));
                utente.setSesso(set.getString("sesso"));
                utente.setEmail(set.getString("email"));
                utente.setTelefono(set.getString("telefono"));
                utente.setFattura(set.getString("scelta_fattura"));
                utente.setAmministratore(set.getBoolean("amministratore"));
                return utente;

            } else {

                return null;

            }

        } catch (SQLException e) {

            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, e);

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

     /*recupera l'utente dal database in base all'username passato in input*/
    public Utente getUtenteByUsername(String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM utente WHERE username = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            set = stmt.executeQuery();

            if (set.next()) {

                Utente utente = new Utente();
                utente.setUsername(set.getString("username"));
                utente.setPassword(set.getString("password"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                utente.setDataNascita(set.getDate("data_di_nascita"));
                utente.setCodiceFiscale(set.getString("codice_fiscale"));
                utente.setSesso(set.getString("sesso"));
                utente.setEmail(set.getString("email"));
                utente.setTelefono(set.getString("telefono"));
                utente.setFattura(set.getString("scelta_fattura"));
                utente.setAmministratore(set.getBoolean("amministratore"));
                return utente;

            } else {

                return null;

            }

        } catch (SQLException e) {

            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, e);

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
    
         /*recupera l'utente dal database in base all'username passato in input*/
    public Utente getUtenteByNomeCognome(String nomeCognome) {

  List<Utente> tuttiGliUtenti = new ArrayList<>();
  
  tuttiGliUtenti = getAllUtenti();
  int i;
  
  for(i = 0; i < tuttiGliUtenti.size(); i++){
  
        if(nomeCognome.contains(tuttiGliUtenti.get(i).getNome()) && nomeCognome.contains(tuttiGliUtenti.get(i).getCognome())){
        
        return tuttiGliUtenti.get(i);
            
        }
  
  
  }
        return null;

    }

    /*recupera tutti gl utenti dal database che non siano amministratori e restituisce una lsta con gli stessi*/
    public List<Utente> getAllUtenti() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<Utente> utenti = new ArrayList<>();

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM utente WHERE amministratore = false";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            while (set.next()) {

                Utente utente = new Utente();
                utente.setUsername(set.getString("username"));
                utente.setPassword(set.getString("password"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                utente.setDataNascita(set.getDate("data_di_nascita"));
                utente.setCodiceFiscale(set.getString("codice_fiscale"));
                utente.setSesso(set.getString("sesso"));
                utente.setEmail(set.getString("email"));
                utente.setTelefono(set.getString("telefono"));
                utente.setFattura(set.getString("scelta_fattura"));
                utente.setAmministratore(set.getBoolean("amministratore"));
                utenti.add(utente);

            }

            return utenti;

        } catch (SQLException e) {

            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, e);

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

    /*restituisce una lista di utenti recuperati dal database secondo un certo ordine indicato in input*/
    public List<Utente> getAllUtenti(String ordinamento) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<Utente> utenti = new ArrayList<>();
        List<Utente> utentiSenzaPosti = new ArrayList<>();
        int i, j;
        boolean esisteGia = false;

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = null;

            if (ordinamento != null) {

                if (ordinamento.equals("CC")) { //caso ordinamento per cognome crescente

                    query = "SELECT * FROM utente WHERE amministratore = false ORDER BY cognome";

                } else if (ordinamento.equals("CD")) {//ordinamento per conome decrescente

                    query = "SELECT * FROM utente WHERE amministratore = false ORDER BY cognome DESC";

                } else if (ordinamento.equals("NC")) {//ordinamento per posti totali crescenti

                    /*creo una lista (ordinata in modo crescente in base ai posti prenotati) con solo gli utenti che hanno almeno una prenotazione*/
                    query = "SELECT username,password,nome,cognome,data_di_nascita,sesso,email,scelta_fattura,codice_fiscale,telefono,amministratore from utente join prenotazione on username = cliente group by(username) order by SUM(posti)";

                    stmt = conn.prepareStatement(query);
                    set = stmt.executeQuery();
                    while (set.next()) {

                        Utente utente = new Utente();
                        utente.setUsername(set.getString("username"));
                        utente.setPassword(set.getString("password"));
                        utente.setNome(set.getString("nome"));
                        utente.setCognome(set.getString("cognome"));
                        utente.setDataNascita(set.getDate("data_di_nascita"));
                        utente.setCodiceFiscale(set.getString("codice_fiscale"));
                        utente.setSesso(set.getString("sesso"));
                        utente.setEmail(set.getString("email"));
                        utente.setTelefono(set.getString("telefono"));
                        utente.setFattura(set.getString("scelta_fattura"));
                        utente.setAmministratore(set.getBoolean("amministratore"));
                        utenti.add(utente);

                    }

                    utentiSenzaPosti = getAllUtenti(); //recupera tutti gli utenti (tranne l'amministratore)
                    int numeroUtentiConPosti = utenti.size();

                    /*confronta la lista di tutti gli utenti e aggiunge quelli che non hanno prenotazioni in coda alla lista degli utenti con prenotazioni*/
                    for (i = 0; i < utentiSenzaPosti.size(); i++) {

                        for (j = 0; j < numeroUtentiConPosti; j++) {

                            if (utentiSenzaPosti.get(i).getUsername().equals(utenti.get(j).getUsername())) {

                                esisteGia = true;

                            }

                        }

                        if (esisteGia == false) {

                            utenti.add(utentiSenzaPosti.get(i));

                        }

                        esisteGia = false;

                    }

                    return utenti;

                } else if (ordinamento.equals("ND")) {//ordinamento per posti totali decrescenti

                    /*creo una lista (ordinata in modo decrescente in base ai posti prenotati) con solo gli utenti che hanno almeno una prenotazione*/
                    query = "SELECT username,password,nome,cognome,data_di_nascita,sesso,email,scelta_fattura,codice_fiscale,telefono,amministratore from utente join prenotazione on username = cliente group by(username) order by SUM(posti) DESC";

                    stmt = conn.prepareStatement(query);
                    set = stmt.executeQuery();
                    while (set.next()) {

                        Utente utente = new Utente();
                        utente.setUsername(set.getString("username"));
                        utente.setPassword(set.getString("password"));
                        utente.setNome(set.getString("nome"));
                        utente.setCognome(set.getString("cognome"));
                        utente.setDataNascita(set.getDate("data_di_nascita"));
                        utente.setCodiceFiscale(set.getString("codice_fiscale"));
                        utente.setSesso(set.getString("sesso"));
                        utente.setEmail(set.getString("email"));
                        utente.setTelefono(set.getString("telefono"));
                        utente.setFattura(set.getString("scelta_fattura"));
                        utente.setAmministratore(set.getBoolean("amministratore"));
                        utenti.add(utente);

                    }

                    utentiSenzaPosti = getAllUtenti();
                    int numeroUtentiConPosti = utenti.size();

                     /*confronta la lista di tutti gli utenti e aggiunge quelli che non hanno prenotazioni in coda alla lista degli utenti con prenotazioni*/
                    for (i = 0; i < utentiSenzaPosti.size(); i++) {

                        for (j = 0; j < numeroUtentiConPosti; j++) {

                            if (utentiSenzaPosti.get(i).getUsername().equals(utenti.get(j).getUsername())) {

                                esisteGia = true;

                            }

                        }

                        if (esisteGia == false) {

                            utenti.add(utentiSenzaPosti.get(i));

                        }

                        esisteGia = false;

                    }

                    return utenti;

                }

            } else { //se non ho scelto un ordinamento, recupera tutti gli utenti in base all'ordinamento di default del database

                query = "SELECT * FROM utente WHERE amministratore = false";

            }

            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            while (set.next()) {

                Utente utente = new Utente();
                utente.setUsername(set.getString("username"));
                utente.setPassword(set.getString("password"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                utente.setDataNascita(set.getDate("data_di_nascita"));
                utente.setCodiceFiscale(set.getString("codice_fiscale"));
                utente.setSesso(set.getString("sesso"));
                utente.setEmail(set.getString("email"));
                utente.setTelefono(set.getString("telefono"));
                utente.setFattura(set.getString("scelta_fattura"));
                utente.setAmministratore(set.getBoolean("amministratore"));
                utenti.add(utente);

            }

            return utenti;

        } catch (SQLException e) {

            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, e);

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
