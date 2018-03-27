<%-- 
    Document   : overzichtHotels
    Created on : 21-mrt-2018, 9:47:52
    Author     : c1042421
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="hbo5.it.www.beans.Aanbod"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% ArrayList<Aanbod> aanbidingen = (ArrayList<Aanbod>) session.getAttribute("aanbiedingen");%>
        <a href="index.jsp">Home</a>
        <h1>Lijst van hotels:</h1>
        
        <% for (Aanbod aanbod : aanbidingen) { %>
        <p><a href="DetailServlet?id=<%= aanbod.getId() %>"> <%= aanbod.getPeriode().getPeriode() %> <%= aanbod.getHotel().getHotelnaam() %>  <%= aanbod.getPrijs() %></a></p>
        <% } %>
    </body>
</html>
