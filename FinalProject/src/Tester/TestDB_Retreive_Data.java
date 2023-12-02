/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tester;

import java.sql.*;
import possystem.DBConfig;
/**
 *
 * @author Vothana CHY
 */
public class TestDB_Retreive_Data {
    public static void main(String args[]) {  
        try {  
            String query = "Select * From Customers";
            ResultSet result = DBConfig.query(query);
            //int count = DBConfig.countRows(query);
            int column_count = DBConfig.countColumn(query);

            while(result.next()){
                int i = 1;
                while (i <= column_count){
                    System.out.print(result.getString(i) + " | ");
                    i++;
                }
                System.out.println();
            }
            
            System.out.println();

            result.close();
            DBConfig.close();
        } catch (SQLException e) { 
            System.err.println(e.getMessage());
        }  
  }
}
