<%-- 
    Document   : nieuweReis
    Created on : 28-mrt-2018, 10:52:26
    Author     : c1042421
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="hbo5.it.www.beans.Periode"%>
<%@page import="hbo5.it.www.beans.Hotel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Hotel hotel = (Hotel) session.getAttribute("adminHotel");
            ArrayList<Periode> periodes = (ArrayList<Periode>) session.getAttribute("periodes");%>
        <h1>Maak aanbod voor : <%= hotel.getHotelnaam()%> </h1>

        <form action="NieuwAanbodServlet">
            <p><label>Kies een periode: </label>
                <select name="periode">
                    <% for (Periode periode : periodes) {%>
                    <option value="<%= periode.getId()%>">
                        <%= periode.getPeriode()%>
                    </option>
                    <% }%>
                </select>
            </p>

            <p><label>Kies een prijs:</label>
                <input type="text" name="prijs" placeholder="vb. 600"/></p>
            
            <p><input type="submit" value="Opslaan" name="opslaan"> <input type="submit" value="Annuleer" name="annuleer"></p>
        </form>
    </body>
</html>
