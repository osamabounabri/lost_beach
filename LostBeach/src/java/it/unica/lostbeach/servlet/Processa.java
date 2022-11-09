/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.exceptions.InvalidParamException;
import it.unica.lostbeach.model.FatturaFactory;
import it.unica.lostbeach.model.Prenotazione;
import it.unica.lostbeach.model.PrenotazioneFactory;
import it.unica.lostbeach.model.Slot;
import it.unica.lostbeach.model.SlotFactory;
import it.unica.lostbeach.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "Processa", urlPatterns = {"/Processa"})
public class Processa extends HttpServlet {

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

        HttpSession session = request.getSession(false); //crea o recupera una sessione
        
        /*dichiara e inizializza le variabili necessarie alla connessione con il database*/
        int res = 0;
        int prenotazioneEliminata = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        int i;
        String idPrenotazione = request.getParameter("processaId");//recupera l'id della prenotazione da processare
        String sceltaFattura; //variabile per la scelta della fattura

        try {

            if (session != null && session.getAttribute("user") != null) {//controlla che la sessione esista

                //crea una lista con tuute le prenotazioni presenti in database
                List<Prenotazione> prenotazioni = PrenotazioneFactory.getInstance().getAllPrenotazioni();

                int totPrenotazioni = prenotazioni.size();//dimensione lista prenotazioni
                
                //controllo che l'id della prenotazione inserito sia valido
                Utils.checkInteger(idPrenotazione, 1, prenotazioni.get(prenotazioni.size() - 1).getId());

                Prenotazione prenotazione_processata = null;// variabile prenotazione da processare

                /*cerca la prenotazione da processare nella lista delle prenotazioni in base all'id*/
                for (i = 0; i < totPrenotazioni; i++) {

                    if (prenotazioni.get(i).getId() == Integer.valueOf(idPrenotazione)) {

                        prenotazione_processata = prenotazioni.get(i);

                    }

                }

                /*controlla di aver trovato la prenotazione*/
                if (prenotazione_processata == null) {

                    throw new InvalidParamException("Qualcosa è andato storto nella prenotazione, compilazione fattura annullata!");

                }

                sceltaFattura = FatturaFactory.getInstance().recuperaSceltaFattura(prenotazione_processata);//recupero la scelta della fattura

                /*
                se la la scelta della fattur è si, la fattura viene generata altrimenti no,
                in ogni caso la prenotazione processata viene eliminata
                */
                if (sceltaFattura.equals("si")) {

                    res = FatturaFactory.getInstance().generaFattura(prenotazione_processata);

                    if (res != 0) {

                        //elimina la prenotazione processata dal database
                        prenotazioneEliminata = PrenotazioneFactory.getInstance().eliminaPrenotazione(prenotazione_processata);

                        if (prenotazioneEliminata != 0) {

                            String mexSuccesso = "Fattura emessa con successo";
                    request.setAttribute("mexSuccesso", mexSuccesso);
                    request.getRequestDispatcher("Successo.jsp").forward(request, response);

                        }

                    }

                } else if (sceltaFattura.equals("no")) {

                    prenotazioneEliminata = PrenotazioneFactory.getInstance().eliminaPrenotazione(prenotazione_processata);

                    if (prenotazioneEliminata != 0) {

                        String mexSuccesso = "Fattura emessa con successo";
                    request.setAttribute("mexSuccesso", mexSuccesso);
                    request.getRequestDispatcher("Successo.jsp").forward(request, response);

                    }

                } else {//lancia un eccezione se la scleta della fattura non è ne si ne no

                    throw new InvalidParamException("Qualcosa è andato storto nel recuperare la prenotazione");

                }

            } else {//se la sessione non è presente, manda al login

                request.getRequestDispatcher("login.jsp").forward(request, response);

            }

        } catch (InvalidParamException e) {

            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);

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
