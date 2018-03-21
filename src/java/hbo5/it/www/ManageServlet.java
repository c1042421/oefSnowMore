/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hbo5.it.www;

import hbo5.it.www.beans.Aanbod;
import hbo5.it.www.beans.Hotel;
import hbo5.it.www.beans.Skigebied;
import hbo5.it.www.dataacces.DAAanbod;
import hbo5.it.www.dataacces.DAHotel;
import hbo5.it.www.dataacces.DASkigebied;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author c1042421
 */
@WebServlet(urlPatterns = {"/ManageServlet"}, initParams = {
    @WebInitParam(name = "url", value = "jdbc:oracle:thin:@itf-oracledb01.thomasmore.be:1521:XE")
    , @WebInitParam(name = "driver", value = "oracle.jdbc.driver.OracleDriver")
    , @WebInitParam(name = "login", value = "c1042421")
    , @WebInitParam(name = "password", value = "1234")})

public class ManageServlet extends HttpServlet {

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
        
        boolean laadPaginaVoorHotel = request.getParameter("hotel") != null;
        boolean laadPaginaVoorSkiGebied = request.getParameter("skigebied") != null;
        boolean laadPaginaAlleHotels = request.getParameter("allHotels") != null;
        boolean laadPaginaVoorAanbod = request.getParameter("aanbod") != null;
        String teZoekenGebied = request.getParameter("zoekSkigebied");

        try {

            if (laadPaginaVoorSkiGebied) {
                DASkigebied daSkigebied = new DASkigebied(url, login, password, driver);
                Skigebied skigebied = daSkigebied.zoekSkigebiedOpNaam(teZoekenGebied);
                
                session.setAttribute("skigebied", skigebied);
                request.getRequestDispatcher("skigebied.jsp").forward(request, response);
                
            }else if(laadPaginaVoorHotel) {
                DAHotel daHotel = new DAHotel(url, login, password, driver);
                Hotel hotel = daHotel.getHotel("1");
                
                session.setAttribute("hotel", hotel);
                request.getRequestDispatcher("hotel.jsp").forward(request, response);
                
            } else if(laadPaginaAlleHotels) {
                DAHotel daHotel = new DAHotel(url, login, password, driver);
                ArrayList<Hotel> hotels = daHotel.getAllHotelsSorted();
                
                session.setAttribute("hotels", hotels);
                request.getRequestDispatcher("overzichtHotels.jsp").forward(request, response);
               
            }else if (laadPaginaVoorAanbod) {
                 DAAanbod daAanbod = new DAAanbod(url, login, password, driver);
                ArrayList<Aanbod> hotels = daAanbod.getAllAanbodSortedByPeriodAndName();
                
                session.setAttribute("hotels", hotels);
                request.getRequestDispatcher("overzichtHotels.jsp").forward(request, response);
                
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
