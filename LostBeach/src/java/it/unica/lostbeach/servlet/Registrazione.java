/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.db.DatabaseManager;
import it.unica.lostbeach.exceptions.InvalidParamException;
import it.unica.lostbeach.model.Utente;
import it.unica.lostbeach.model.UtenteFactory;
import it.unica.lostbeach.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
@WebServlet(name = "Registrazione", urlPatterns = {"/Registrazione"})
public class Registrazione extends HttpServlet {

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

        /* recuper i dati di registrazione dalla jsp*/
        String nome = request.getParameter("nome").toLowerCase();
        String cognome = request.getParameter("cognome").toLowerCase();
        String dataDiNascita = request.getParameter("dataNascita").toString();
        String codiceFiscale = request.getParameter("cf");
        String sesso = request.getParameter("sesso");
        String email = request.getParameter("e-mail");
        String telefono = request.getParameter("num");
        String username = request.getParameter("regUsername");
        String password = request.getParameter("regPassword"); 
        String confermaPassword = request.getParameter("confPassword");
        String scelta_fattura = request.getParameter("fatturaSiNo");

        /*dichiarazione e inizializzazione variabili per la connsessione col datbase*/
        int res = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {

            /*Variabili di controllo*/
            int minUser = 5;
            int maxUser = 20;
            int minPass = 5;
            int maxPass = 15;
            int minCf = 16;
            int minInput = 1;
            int maxInput = 50;
            int minTelefono = 9;
            int maxTelefono = 10;

            /*Controlli sugli input*/
            Utils.checkString(nome, minInput, maxInput);
            Utils.checkString(cognome, minInput, maxInput);
            Utils.checkString(codiceFiscale, minCf, minCf);
            Utils.checkString(email, minInput, maxInput);
            Utils.checkString(telefono, minTelefono, maxTelefono);
            Utils.checkString(username, minUser, maxUser);
            Utils.checkString(password, minPass, maxPass);

            if (!(password.equals(confermaPassword))) {

                throw new InvalidParamException("Le due password non coincidono" + password + confermaPassword);

            }

            if (scelta_fattura == null) {

                throw new InvalidParamException("Indicare se si desidera la fattura");

            }

            if (UtenteFactory.getInstance().getUtenteByUsername(username) != null) {

                throw new InvalidParamException("Username già resistrato, scegline un altro");

            }

            /*Formattazione data*/
            java.util.Date nascita = new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascita.toString());

            java.sql.Date sqlNascita = new java.sql.Date(nascita.getTime());

            /* inserimento dati nel database*/
            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "INSERT INTO utente VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, nome);
            stmt.setString(4, cognome);
            stmt.setDate(5, sqlNascita);
            stmt.setString(6, sesso);
            stmt.setString(7, email);
            stmt.setString(8, scelta_fattura);
            stmt.setString(9, codiceFiscale);
            stmt.setString(10, telefono);
            stmt.setBoolean(11, false);

            res = stmt.executeUpdate();

            /*Controllo sul successo dell'inetrrogazione al datbase*/
            if (res != 0) {

                String mexSuccesso = "Registrazione avvenuta con successo";
                    request.setAttribute("mexSuccesso", mexSuccesso);
                    request.getRequestDispatcher("Successo.jsp").forward(request, response);

            }

        } catch (InvalidParamException e) {

            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", "registrazione.jsp");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        } catch (ParseException e) {

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
