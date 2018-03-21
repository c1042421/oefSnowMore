<%-- 
    Document   : index
    Created on : 14-mrt-2018, 10:39:13
    Author     : c1042421
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Startscherm</title>
    </head>
    <body>
        <h1>OefSnowMore</h1>
        <h2>Door: Jelmar Van Aert</h2>
        
        <form action="ManageServlet">
            <p><input type="submit" name="skigebied" value="Toon specifiek skigebied"/>
                <input type="text" name="zoekSkigebied" placeholder="Geef de naam"/></p>
            <p><input type="submit" name="hotel" value="Toon specifiek hotel"/></p>
            <p><input type="submit" name="allHotels" value="Toon hotels"/></p>
            <p><input type="submit" name="aanbod" value="Toon Aanbod"/></p>
        </form>
    </body>
</html>
