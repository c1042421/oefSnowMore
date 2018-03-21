/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataacces;

import hbo5.it.www.beans.Hotel;
import hbo5.it.www.beans.Skigebied;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author c1042421
 */
public class DAHotel {
    private final String url, login, password;

    public DAHotel(String url, String login, String password, String driver) throws ClassNotFoundException {
        Class.forName(driver);
        this.url = url;
        this.login = login;
        this.password = password;
    }   
    
    public Hotel getHotel() {
        Hotel hotel = null;

        try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("Select * from Hotel where id = 1");) {

            if (resultset.next()){
                hotel = new Hotel();
                hotel.setId(resultset.getInt("id"));
                hotel.setHotelnaam(resultset.getString("hotelnaam"));
                hotel.setAantalSterren(resultset.getDouble("aantalsterren"));
                hotel.setAccomodatie(resultset.getString("accomodatie"));
                hotel.setFoto(resultset.getString("foto"));
                hotel.setKamers(resultset.getString("kamers"));
                hotel.setLigging(resultset.getString("ligging"));
                hotel.setMaaltijden(resultset.getString("maaltijden"));
                hotel.setPistes(resultset.getString("pistes"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return hotel;
    }
}
