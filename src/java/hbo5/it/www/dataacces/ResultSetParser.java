/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataacces;

import hbo5.it.www.beans.Aanbod;
import hbo5.it.www.beans.Hotel;
import hbo5.it.www.beans.Periode;
import hbo5.it.www.beans.Skigebied;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author c1042421
 */
public class ResultSetParser {

    static public Hotel maakHotel(ResultSet resultset) throws SQLException {
        Hotel hotel = new Hotel();
        Skigebied skigebied = maakSkigebied(resultset);

        hotel.setSkigebied(skigebied);
        int id = checkIfIdExistsAndReturn("hotelid", resultset);

        hotel.setId(id);
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

    static public Aanbod maakAanbod(ResultSet resultset) throws SQLException {
        Hotel hotel = maakHotel(resultset);
        Periode periode = maakPeriode(resultset);

        Aanbod aanbod = new Aanbod();
        aanbod.setHotel(hotel);
        aanbod.setPeriode(periode);
        aanbod.setId(resultset.getInt("id"));
        aanbod.setPrijs(resultset.getDouble("prijs"));

        return aanbod;
    }

    static public Skigebied maakSkigebied(ResultSet resultset) throws SQLException {
        Skigebied skigebied = new Skigebied();

        int id = checkIfIdExistsAndReturn("skigebiedid", resultset);
        skigebied.setId(id);
        skigebied.setSkigebied(resultset.getString("skigebied"));

        return skigebied;
    }

    static public Periode maakPeriode(ResultSet resultset) throws SQLException {
        Periode periode = new Periode();

        int id = checkIfIdExistsAndReturn("periodeid", resultset);
        periode.setId(id);
        periode.setPeriode(resultset.getString("periode"));

        return periode;
    }

    private static int checkIfIdExistsAndReturn(String columnname, ResultSet resultset) throws SQLException {
        int id;
        try {
            id = resultset.getInt(columnname);
        } catch (Exception e) {
            id = resultset.getInt("id");
        }
        return id;
    }

}
