/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataacces;

import hbo5.it.www.beans.Aanbod;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author c1042421
 */
public class DAAanbod {
    private final String url, login, password;

    public DAAanbod(String url, String login, String password, String driver) throws ClassNotFoundException {
        Class.forName(driver);
        this.url = url;
        this.login = login;
        this.password = password;
    }  
    
    public ArrayList<Aanbod> getAllAanbodSortedByPeriodAndName(){
         ArrayList<Aanbod> aanbiedingen = new ArrayList<>();
         
          try (
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("select * from aanbod inner join periode on periodeid = periode.id inner join hotel on hotelid = hotel.id inner join skigebied on skigebiedid = skigebied.id");) {

            while (resultset.next()){
                Aanbod aanbod = ResultSetParser.maakAanbod(resultset);
                aanbiedingen.add(aanbod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
          
          aanbiedingen.sort(new Comparator<Aanbod>() {
             @Override
             public int compare(Aanbod o1, Aanbod o2) {
                 return o1.getPeriode().getPeriode().compareTo(o2.getPeriode().getPeriode());
             }
          });
               
        return aanbiedingen;
    }
}
