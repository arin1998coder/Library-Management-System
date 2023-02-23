/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author arin1
 */
public class DBConnection {
    static Connection con=null;
    //whenever we want to communicate with the db, we will call this method and
    //this will establish a link between our java stc code and the db and 
    //provide us the connection obj
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver"); //loads the driver classes
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
