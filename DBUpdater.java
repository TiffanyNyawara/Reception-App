/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reeption;

/**
 *
 * @author triad
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class DBUpdater {
    ResultSet rsx;
    Reeption sqlite = new Reeption();
    //INSERT INTO DB
    public Boolean add(String fname, String lname, String pnumber, String birthdate,String gen, int a) {
        //SQL STATEMENT
        String sql = "INSERT INTO clientdetails(firstname,lastname,phonenumber,dateofbirth,gender,age) VALUES('" + fname + "','" + lname + "','" + pnumber +"','" + birthdate + "','" + gen + "','" +a + "')";

        try {
            //GET COONECTION
            Connection con = sqlite.connect();

            // PREPARED STMT
            PreparedStatement s = con.prepareStatement(sql);

            s.executeUpdate();

            return true;

        } catch (SQLException ex) {
        }
        return false;
    }

    //RETRIEVE DATA
    public DefaultTableModel getData() {
        //ADD COLUMNS TO TABLE MODEL
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("ID");
        dm.addColumn("FirstName");
        dm.addColumn("LastName");
        dm.addColumn("PhoneNumber");
        dm.addColumn("DateOfBirth");
        dm.addColumn("Gender");
        dm.addColumn("Age");

        //SQL STATEMENT
        String sql = "SELECT * FROM clientdetails";

        try {
            Connection con = sqlite.connect();

            //PREPARED STMT
            PreparedStatement s = con.prepareStatement(sql);
            ResultSet rs = s.executeQuery();

            //LOOP THRU GETTING ALL VALUES
            while (rs.next()) {
                //GET VALUES
                String id = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String pnumber = rs.getString(4);
                String birthdate = rs.getString(5);
                String gendersex = rs.getString(6);
                String ages = rs.getString(7);

                dm.addRow(new String[]{id, fname, lname, pnumber, birthdate, gendersex, ages});
            }

            return dm;

        } catch (SQLException ex) {
        }

        return null;

    }

    //UPDATE DATA
    public Boolean update(String id, String fname, String lname, String pnumber, String birthdate,String gen, int a) {
        //SQL STMT
        String sql = "UPDATE clientdetails SET FirstName ='" + fname + "',LastName='" + lname + "',PhoneNumber='" + pnumber +"',DateOfBirth ='"+ birthdate + "',Gender='"+gen+"',Age='"+a+ "' WHERE ID='" + id + "'";

        try {
            //GET COONECTION
            Connection con = sqlite.connect();

            //STATEMENT
            PreparedStatement s = con.prepareStatement(sql);

            //EXECUTE
            s.executeUpdate();

            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

    //DELETE DATA
    public Boolean delete(String id)
    {
        //SQL STMT
        String sql="DELETE FROM clientdetails WHERE ID ='"+id+"'";

        try
        {
            //GET COONECTION
            Connection con=sqlite.connect();

            //STATEMENT
            PreparedStatement s=con.prepareStatement(sql);

            //EXECUTE
            s.executeUpdate();

            return true;

        }catch(SQLException ex)
        {
            return false;
        }
    }
    public ResultSet chartData(){
        String sql = "SELECT Gender, Age FROM clientdetails";
        PreparedStatement statement;
    try{
    Connection conn = sqlite.connect();
    statement = conn.prepareStatement(sql);
    rsx = statement.executeQuery();
    
    }catch(SQLException ex){
        
    }
        return rsx;
    }


}