/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.servlet;

import it.unica.lostbeach.exceptions.InvalidParamException;
import it.unica.lostbeach.model.Utente;
import it.unica.lostbeach.model.UtenteFactory;
import it.unica.lostbeach.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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

        //recupero i parametri dal client di login
        HttpSession session = request.getSession(); //crea o recupera una sessione
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String message = "Benvenuto";

        try {

            //dichiaro e inizializzo le lunghezze degli input
            int minUser = 5;
            int maxUser = 20;
            int minPass = 5;
            int maxPass = 15;

            /*controlli sugli input del client*/
            Utils.checkString(user, minUser, maxUser);
            Utils.checkString(pass, minPass, maxPass);

            Utente utente = UtenteFactory.getInstance().getUtenteByUsernamePassword(user, pass);//recupero l'utente dal database

            /*controlli sull'utente*/
            if (utente == null) {

                throw new InvalidParamException("Username o password non validi");//se l'utente non è presente nel database lancia un'eccezione

            } else if (utente.isAmministratore()) {//controlla se l'utente è un amministratore

                setSession(user, 1800, session); //massimo intervallo di inattività prima della scadenza della sessione
                session.setAttribute("amministratore", utente.isAmministratore()); //attributo di sessione che indica se l'utente è un amministratore
                response.sendRedirect("amministratore"); //redirect alla servlet amministratore

            } else {

                /*setta gli attributi di sessione con i dati dell'utente*/
                session.setAttribute("user", utente.getUsername());
                session.setAttribute("nome", utente.getNome());
                session.setAttribute("cognome", utente.getCognome());
                session.setAttribute("dataNascita", utente.getDataNascita());
                session.setAttribute("sesso", utente.getSesso());
                session.setAttribute("cf", utente.getCodiceFiscale());
                session.setAttribute("telefono", utente.getTelefono());
                session.setAttribute("scelta_fattura", utente.getFattura());
                session.setAttribute("email", utente.getEmail());
                session.setAttribute("pass", utente.getPassword());
                session.setAttribute("amministratore", utente.isAmministratore());

                session.setAttribute("lastLogin", Utils.convertTime(session.getLastAccessedTime()));//ultimo accesso
                session.setMaxInactiveInterval(1800);//massimo intervallo di inattività prima della scadenza della sessione
                response.sendRedirect("user");

            }

            
        } catch (InvalidParamException e) {

            session.invalidate();
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", "login.jsp");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        }

    }

   
    /*Setta una sessione*/
    public void setSession(String attUser, int tempoMaxSessione, HttpSession session) {

        session.setAttribute("user", attUser);//imposta l'utente
        session.setAttribute("lastLogin", Utils.convertTime(session.getLastAccessedTime())); //imposto l'ultimo accesso
        session.setMaxInactiveInterval(tempoMaxSessione); //Tempo di inattività prima della scadenza della sessione

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
