/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.io.*;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;


public class TransactionsTable {
    
    JFrame tableFrame = new JFrame("Transactions Table");
    JTable transactionsTable = new JTable();
    JLabel headingLabel = new JLabel("Summary of transactions");
    JScrollPane transactionScrollPane = new JScrollPane(transactionsTable);
    
    JButton printSummaryBtn = new JButton("Save this summary");
    JButton removeRow = new JButton("Remove Selection");
    JButton closeBtn = new JButton("Close");
    //JCheckBox verifiedCheck = new JCheckBox("Data Verified");
    boolean selected;
    int counter;
    public TransactionsTable() {
       
        tableFrame.setLayout(null);
        tableFrame.getContentPane().setBackground(Color.WHITE);
        tableFrame.setSize(600,400);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.add(transactionScrollPane);
        tableFrame.add(headingLabel);
        tableFrame.add(printSummaryBtn);
        tableFrame.add(removeRow);
        //tableFrame.add(verifiedCheck);
        tableFrame.add(closeBtn);
        //tableFrame.setVisible(true);
        transactionsTable.setPreferredScrollableViewportSize(new Dimension(200,30));
        transactionsTable.setFillsViewportHeight(true);
        transactionsTable.setSize(300,100);
        
        transactionScrollPane.setBounds(2,50,580,240);
        headingLabel.setBounds(10,15,280,25);
        printSummaryBtn.setBounds(10,310,175,32);
        removeRow.setBounds(310,310,165,32);
        //verifiedCheck.setBounds(460,14,120,32);
        closeBtn.setBounds(480,310,100,32);
        java.util.Date today = new java.util.Date();
        
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transactionsTable.setFont(new Font("Work Sans", Font.PLAIN, 14));
        headingLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN, 22));
        printSummaryBtn.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        removeRow.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        //verifiedCheck.setFont(new Font("Work Sans", Font.PLAIN,15));
        closeBtn.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        
        printSummaryBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                printBtnActionPerformed(evt);
                
            }
        });
        
        removeRow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                removeRowBtnActionPerformed(evt);
            }
        });
        
        closeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                closeBtnActionPerformed(evt);
            }
        });
       
    }
   public void setTableModel(){
        transactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date of transaction", "Transaction description", "Transaction amount", "Transaction type"
            }
        ) {public boolean isCellEditable(int row,int column) {return false;}}
        );
    }
   
   public void printBtnActionPerformed(ActionEvent evt){
       try
      {     
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transaction",true));
            PrintWriter fileWriter = new PrintWriter(bufferedWriter);
               fileWriter.print("Transaction summary");
               fileWriter.write(System.getProperty("line.separator"));
            for(int i=0; i<transactionsTable.getModel().getRowCount(); ++i)
            {     
                  for(int j=0; j<transactionsTable.getModel().getColumnCount(); ++j)
                  {     
                        String s = transactionsTable.getModel().getValueAt(i,j).toString()+" | ";
                        fileWriter.print(s);
                  }
                  fileWriter.println("");
            }      
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Your transaction has been saved.","Success",JOptionPane.INFORMATION_MESSAGE);
      } catch(Exception e)
      {
            JOptionPane.showMessageDialog(null, "Your transaction could not be saved.","Failure",JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public void removeRowBtnActionPerformed(ActionEvent evt){
              DefaultTableModel model = (DefaultTableModel)transactionsTable.getModel(); 
              int toRemove = transactionsTable.getSelectedRow();
              
        try
        {
        
        Class.forName("java.sql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java project","root","arun2304");
        System.out.println("Connection done");
        if(toRemove>=0){
            String desc = transactionsTable.getModel().getValueAt(toRemove, 1).toString();
            model.removeRow(toRemove);
            
            String query = "delete from `java project`.`transactions` where `Transaction Description`=?;";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, desc);
            System.out.print(ps);
            ps.executeUpdate();

        ps.close();
        conn.close();
        }
       }  
    catch(SQLException | ClassNotFoundException e)
    {
        System.out.println("error");
        e.printStackTrace();
    }
   }
  public void closeBtnActionPerformed(ActionEvent evt){
      
      tableFrame.setVisible(false);
      
  }
  
    
}


