package it.unica.lostbeach.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import it.unica.lostbeach.db.DatabaseManager;
import it.unica.lostbeach.exceptions.InvalidParamException;
import it.unica.lostbeach.model.MexFactory;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author fpw
 */
@WebServlet(urlPatterns = {"/Messaggio"})
public class Messaggio extends HttpServlet {

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

        HttpSession session = request.getSession(false); //recupero la sessione o
        String messaggio = request.getParameter("messaggio");//recuper il messaggio dalla jsp

        /*Dichiaro  e inizializzo variabili per la connsessione col database*/
        int res = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {

            /*Variabili di controllo*/
            int minMex = 0;
            int maxMex = 250;

            Utils.checkString(messaggio, minMex, maxMex);//controllo sulla lunghezza del messaggio

            /*Recupera il messaggio e l'autore dello stesso dal database*/
            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "INSERT INTO messaggio VALUES (?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, session.getAttribute("user").toString());
            stmt.setString(2, messaggio);

            res = stmt.executeUpdate();

            /*Controllo sul successo dell'interrogazione al database*/
            if (res != 0) {

                String mexSuccesso = "Messaggio inviato con successo";
                    request.setAttribute("mexSuccesso", mexSuccesso);
                    request.getRequestDispatcher("Successo.jsp").forward(request, response);

            }

        } catch (InvalidParamException e) {

            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", "messaggistica.jsp");
            request.getRequestDispatcher("error.jsp").forward(request, response);

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
