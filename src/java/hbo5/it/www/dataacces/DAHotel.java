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

            if (resultset.next()) {
                hotel = ResultSetParser.maakHotel(resultset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotel;
    }

    public ArrayList<Hotel> getAllHotelsSorted() {
        ArrayList<Hotel> hotels = getHotelForSQLStatement("Select * from Hotel "
                + "inner join skigebied on hotel.skigebiedid = skigebied.id");

        hotels.sort(new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return o1.getHotelnaam().compareTo(o2.getHotelnaam());
            }
        });

        return hotels;
    }

    public ArrayList<Hotel> getHotelsForStars(int sterren) {
        return getHotelForSQLStatement("Select * from Hotel "
                + "inner join skigebied on hotel.skigebiedid = skigebied.id "
                + "where aantalsterren <= " + sterren);
    }

    private ArrayList<Hotel> getHotelForSQLStatement(String stringStatement) {
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
