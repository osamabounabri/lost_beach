/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.db.DatabaseManager;
import it.unica.lostbeach.exceptions.InvalidParamException;
import it.unica.lostbeach.model.Slot;
import it.unica.lostbeach.model.SlotFactory;
import it.unica.lostbeach.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "InserisciSlot", urlPatterns = {"/InserisciSlot"})
public class InserisciSlot extends HttpServlet {

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

        /*Reuper i dati dal client*/
        String data = request.getParameter("dataSlot");
        String orario = request.getParameter("orario");
        String posti = request.getParameter("posti");
        String bagnino = request.getParameter("selBagnino");

        /*dichiaro e inizializzo variabili per la connessione col database*/
        int res = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        int i;
        int aggiornato = 0;//variabile di controllo sull'esistenza e aggiornamento di uno slot uguale a quello inserito dall'amministratore

        try {

            /*variabili di controllo*/
            int minPosti = 1;
            int maxPosti = 50;

            Utils.checkInteger(posti, minPosti, maxPosti);//controllo sulla quantità di posti inseriti

            java.util.Date dataSlot = new SimpleDateFormat("yyyy-MM-dd").parse(data);//formattazione data

            List<Slot> allSlots = SlotFactory.getInstance().getAllSlots(); //recupero tutti gli slot dal database

            /*controllo se esiste gia uno slot con la stessa data e orario e in questo caso ne aggiorno i posti aggiungendo quelli nuovi*/
            for (i = 0; i < allSlots.size(); i++) {

                if (orario.equals(allSlots.get(i).getOrario()) && dataSlot.equals(allSlots.get(i).getData())) {

                    res = SlotFactory.getInstance().aggiornaSlot(allSlots.get(i), Integer.valueOf(posti));

                    if (res != 0) {

                        aggiornato = 1;
                        String mexSuccesso = "Slot Aggiornato con successo";
                    request.setAttribute("mexSuccesso", mexSuccesso);
                    request.getRequestDispatcher("Successo.jsp").forward(request, response);

                    } else {

                        throw new InvalidParamException("Qualcosa e' andato storto, Inserimento slot annullato");

                    }

                }

            }

            /*se lo slot non esisteva gia, ne inserisce uno nuovo con i suoi dati*/
            if (aggiornato != 1) {
                
                java.sql.Date sqlData = new java.sql.Date(dataSlot.getTime()); //formatto la data

                int postiInt = Integer.parseInt(posti);

                /*connessione al database e inserimento slot*/
                conn = DatabaseManager.getInstance().getDbConnection();

                String query = "INSERT INTO slot VALUES (default,?,?,?,?)";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, orario);
                stmt.setDate(2, sqlData);
                stmt.setInt(3, postiInt);
                stmt.setString(4, bagnino);

                res = stmt.executeUpdate();

                /*controlla che lo slot sia stato inserito e avvisa l'amministratore*/
                if (res != 0) {

                    String mexSuccesso = "Slot inserito con successo";
                    request.setAttribute("mexSuccesso", mexSuccesso);
                    request.getRequestDispatcher("Successo.jsp").forward(request, response);

                }

            }

        } catch (InvalidParamException e) {

            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", "registrazione.jsp");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        } catch (ParseException e) {

        } catch (SQLException e) {

            Logger.getLogger(SlotFactory.class.getName()).log(Level.SEVERE, null, e);

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
