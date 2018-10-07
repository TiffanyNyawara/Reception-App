/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reeption;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author triad
 */
public class Reeption {
   Statement statement;
   ResultSet rsx;
   Connection conn = null;
   public Connection connect() throws SQLException {
        try {
            // SQLite connection string
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:ReceptionDB.sqlite";
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Reeption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
   
      
    public ResultSet loginData(){
        String sql = "SELECT * FROM login";
        try{
        Connection conns = this.connect();
        PreparedStatement stmt = conns.prepareStatement(sql);
        rsx = stmt.executeQuery();

        }catch(SQLException ex){

        }
            return rsx;
        }

    public static void main(String[] args){
        LoginForm xs = new LoginForm();   
    }
}
