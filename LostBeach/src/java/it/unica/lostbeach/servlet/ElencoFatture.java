/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.model.Fattura;
import it.unica.lostbeach.model.FatturaFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ElencoFatture", urlPatterns = {"/ElencoFatture"})
public class ElencoFatture extends HttpServlet {

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

        HttpSession session = request.getSession(false);//recupera o crea una sesssione

        String username = (String) session.getAttribute("user"); //recupero l'attributo di sessione dell'username
        List<Fattura> allFatture = FatturaFactory.getInstance().getAllFatture(); //recupera tutte le fatture dal database
        int i;

        List<Fattura> fatturaUtente = new ArrayList<>(); //crea una nuova lista di fatture per ora vuota

        /*controllo che la fattura sia associata all'utente in sessione e la aggiungo alla nuova lista di fatture*/
        for (i = 0; i < allFatture.size(); i++) {

            //controllo username e metti nella nuova lista
            if (allFatture.get(i).getUtente_id().equals(username)) {

                fatturaUtente.add(allFatture.get(i));

            }

        }

        //setto una variabile con la lista ora contente solo le fatture dell'utente in sessione
        request.setAttribute("listaDiFatture", fatturaUtente); //attenzione, modificare

        request.getRequestDispatcher("fatturaUtente.jsp").forward(request, response);

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
