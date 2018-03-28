/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www;

import hbo5.it.www.beans.Aanbod;
import hbo5.it.www.beans.Hotel;
import hbo5.it.www.beans.Periode;
import hbo5.it.www.dataacces.DAAanbod;
import hbo5.it.www.dataacces.DAPeriode;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/NieuwAanbodServlet"}, initParams = {
    @WebInitParam(name = "url", value = "jdbc:oracle:thin:@itf-oracledb01.thomasmore.be:1521:XE")
    , @WebInitParam(name = "driver", value = "oracle.jdbc.driver.OracleDriver")
    , @WebInitParam(name = "login", value = "c1042421")
    , @WebInitParam(name = "password", value = "1234")})

public class NieuwAanbodServlet extends HttpServlet {

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

        boolean annuleer = request.getParameter("annuleer") != null;
        boolean opslaan = request.getParameter("opslaan") != null;

        if (annuleer) {
            request.getRequestDispatcher("admin.jsp").forward(request, response);
            
        } else if (opslaan) {
            double prijs = Double.parseDouble(request.getParameter("prijs"));
            int periodeID = Integer.parseInt(request.getParameter("periode"));

            try {
                DAAanbod daAanbod = new DAAanbod(url, login, password, driver);
                DAPeriode daPeriode = new DAPeriode(url, login, password, driver);

                Aanbod aanbod = new Aanbod();
                Hotel hotel = (Hotel) session.getAttribute("adminHotel");
                Periode periode = daPeriode.getPeriodeForID(periodeID);

                aanbod.setHotel(hotel);
                aanbod.setPeriode(periode);
                aanbod.setPrijs(prijs);
                
                daAanbod.makeNewAanbod(aanbod);

                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
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
