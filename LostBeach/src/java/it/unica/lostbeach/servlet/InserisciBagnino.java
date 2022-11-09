/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.db.DatabaseManager;
import it.unica.lostbeach.exceptions.InvalidParamException;
import it.unica.lostbeach.model.BagninoFactory;
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
@WebServlet(name = "InserisciBagnino", urlPatterns = {"/InserisciBagnino"})
public class InserisciBagnino extends HttpServlet {

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

        /* Recupero i dati dalla jsp */
        String nome = request.getParameter("nomeBagnino");
        String cognome = request.getParameter("cognomeBagnino");
        String email = request.getParameter("e-mailBagnino");
        String telefono = request.getParameter("numBagnino");
        String attestati = request.getParameter("attestati");

        /* dichiarazione e inizializzazione variabili per getsione database */
        int res = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {

            /* variabili di controllo */
            int minInput = 1;
            int maxInput = 50;
            int minTelefono = 9;
            int maxTelefono = 10;
            int minAttestato = 0;
            int maxAttestato = 250;

            /* Controlli sugli input */
            Utils.checkString(nome, minInput, maxInput);
            Utils.checkString(cognome, minInput, maxInput);
            Utils.checkString(email, minInput, maxInput);
            Utils.checkString(telefono, minTelefono, maxTelefono);
            Utils.checkString(attestati, minAttestato, maxAttestato);

            /* Inserimento banino nel database */
            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "INSERT INTO bagnino VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, cognome);
            stmt.setString(3, telefono);
            stmt.setString(4, email);
            stmt.setString(5, attestati);

            res = stmt.executeUpdate();

            /*Controllo del successo dell'inserimento*/
            if (res != 0) {

                String mexSuccesso = "Bagnino registrato con successo";
                    request.setAttribute("mexSuccesso", mexSuccesso);
                    request.getRequestDispatcher("Successo.jsp").forward(request, response);

            }

        } catch (InvalidParamException e) {

            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", "registrazione.jsp");
            request.getRequestDispatcher("error.jsp").forward(request, response);

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
