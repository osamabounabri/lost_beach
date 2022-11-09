/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.db.DatabaseManager;
import it.unica.lostbeach.exceptions.InvalidParamException;
import it.unica.lostbeach.model.Prenotazione;
import it.unica.lostbeach.model.PrenotazioneFactory;
import it.unica.lostbeach.model.Slot;
import it.unica.lostbeach.model.SlotFactory;
import it.unica.lostbeach.model.Utente;
import it.unica.lostbeach.model.UtenteFactory;
import it.unica.lostbeach.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fpw
 */
@WebServlet(name = "Prenota", urlPatterns = {"/prenotazione"})
public class Prenota extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);//recupera o crea una sessione
        
        /*dichiaro e inizializzo le variabili per la connessione col database*/
        int res = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        int i;
        
        /*recupero i dati dal client*/
        String idSlotHome = request.getParameter("prenotaSlot");
        String postiPrenotati = request.getParameter("prenotaPosti");
        String user = session.getAttribute("user").toString();//recupero l'utente della sessione
        boolean flag = false; //variabile di controllo

        
        try {

            /*controlla che l'utente sia loggato*/
            if (session != null && session.getAttribute("user") != null) {

                List<Slot> slots = SlotFactory.getInstance().getAllSlots(); //recupera tutti gli slot dal database
                int totSlot = slots.size();//dimensione lista degli slot
                

                Slot slotPrenotato = null; //dichiaro e inizializzo uno slot

                /*cerco lo slot prenotato in base all'id selezionato dal client*/
                for (i = 0; i < totSlot; i++) {

                    if (slots.get(i).getId() == Integer.valueOf(idSlotHome)) {

                        slotPrenotato = slots.get(i);

                    }

                }

                /*altro controllo sull'esistenza dello slot che lancia un eccezione in caso di problemi come lo slot non trovato*/
                if (slotPrenotato == null) {

                    throw new InvalidParamException("Qualcosa è andato storto nella prenotazione, prenotazione annullata!");

                }

                Utils.checkPosti(slotPrenotato.getPosti(), postiPrenotati);//controllo sulla quantità di posti disponibili e quelli prenotati

                /*connessione al database e inserimento della prenotazione */
                conn = DatabaseManager.getInstance().getDbConnection();

                //modifico la prenotazione se ne esiste gia una con gli stessi dati (a parte i posti)
                res = PrenotazioneFactory.getInstance().modificaPrenotazione(slotPrenotato, Integer.valueOf(postiPrenotati), user);

                /*controlla che non esiste gia una prenotazione (ancora non processata) simile e ne inserisco una nuova in caso negativo*/
                if (res == 0) {

                    String query = "INSERT INTO prenotazione VALUES (default,?,?,?,?,?,?)";
                    stmt = conn.prepareStatement(query);

                    stmt.setString(1, user);
                    stmt.setString(2, slotPrenotato.getOrario());

                    java.sql.Date sqlData = new java.sql.Date(slotPrenotato.getData().getTime());

                    stmt.setDate(3, sqlData);
                    stmt.setInt(4, Integer.valueOf(postiPrenotati));
                    stmt.setInt(5, slotPrenotato.getId());

                    java.sql.Date sqlDataCorrente = new java.sql.Date(java.util.Calendar.getInstance().getTimeInMillis());

                    stmt.setDate(6, sqlDataCorrente);
                    res = stmt.executeUpdate();
                    
                   

                }

                /*controlla se esiste una prenotazione (ancora non processata) simile e in caso positivo ne aggiorna i posti*/
                if (res != 0) {

                    String aggiornaSlotQuery = "UPDATE slot SET posti = (? - ?) where id = ?";
                    stmt = null;
                    res = 0;
                    stmt = conn.prepareStatement(aggiornaSlotQuery);
                    stmt.setInt(1, slotPrenotato.getPosti());
                    stmt.setInt(2, Integer.valueOf(postiPrenotati));
                    stmt.setInt(3, slotPrenotato.getId());
                    res = stmt.executeUpdate();

                    if (res != 0) {
                    String mexSuccesso = "Prenotazione effettuata con successo";
                    request.setAttribute("mexSuccesso", mexSuccesso);
                    request.getRequestDispatcher("Successo.jsp").forward(request, response);
                    }
                }

            } else {//nessuna sessione in corso, mando al login

                request.getRequestDispatcher("login.jsp").forward(request, response);

            }

        } catch (InvalidParamException e) {

            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);

        } catch (SQLException e) {

            Logger.getLogger(SlotFactory.class.getName()).log(Level.SEVERE, null, e);
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
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
