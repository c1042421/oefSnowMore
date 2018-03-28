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
        return getHotelsForSQLStatement("Select * from Hotel "
                + "inner join skigebied on hotel.skigebiedid = skigebied.id "
                + "where hotel.id = " + id).get(0);
    }

    public ArrayList<Hotel> getAllHotelsSorted() {
       return getHotelsForSQLStatement("Select * from Hotel "
                + "inner join skigebied on hotel.skigebiedid = skigebied.id "
                + "order by hotelnaam");
    }

    public ArrayList<Hotel> getHotelsForStars(int sterren) {
        return getHotelsForSQLStatement("Select * from Hotel "
                + "inner join skigebied on hotel.skigebiedid = skigebied.id "
                + "where aantalsterren <= " + sterren);
    }

    private ArrayList<Hotel> getHotelsForSQLStatement(String stringStatement) {
        ArrayList<Hotel> hotels = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(stringStatement);) {

            while (resultset.next()) {
                Hotel hotel = ResultSetParser.maakHotel(resultset);
                hotels.add(hotel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotels;
    }
}
