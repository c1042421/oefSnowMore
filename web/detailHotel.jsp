<%-- 
    Document   : detailHotel
    Created on : 21-mrt-2018, 10:16:22
    Author     : c1042421
--%>

<%@page import="hbo5.it.www.beans.Hotel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Hotel hotel = (Hotel) session.getAttribute("detailHotel"); %>
        
        <h1><%= hotel.getHotelnaam() %> </h1> <h3><%= hotel.getAantalSterren() %></h3>
        <h5><%= hotel.getSkigebied().getSkigebied() %></h5>
        
        <p><%= hotel.getLigging() %></p>
        <p><%= hotel.getAccomodatie()%></p>
        <p><%= hotel.getKamers()%></p>
        <p><%= hotel.getMaaltijden()%></p>
        <p><%= hotel.getPistes()%></p>
        <img src="images/<%= hotel.getFoto()%>" alt="No foto found" /> 
        
    </body>
</html>
