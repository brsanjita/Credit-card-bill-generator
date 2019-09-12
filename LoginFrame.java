/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Arun Kashyap
 */
public class LoginFrame extends JFrame{
       JFrame loginFrame = new JFrame("Login");
       JFrame messageFrame = new JFrame();
       JPanel loginPanel = new JPanel();
       
       ImageIcon smallLogo = new ImageIcon("images/SBI_Crop.jpg");
       
       JLabel nameLabel = new JLabel("Username");
       JLabel passwordLabel = new JLabel("Password");
       JLabel logoLabel = new JLabel(smallLogo);
       JLabel instructionLabel1 = new JLabel("Login to your SBI online account");
       JLabel instructionLabel2 = new JLabel("to use the utility.");
       JTextField nameField = new JTextField();
       JPasswordField passField = new JPasswordField();
       
       JButton loginBtn = new JButton("Login");
       
       String[][] auth = {{"burgdorf2304","burgy101"},{"krebs1312","fishman101"},{"arun2304","arun1998"}};
       public LoginFrame() {
           
           loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           loginFrame.add(loginPanel);
           loginFrame.setLayout(null);
           loginPanel.setLayout(null);
           loginFrame.setSize(400,550);
           
           loginPanel.setBackground(new Color(40,0,112));
           
           loginPanel.setSize(400,550);
           
           loginPanel.add(nameLabel);
           loginPanel.add(passwordLabel);
           loginPanel.add(logoLabel);
           loginPanel.add(instructionLabel1);
           loginPanel.add(instructionLabel2);
           loginPanel.add(nameField);
           loginPanel.add(passField);
           loginPanel.add(loginBtn);
           
           logoLabel.setBounds(126,20,142,142);
           instructionLabel1.setBounds(39,182,322,25);
           instructionLabel2.setBounds(115,202,170,25);
           nameLabel.setBounds(70,272,150,25);
           passwordLabel.setBounds(70,342,150,25);
           nameField.setBounds(70,297,250,32);
           passField.setBounds(70,367,250,32);
           loginBtn.setBounds(160,420,80,30);
           
           nameLabel.setForeground(Color.white);
           passwordLabel.setForeground(Color.white);
           nameLabel.setForeground(Color.white);
           instructionLabel1.setForeground(Color.white);
           instructionLabel2.setForeground(Color.white);
           
           nameLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
           passwordLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
           instructionLabel1.setFont(new Font("Work Sans Semibold", Font.PLAIN,20));
           instructionLabel2.setFont(new Font("Work Sans Semibold", Font.PLAIN,20));
           nameField.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
           passField.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
           loginBtn.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
           
           loginFrame.setVisible(true);
           loginFrame.setResizable(false);
           loginFrame.setLocationRelativeTo(null);
           loginBtn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt){
                  loginBtnActionPerformed(evt); 
               }
           });
           
       }
       
       private void loginBtnActionPerformed(ActionEvent evt) {
          int i;
          
          for(i=0;i<3;i++){
              if(nameField.getText().equals(auth[i][0])) {
                   if(((new String(passField.getPassword())).equals(auth[i][1]))){
                        JOptionPane.showMessageDialog(messageFrame,"Login successful.","Success!",JOptionPane.INFORMATION_MESSAGE);
                        loginFrame.dispose();
                        MainClass ml = new MainClass();
                        ml.mainFrame.setVisible(true);
                        break;
                   }
                   else {
                        JOptionPane.showMessageDialog(messageFrame,"Login was not successful. Please recheck your username and password.","Login failed",JOptionPane.ERROR_MESSAGE);
                        break;
                    }
              }
              else if(i==2) {
                    JOptionPane.showMessageDialog(messageFrame,"Login was not successful. Please recheck your username and password.","Login failed",JOptionPane.ERROR_MESSAGE);
                    break;
              }
           }
      }
}