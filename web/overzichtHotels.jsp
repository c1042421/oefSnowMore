<%-- 
    Document   : overzichtHotels
    Created on : 21-mrt-2018, 9:47:52
    Author     : c1042421
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="hbo5.it.www.beans.Hotel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% ArrayList<Hotel> hotels = (ArrayList<Hotel>) session.getAttribute("hotels");%>
        <a href="index.jsp">Home</a>
        <h1>Lijst van hotels:</h1>
        
        <% for (Hotel hotel : hotels) { %>
            <p><%= hotel.getHotelnaam() %>  <%= hotel.getAantalSterren() %></p>
        <% } %>
    </body>
</html>
