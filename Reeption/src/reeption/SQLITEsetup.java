/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reeption;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author triad
 */
public class SQLITEsetup {
    String url = "jdbc:sqlite:ReceptionDB.sqlite";
      public  SQLITEsetup(){
               String sql = "SELECT * from clientdetails";
        
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            ResultSet rs  = pstmt.executeQuery();
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("firstname") + "\t" +
                                   rs.getString("lastname") + "\t" +
                                   rs.getString("phonenumber") + "\t" +
                                   rs.getString("dateofbirth") + "\t" +
                                   rs.getString("gender") + "\t" +
                                   rs.getInt("Age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public static void main(String[] args) {
       SQLITEsetup x = new SQLITEsetup();
    }
    
}
