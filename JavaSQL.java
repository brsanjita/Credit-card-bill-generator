/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import entity.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Arun Kashyap
 */
//Transactions inst = new Transactions();
public class JavaSQL {
    
    TransactionsTable inst = new TransactionsTable();
   
       public void viewTable()
        {
          try
        {
        //inst.setTableModel();
        DefaultTableModel model = (DefaultTableModel)inst.transactionsTable.getModel(); 
        Class.forName("java.sql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java project","root","arun2304");
        System.out.println("Connection done");
        Statement stat = conn.createStatement();
        String Query = "select * from transactions;";
        ResultSet rs = stat.executeQuery(Query);
        
        System.out.println("Connection done");
        while(rs.next())
        {  
           java.sql.Date d = rs.getDate("Date of transaction");
           String de = rs.getString("Transaction description");
           double a = rs.getDouble("Transaction amount");
           String t = rs.getString("Transaction type");
           model.addRow(new Object[]{d,de,a,t});
           
        }

        rs.close();
        stat.close();
        conn.close();
    }
        
    catch(SQLException | ClassNotFoundException e)
    {
        System.out.println("error");
        e.printStackTrace();
    }
}
       
}
