/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.exceptions.InvalidParamException;
import it.unica.lostbeach.model.BagninoFactory;
import it.unica.lostbeach.model.Fattura;
import it.unica.lostbeach.model.FatturaFactory;
import it.unica.lostbeach.model.Utente;
import it.unica.lostbeach.model.UtenteFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "RicercaUtente", urlPatterns = {"/RicercaUtente"})
public class RicercaUtente extends HttpServlet {

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
        String nomeCognome = request.getParameter("ricerca").toLowerCase();//ricavo i parametri dal client

        /*dichiarazione e inizializzazione variabili*/
        Utente utente = null;
        List<Fattura> fatture = new ArrayList<>();

        try {

            /*recupero l'utente cercato e le sue fatture associate*/
            utente = UtenteFactory.getInstance().getUtenteByNomeCognome(nomeCognome);
            fatture = FatturaFactory.getInstance().getFattureByUtente(utente);

            /*se l'utente non è stato trovato lancia un'ecceione*/
            if (utente == null) {

                throw new InvalidParamException("Utente non trovato");

            } else {

                /*setto l'utente e le sue fatture come variabili di sessione e torno alla jsp di ricerca utente*/
                session.setAttribute("listaFattureTrovata", fatture);
                session.setAttribute("utenteTrovato", utente);
                request.getRequestDispatcher("ricercaUtente.jsp").forward(request, response);

            }

        } catch (InvalidParamException e) {

            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", "ricercaUtente.jsp");
            request.getRequestDispatcher("error.jsp").forward(request, response);

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
