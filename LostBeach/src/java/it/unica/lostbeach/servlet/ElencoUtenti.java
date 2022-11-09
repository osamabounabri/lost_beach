/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.db.DatabaseManager;
import it.unica.lostbeach.model.Prenotazione;
import it.unica.lostbeach.model.PrenotazioneFactory;
import it.unica.lostbeach.model.SlotFactory;
import it.unica.lostbeach.model.Utente;
import it.unica.lostbeach.model.UtenteFactory;
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

/**
 *
 * @author fpw
 */
@WebServlet(name = "ElencoUtenti", urlPatterns = {"/ElencoUtenti"})
public class ElencoUtenti extends HttpServlet {

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

        String ordinamento = request.getParameter("ordina"); //recupera la scelta del fattore con cui ordinare la lista degli utenti

        /*Recupera e crea la lista di utenti secondo l'ordinamento deciso dal client e setta un attributo con tale lista*/
        List<Utente> utenti = UtenteFactory.getInstance().getAllUtenti(ordinamento);
        request.setAttribute("listaUtenti", utenti);
        /*Recupera e crea la lista di prenotazioni e setta un attributo con tale lista*/
        List<Prenotazione> prenotazioni = PrenotazioneFactory.getInstance().getAllPrenotazioni();
        request.setAttribute("listaPrenotazioni", prenotazioni);

        request.getRequestDispatcher("homeAmministratore.jsp").forward(request, response);

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
