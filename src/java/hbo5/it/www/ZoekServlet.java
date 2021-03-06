/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www;

import hbo5.it.www.beans.Aanbod;
import hbo5.it.www.beans.Hotel;
import hbo5.it.www.dataacces.DAAanbod;
import hbo5.it.www.dataacces.DAHotel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author c1042421
 */
@WebServlet(urlPatterns = {"/ZoekServlet"}, initParams = {
    @WebInitParam(name = "url", value = "jdbc:oracle:thin:@itf-oracledb01.thomasmore.be:1521:XE")
    , @WebInitParam(name = "driver", value = "oracle.jdbc.driver.OracleDriver")
    , @WebInitParam(name = "login", value = "c1042421")
    , @WebInitParam(name = "password", value = "1234")})
public class ZoekServlet extends HttpServlet {

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

        HttpSession session = request.getSession();

        String url = getInitParameter("url");
        String login = getInitParameter("login");
        String password = getInitParameter("password");
        String driver = getInitParameter("driver");

        int aantalSterren = Integer.parseInt(request.getParameter("sterren"));
        String stukHotelNaam = request.getParameter("hotelNaam");
        int maxPrijs = Integer.parseInt(request.getParameter("prijs"));

        boolean zoekSterren = request.getParameter("zoekSterren") != null;
        boolean zoekNaam = request.getParameter("zoekNaam") != null;
        boolean zoekAanbod = request.getParameter("zoekAanbod") != null;

        try {
            if (zoekSterren) {
                DAHotel daHotel = new DAHotel(url, login, password, driver);
                ArrayList<Hotel> hotels = daHotel.getHotelsForStars(aantalSterren);

                session.setAttribute("hotels", hotels);
                request.getRequestDispatcher("overzichtHotels.jsp").forward(request, response);
                
            } else if (zoekNaam) {
                DAHotel daHotel = new DAHotel(url, login, password, driver);
                ArrayList<Hotel> hotels = daHotel.getHotelsWithName(stukHotelNaam);

                session.setAttribute("hotels", hotels);
                request.getRequestDispatcher("overzichtHotels.jsp").forward(request, response);
                
            } else if (zoekAanbod) {
                DAAanbod daAanbod = new DAAanbod(url, login, password, driver);
                ArrayList<Aanbod> aanbiedingen = daAanbod.getAanbodForMaxPrijs(maxPrijs);
                
                session.setAttribute("aanbiedingen", aanbiedingen);
                request.getRequestDispatcher("overzichtAanbiedingen.jsp").forward(request, response);
            }

            

        } catch (Exception e) {
            e.printStackTrace();
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
