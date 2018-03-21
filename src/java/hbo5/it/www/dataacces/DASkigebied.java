/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataacces;

import hbo5.it.www.beans.Skigebied;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author c1042421
 */
public class DASkigebied {

    private final String url, login, password;

    public DASkigebied(String url, String login, String password, String driver) throws ClassNotFoundException {
        Class.forName(driver);
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public Skigebied getSkigebied() {
        Skigebied skigebied = null;

        try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("Select * from Skigebied where id = 1");) {

            if (resultset.next()){
                skigebied = new Skigebied();
                skigebied.setId(resultset.getInt("id"));
                skigebied.setSkigebied(resultset.getString("skigebied"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return skigebied;
    }
    
    public Skigebied zoekSkigebiedOpNaam(String naam){
        
         try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("Select * from Skigebied");) {

            while (resultset.next()){
                
                String skigebiedNaam = resultset.getString("skigebied").toLowerCase();
                
                if (skigebiedNaam.contains(naam.toLowerCase())){
                    Skigebied skigebied = new Skigebied();
                    skigebied.setId(resultset.getInt("id"));
                    skigebied.setSkigebied(resultset.getString("skigebied"));
                    
                    return skigebied;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return null;  
    }
}
