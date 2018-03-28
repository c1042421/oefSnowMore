<%-- 
    Document   : admin
    Created on : 28-mrt-2018, 10:35:42
    Author     : c1042421
--%>

<%@page import="hbo5.it.www.beans.Periode"%>
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
        <h1>Admin</h1>

        <% ArrayList<Hotel> hotels = (ArrayList<Hotel>) session.getAttribute("hotels");%>
        <a href="index.jsp">Home</a>
        <h1>Lijst van hotels:</h1>

        <% for (Hotel hotel : hotels) {%>
        <p><%= hotel.getHotelnaam()%>  <%= hotel.getAantalSterren()%> <a href="AdminServlet?id=<%= hotel.getId()%>">+ aanbod</a></p>
        <% }%>
    </body>
</html>
