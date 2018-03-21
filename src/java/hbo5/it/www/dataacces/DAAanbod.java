/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataacces;

import hbo5.it.www.beans.Aanbod;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

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
         ArrayList<Aanbod> aanboden = new ArrayList<>();
         
         return aanboden;
    }
    
}
