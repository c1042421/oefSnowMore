<%-- 
    Document   : zoek
    Created on : 27-mrt-2018, 14:58:12
    Author     : c1042421
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zoeken</title>
    </head>
    <body>
        <p><a href="index.jsp">Home</a></p>   
        <form action="ZoekServlet">
            <p><select name="sterren">
                    <% for (int i = 2; i < 6; i++) {%>
                    <option value="<%= i%>"><%= i%> sterren</option>
                    <% }%>
                </select>
                <input type="submit" value="Zoek!" name="zoekSterren" /></p>

            <p><input type="text" name="hotelNaam" placeholder="Zoek op naam hotel" />
                <input type="submit" name="zoekNaam" value="Zoek Hotel"/></p>

            <p><input type="text" name="hotelNaam" placeholder="Zoek op prijs" />
                <input type="submit" name="zoekNaam" value="Zoek Hotel"/></p>

            <p><select name="prijs">
                    <% for (int i = 615; i < 1300; i += 100) {%>
                    <option value="<%= i%>"> €<%= i%></option>
                    <% }%>
                </select>
                <input type="submit" value="Zoek!" name="zoekAanbod" /></p>

        </form>
    </body>
</html>
