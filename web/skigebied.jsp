<%-- 
    Document   : skigebied
    Created on : 14-mrt-2018, 11:28:35
    Author     : c1042421
--%>

<%@page import="hbo5.it.www.beans.Skigebied"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Skigebied skigebied = (Skigebied) session.getAttribute("skigebied"); %>
        <% if (skigebied != null) { %>
        <h1>Skigebied: <%= skigebied.getSkigebied() %></h1>
        <%} else { %>
        <h1>Er is geen gebied gevonden..</h1>
        <%}%>
    </body>
</html>
