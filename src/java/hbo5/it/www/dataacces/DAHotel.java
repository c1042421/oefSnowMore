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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

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
    
    public Hotel getHotel(String id) {
        Hotel hotel = null;

        try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("Select * from Hotel inner join skigebied on hotel.skigebiedid = skigebied.id where hotel.id = " + id)) {

            if (resultset.next()){
                hotel = maakHotelVanResultSet(resultset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return hotel;
    }
    public ArrayList<Hotel> getAllHotelsSorted() {
        ArrayList<Hotel> hotels = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("Select * from Hotel inner join skigebied on hotel.skigebiedid = skigebied.id");) {

            while (resultset.next()){
                Hotel hotel = maakHotelVanResultSet(resultset);
                hotels.add(hotel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        hotels.sort(new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
               return o1.getHotelnaam().compareTo(o2.getHotelnaam());
            }
        });
        
        return hotels;
    }
    
    private Hotel maakHotelVanResultSet(ResultSet resultset) throws SQLException {
        Hotel hotel = new Hotel();
        Skigebied skigebied = new Skigebied();

        skigebied.setId(resultset.getInt("skigebiedid"));
        skigebied.setSkigebied(resultset.getString("skigebied"));

        hotel.setSkigebied(skigebied);
        hotel.setId(resultset.getInt("id"));
        hotel.setHotelnaam(resultset.getString("hotelnaam"));
        hotel.setAantalSterren(resultset.getDouble("aantalsterren"));
        hotel.setAccomodatie(resultset.getString("accommodatie"));
        hotel.setFoto(resultset.getString("foto"));
        hotel.setKamers(resultset.getString("kamers"));
        hotel.setLigging(resultset.getString("ligging"));
        hotel.setMaaltijden(resultset.getString("maaltijden"));
        hotel.setPistes(resultset.getString("pistes"));

        return hotel;
    }
}
