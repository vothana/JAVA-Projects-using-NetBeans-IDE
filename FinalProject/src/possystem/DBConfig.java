/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possystem;

import java.sql.*;
import possystem.Fucntions;
/**
 *
 * @author Vothana CHY
 */
public class DBConfig {
    private static Connection con;
    private static Fucntions fucntion = new Fucntions();
    //Connect to MYSQL database
    private static void connect() {
        final String DB_URL = "jdbc:mysql://localhost:3306/possystem";
        final String DB_USER = "root";
        final String DB_PASS = ""; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);  
        }catch(ClassNotFoundException | SQLException ex) {
            fucntion.printSQLException((SQLException) ex);
        }
    }
    
    public static Connection getConnection(){
        Connection connection;
        connection = con;
        return connection;
    }

    //Query Data
    public static ResultSet query(String q) {
        try {
            connect();

            Statement stmt = con.createStatement(); 
            ResultSet rs = stmt.executeQuery(q); 

            return rs;
        }  catch (SQLException ex) {
            fucntion.printSQLException((SQLException) ex);
            close();
            return null;
        }  
    }
    
    //Execute Query
    public static void executeQueryUpdate(String query){
        try {
            connect();
            Statement st = con.createStatement();
                      st.executeUpdate(query);
        } catch (SQLException ex) {
            fucntion.printSQLException((SQLException) ex);
        }
    }
    
    //get largest id for increment 
    public static int getMaxId(String query){
        int max = 0 ;
        try {
            connect();
            Statement st = con.createStatement();
            ResultSet r  = st.executeQuery(query);
            r.next();
            max = r.getInt(1);

            return max;
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return max;
        }  
    }
    
    
    
    //count rows of a table
    public static int countRows(String q){
        try {
            ResultSet rs = query(q);
            int count = 0;      
            while (rs.next()){
                count = rs.getInt(1);
            }
            rs.close();
            return count;
        } catch (SQLException ex) {
           fucntion.printSQLException((SQLException) ex);
            return 0;
        } 
    }
    
    //count column of a record
    public static int countColumn(String q){
        try {
            ResultSet rs = query(q);
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            rs.close();
            return column_count;
        } catch (SQLException ex) {
            fucntion.printSQLException((SQLException) ex);
            return 0;
        } 
    }
    
    //Close Connection
    public static void close() {
        try {
            if (!con.isClosed()) {
                con.close();
            }
        }catch(SQLException ex) {
            fucntion.printSQLException((SQLException) ex);
        }
    }
}
