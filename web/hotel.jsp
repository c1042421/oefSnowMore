<%-- 
    Document   : hotel
    Created on : 21-mrt-2018, 9:13:46
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
        <% Hotel hotel = (Hotel) session.getAttribute("hotel"); %>
        <h1>Hotel: <%= hotel.getHotelnaam() %></h1>
    </body>
</html>
