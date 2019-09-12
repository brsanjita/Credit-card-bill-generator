/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*; 
import javax.swing.JFormattedTextField.*;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.*;
import javax.swing.text.*;

public class MainClass{
   
    public static void main(String[] args){
       
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
          
        }
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }
    
    ImageIcon icon = new ImageIcon("images/SBI_New_Logo.jpg");
    
    JFrame mainFrame = new JFrame("SBI Credit Card Bill Generator");
    JFrame emptyFieldsError = new JFrame();
    
    JPanel mainPanel = new JPanel();
    JPanel customerIDPanel = new JPanel();
    JPanel transactionPanel = new JPanel();
    JPanel tablePanel = new JPanel();
    JPanel billGenerationPanel = new JPanel();
    
    JLabel logoLabel = new JLabel(icon);
    JLabel welcomeLabel1 = new JLabel("Welcome to");
    JLabel welcomeLabel2 = new JLabel("the SBI Credit Card Bill Generator");
    JLabel instructionLabel1 = new JLabel("Please enter your credit card credentials.");
    JLabel instructionLabel2 = new JLabel("Please enter the details of your transaction(s)");
    JLabel instructionLabel3 = new JLabel("Your transaction details are added to a summarised table.");
    JLabel instructionLabel4 = new JLabel("To view it, please click on the \"View transactions\" button.");
    JLabel instructionLabel5 = new JLabel("Before proceeding to bill generation, please verify");
    JLabel instructionLabel6 = new JLabel("your details and check the \"Verified\" checkbox");
    JLabel tableLabel = new JLabel("Table of transactions");
    JLabel nameLabel = new JLabel("Name on credit card: ");
    JLabel cardNumberLabel = new JLabel("Credit card number: ");
    JLabel cvvLabel = new JLabel("CVV: ");
    JLabel expiryLabel = new JLabel("Expiry date: ");
    JLabel dateLabel = new JLabel("Date of transaction: ");
    JLabel transactionDescLabel = new JLabel("Description of transaction: ");
    JLabel transactionAmountLabel = new JLabel("Transaction amount: ");
    JLabel transactionTypeLabel = new JLabel("Transaction type: ");
    JLabel titleLabel = new JLabel("Bill details");
    JLabel informationLabel1 = new JLabel("Your outstanding amount is: ");
    JLabel outstandingAmountLabel = new JLabel();
    JLabel informationLabel2 = new JLabel("The interest calculated is: ");
    JLabel interestLabel = new JLabel();
    JLabel informationLabel3 = new JLabel("The total bill for given transactions is: ");
    JLabel billLabel = new JLabel();
    JLabel exitLabel = new JLabel("Thank you for using the utility.");
    JLabel instructionLabel7 = new JLabel("You may please log out now.");
    
    JTextField nameField = new JTextField();
    JTextField cardNumberField = new JTextField();
    JTextField cvvField = new JFormattedTextField();
    JTextField transactionDescField = new JTextField();
    JTextField transactionAmountField = new JTextField();
    
    PlainDocument doc = (PlainDocument) cardNumberField.getDocument();
    PlainDocument doc1 = (PlainDocument) cvvField.getDocument();
    
    JComboBox monthCombo;
    JComboBox yearCombo;
    JComboBox transactionTypeCombo;
        
    JButton nextBtn1 = new JButton("Next"); 
    JButton backBtn1 = new JButton("Back");
    JButton backBtn2 = new JButton("Back");
    JButton generateBillBtn = new JButton("Generate Bill");
    JButton addTransactionBtn = new JButton("Add transaction");
    JButton viewTransactionsBtn = new JButton("View transactions");
    JButton logoutBtn = new JButton("Logout");
   
    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    String st;
    int rowCounter=0;
    double outstandingAmount;
    JCheckBox verifiedCheck = new JCheckBox("Data Verified");
    public MainClass(){
        
        
        String typeList[] = {"Debit","Credit"};
        String monthList[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String yearList[] = new String[21];
        for(int i=0,j=2017;i<21;i++)
            yearList[i]=Integer.toString(j++);
        
        monthCombo = new JComboBox(monthList);
        yearCombo = new JComboBox(yearList);
        transactionTypeCombo = new JComboBox(typeList);
        
        mainFrame.add(mainPanel);
        mainPanel.add(customerIDPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel.setLayout(null);
        customerIDPanel.setLayout(null);
        transactionPanel.setLayout(null);
        tablePanel.setLayout(null);
        billGenerationPanel.setLayout(null);
        
        mainPanel.setSize(500,500);
        customerIDPanel.setSize(600,600);
        transactionPanel.setSize(600,600);
        billGenerationPanel.setSize(600,600);
        
        /* CUSTOMER ID PANEL CONFIGURATION */
        customerIDPanel.setBackground(white);
        customerIDPanel.add(nextBtn1);
        customerIDPanel.add(logoLabel);
        customerIDPanel.add(welcomeLabel1);
        customerIDPanel.add(welcomeLabel2);
        customerIDPanel.add(instructionLabel1);
        customerIDPanel.add(nameLabel);
        customerIDPanel.add(cardNumberLabel);
        customerIDPanel.add(cvvLabel);
        customerIDPanel.add(expiryLabel);
        customerIDPanel.add(nameField);
        customerIDPanel.add(cvvField);
        customerIDPanel.add(cardNumberField);
        customerIDPanel.add(monthCombo);
        customerIDPanel.add(yearCombo);
        
        nextBtn1.setBounds(265,495,70,30);
        logoLabel.setBounds(90,35,390,138);
        welcomeLabel1.setBounds(224,220,152,22);
        welcomeLabel2.setBounds(74,250,453,25);
        instructionLabel1.setBounds(89,300,422,25);
        nameLabel.setBounds(120,355,200,25);
        nameField.setBounds(280,350,200,32);
        cardNumberLabel.setBounds(120,395,200,25);
        cardNumberField.setBounds(280,390,200,32);
        cvvLabel.setBounds(120,435, 40, 25);
        cvvField.setBounds(160, 430, 50,30);
        monthCombo.setBounds(340,430,60,30);
        yearCombo.setBounds(410,430,70,30);
        expiryLabel.setBounds(240, 430, 100,30);
        
        doc.setDocumentFilter(new MyIntFilter());
        doc1.setDocumentFilter(new MyIntFilter());
         
        welcomeLabel1.setFont(new Font("Work Sans SemiBold", Font.PLAIN,26));
        welcomeLabel2.setFont(new Font("Work Sans SemiBold", Font.PLAIN,27));
        instructionLabel1.setFont(new Font("Work Sans Medium", Font.BOLD,20));
        nameLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        cardNumberLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        cvvLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        expiryLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        nameField.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        cardNumberField.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        cvvField.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        monthCombo.setFont(new Font("Work Sans", Font.PLAIN,15));
        yearCombo.setFont(new Font("Work Sans", Font.PLAIN,15));
        nextBtn1.setFont(new Font("Work Sans", Font.PLAIN,15));
      
        /* TRANSACTION PANEL CONFIGURATION */
        transactionPanel.setBackground(white);
        transactionPanel.add(instructionLabel2);
        transactionPanel.add(transactionDescLabel);
        transactionPanel.add(transactionDescField);
        transactionPanel.add(dateLabel);
        transactionPanel.add(transactionAmountLabel);
        transactionPanel.add(transactionAmountField);
        transactionPanel.add(transactionTypeLabel);
        transactionPanel.add(transactionTypeCombo);
        transactionPanel.add(instructionLabel3);
        transactionPanel.add(instructionLabel4);
        transactionPanel.add(instructionLabel5);
        transactionPanel.add(instructionLabel6);
        transactionPanel.add(backBtn1);
        transactionPanel.add(addTransactionBtn);
        transactionPanel.add(generateBillBtn);
        transactionPanel.add(viewTransactionsBtn);
        transactionPanel.add(verifiedCheck);
        
        //DATE PICKER
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePicker.setBackground(white);
        transactionPanel.add(datePicker);
        
        backBtn1.setBounds(205,495,70,30);
        datePicker.setBounds(280,115,230,32);
        instructionLabel2.setBounds(71,35,458,25);
        transactionDescLabel.setBounds(72,80,205,25);
        transactionDescField.setBounds(280,75,200,32);
        dateLabel.setBounds(122,118,154,25);
        transactionTypeCombo.setBounds(280,150,100,32);
        transactionTypeLabel.setBounds(140,153,138,25);
        transactionAmountLabel.setBounds(118,193,158,25);
        transactionAmountField.setBounds(280,190,200,32);
        instructionLabel3.setBounds(38,307,524,25);
        instructionLabel4.setBounds(41,330,516,25);
        instructionLabel5.setBounds(81,405,438,25);
        instructionLabel6.setBounds(93,428,414,25);
        addTransactionBtn.setBounds(228,237,145,30);
        viewTransactionsBtn.setBounds(218,365,164,30);
        generateBillBtn.setBounds(280,495,130,30);
        verifiedCheck.setBounds(240,450,120,32);
        
        instructionLabel2.setFont(new Font("Work Sans Semibold",Font.BOLD,20));
        transactionDescLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        transactionDescField.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        datePicker.getJFormattedTextField().setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        dateLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        transactionTypeLabel.setFont(new Font("Work Sans Semibold",Font.PLAIN,15));
        transactionTypeCombo.setFont(new Font("Work Sans", Font.PLAIN,15));
        transactionAmountLabel.setFont(new Font("Work Sans Semibold",Font.PLAIN,15));
        transactionAmountField.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        tableLabel.setFont(new Font("Work Sans Semibold", Font.BOLD,20));
        addTransactionBtn.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        instructionLabel3.setFont(new Font("Work Sans",Font.PLAIN,18));
        instructionLabel4.setFont(new Font("Work Sans",Font.PLAIN,18));
        instructionLabel5.setFont(new Font("Work Sans",Font.PLAIN,18));
        instructionLabel6.setFont(new Font("Work Sans",Font.PLAIN,18));
        viewTransactionsBtn.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        backBtn1.setFont(new Font("Work Sans", Font.PLAIN,15));
        generateBillBtn.setFont(new Font("Work Sans Semibold", Font.PLAIN,15));
        verifiedCheck.setFont(new Font("Work Sans", Font.PLAIN,15));
        
        /*BILL GENERATION PANEL CONFIGURATION*/
        
        billGenerationPanel.setBackground(white);
        billGenerationPanel.add(informationLabel1);
        billGenerationPanel.add(outstandingAmountLabel);
        billGenerationPanel.add(informationLabel2);
        billGenerationPanel.add(interestLabel);
        billGenerationPanel.add(informationLabel3);
        billGenerationPanel.add(billLabel);
        billGenerationPanel.add(exitLabel);
        billGenerationPanel.add(instructionLabel7);
        billGenerationPanel.add(logoutBtn);
        
        
        informationLabel1.setBounds(1,10,370,25);
        outstandingAmountLabel.setBounds(1,40,170,25);
        informationLabel2.setBounds(1,70,350,25);
        interestLabel.setBounds(1,100,200,25);
        informationLabel3.setBounds(1,130,550,25); 
        billLabel.setBounds(1,160,340,25);
        exitLabel.setBounds(1,190,400,25);
        instructionLabel7.setBounds(1,220,450,25);
        logoutBtn.setBounds(1,250,100,32);
        
        informationLabel1.setFont(new Font("Work Sans Semibold", Font.PLAIN,25));
        outstandingAmountLabel.setFont(new Font("Work Sans Semibold", Font.BOLD,32));
        informationLabel2.setFont(new Font("Work Sans Semibold", Font.PLAIN,25));
        interestLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,32));
        informationLabel3.setFont(new Font("Work Sans Semibold", Font.PLAIN,25));
        billLabel.setFont(new Font("Work Sans Semibold", Font.BOLD,32));
        exitLabel.setFont(new Font("Work Sans Semibold", Font.PLAIN,25));
         instructionLabel7.setFont(new Font("Work Sans Semibold", Font.BOLD,25));
           
        mainFrame.setResizable(false);
        mainFrame.setSize(600,600);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        
        //ACTION LISTENERS for JButtons
        nextBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                nextBtn1ActionPerformed(evt);
            }
        });
       
        backBtn1.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent evt) {
               backBtn1ActionPerformed(evt);
           }
        });
        
        addTransactionBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
               addTransactionBtnActionPerformed(evt);
            }
        });
        viewTransactionsBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                viewTransactionsBtnActionPerformed(evt);
            }
        });
        
        generateBillBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                generateBillBtnActionPerformed(evt);
            }
        });
        
      
       
        //KEY LISTENERS for JTextFields
        cvvField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) { 
                if (cvvField.getText().length() >= 3 ) 
                    e.consume();
            }   
        });
       
        cardNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) { 
                if (cardNumberField.getText().length() >= 16 ) 
                    e.consume();
            }   
        });
   }
   /*EVENT HANDLERS*/
    public void nextBtn1ActionPerformed(ActionEvent evt){  
        
        if((cardNumberField.getText().isEmpty())||(nameField.getText().isEmpty())||(cvvField.getText().isEmpty())) {
            JOptionPane.showMessageDialog(emptyFieldsError,"Fields cannot be empty","Empty fields",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if((cardNumberField.getText().length()<16)){
            JOptionPane.showMessageDialog(emptyFieldsError,"Card number cannot "
                    + "be less than 16 digits","Insufficient card numberlength",JOptionPane.ERROR_MESSAGE);
            return;
        }
        st = nameField.getText();
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        mainPanel.add(transactionPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    private void backBtn1ActionPerformed(ActionEvent evt) {
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        mainPanel.add(customerIDPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    public void generateBillBtnActionPerformed(ActionEvent evt) {
        if(rowCounter==0){
            JOptionPane.showMessageDialog(null,"No transactions have been recorded.","Null Data",JOptionPane.ERROR_MESSAGE);
        }
        else {
        if(verifiedCheck.isSelected()==true){
            JOptionPane.showMessageDialog(null,"Verification confirmed. Your bill is bein generated","Verified",JOptionPane.INFORMATION_MESSAGE);
            
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
        
            mainPanel.add(billGenerationPanel);
            mainPanel.repaint();
            mainPanel.revalidate();
            try {
        
                Class.forName("java.sql.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java project?allowMultipleQueries=true","root","sanj");
                Statement stat = conn.createStatement();
                String Query1 = "select sum(`Transaction Amount`) from transactions where `Transaction Type` ='Credit';";
                String Query2 = "select sum(`Transaction Amount`) from transactions where `Transaction Type` ='Debit';";
                
                ResultSet cred = stat.executeQuery(Query1);
                cred.next();
                double cr = cred.getDouble("sum(`Transaction Amount`)");
                
                ResultSet deb = stat.executeQuery(Query2);
                deb.next();
                double de = deb.getDouble("sum(`Transaction Amount`)");
        
                outstandingAmount = de-cr;
                outstandingAmountLabel.setText(Double.toString(outstandingAmount));
                Double interestCalc = outstandingAmount*0.199;
                interestLabel.setText(Double.toString(interestCalc));
                Double billCalc = outstandingAmount+interestCalc;
                billLabel.setText(Double.toString(billCalc));
                
                cred.close();
                deb.close();
                stat.close();
                conn.close();
            } catch(SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
            }
            
            
        }
        else {
             JOptionPane.showMessageDialog(null,"Please verify your data before generating bill","Verification Error",JOptionPane.ERROR_MESSAGE);
        }
        }
    }
    
    private void viewTransactionsBtnActionPerformed(ActionEvent evt) {
        TransactionsTable inst = new TransactionsTable();
        
        inst.setTableModel();
        DefaultTableModel model = (DefaultTableModel)inst.transactionsTable.getModel(); 
        
        try {
        
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java project","root","sanj");
            Statement stat = conn.createStatement();
            String Query = "select * from transactions;";
            ResultSet rs = stat.executeQuery(Query);
        
            while(rs.next())
            {  
                java.sql.Date d = rs.getDate("Date of transaction");
                String de = rs.getString("Transaction description");
                double a = rs.getDouble("Transaction amount");
                String t = rs.getString("Transaction type");
                model.addRow(new Object[]{d,de,a,t});
            }
            
            rowCounter = inst.transactionsTable.getRowCount();
            rs.close();
            stat.close();
            conn.close();
        } catch(SQLException | ClassNotFoundException e) {
                e.printStackTrace();
        }
      inst.tableFrame.setVisible(true);  
    }
    
    private void addTransactionBtnActionPerformed(ActionEvent evt) {
        TransactionsTable inst = new TransactionsTable();
        inst.setTableModel();
        DefaultTableModel model = (DefaultTableModel)inst.transactionsTable.getModel();  
        
        try {
        
            Class.forName("java.sql.Driver");
           // System.out.println("Troll");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java project","root","sanj");
            Statement stat = conn.createStatement();
            String Query = "INSERT INTO `java project`.`transactions` (`Date of transaction`, `Transaction Description`, `Transaction Amount`, `Transaction Type`) "
                + "VALUES ('"+datePicker.getJFormattedTextField().getText()+"','"+transactionDescField.getText()+"','"+Integer.parseInt(transactionAmountField.getText())+"', '"+transactionTypeCombo.getSelectedItem()+"');";
            stat.executeUpdate(Query);
           // System.out.println("Lel");
        
            stat.close();
            conn.close();
        } catch(SQLException | ClassNotFoundException |NumberFormatException e) {
            JOptionPane.showMessageDialog(emptyFieldsError,"Fields cannot be empty.","Empty fields",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*DATE PICKER FORMAT*/
    class DateLabelFormatter extends AbstractFormatter {

        private final String datePattern = "yyyy-MM-dd";
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                String st =  dateFormatter.format(cal.getTime());
                datePicker.getJFormattedTextField().getText();
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
    
    /*TEXT FIELD FILTER*/
    class MyIntFilter extends DocumentFilter {
       
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);
            if(fb.getDocument()==cardNumberField.getDocument()) {
                if (test(sb.toString())) {
                    super.insertString(fb, offset, string, attr);
                }
             }
           else if (fb.getDocument()==cvvField.getDocument()){
                 if(test1(sb.toString())) {
                    super.insertString(fb, offset, string, attr);
                }
            }
        }

        private boolean test(String text) {
            try {
                Long.parseLong(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } 
        
        private boolean test1(String text) {
            try {
                Short.parseShort(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);
            if(fb.getDocument()==cardNumberField.getDocument()) {
                if (test(sb.toString())) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
            else if (fb.getDocument()==cvvField.getDocument()){
                if(test1(sb.toString())) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);
            if(fb.getDocument()==cardNumberField.getDocument()) {
                if(sb.toString().length()==0)
                {
                    super.replace(fb, offset, length, "", null);
                }
                else {
                        if (test(sb.toString())) {
                            super.remove(fb, offset, length);
                        } 
                }
            } 
            else if (fb.getDocument()==cvvField.getDocument()){
                        if(sb.toString().length()==0) {
                            super.replace(fb, offset, length, "", null);
                        }
      
                        else {
                             if (test1(sb.toString())) {
                                 super.remove(fb, offset, length);
                            } 
                        }
            }
        }
    }
}
