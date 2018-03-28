/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataacces;

import hbo5.it.www.beans.Periode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author c1042421
 */
public class DAPeriode {

    private final String url, login, password;

    public DAPeriode(String url, String login, String password, String driver) throws ClassNotFoundException {
        Class.forName(driver);
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public ArrayList<Periode> getPeriodes() {
        ArrayList<Periode> periodes = new ArrayList<Periode>();

        try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("select * from periode");) {

            while (resultset.next()) {
                Periode aanbod = ResultSetParser.maakPeriode(resultset);
                periodes.add(aanbod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return periodes;
    }
    
    public Periode getPeriodeForID(int id) {
         try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("select * from periode where id = " + String.valueOf(id));) {

            if (resultset.next()) {
                return ResultSetParser.maakPeriode(resultset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
