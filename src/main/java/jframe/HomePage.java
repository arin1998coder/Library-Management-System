/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author arin1
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    DefaultTableModel model;
    Color mouseEnterColor =new Color(204,255,255); //when mouse h
    Color mouseExitColor =new Color(0,102,102);
    public HomePage() {
        initComponents();
        showPieChart();
        setStudentDetailsToTable();
        setBookDetailsToTable();
        setDataToCards();
    }
    //pie chart code
    public void showPieChart(){
        
        //create dataset
        
        DefaultPieDataset barDataset= new DefaultPieDataset();
        try {
            Connection con=DBConnection.getConnection();
            String sql = "select book_name,count(*) as issue_count from issue_book_details group by book_id";
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                 barDataset.setValue(rs.getString("book_name"),new Double(rs.getDouble("issue_count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Book Count",barDataset, true,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
    }
    
    //method to fetch the data from the db and set the student_details table data in our ui manageStudents table
    public void setStudentDetailsToTable(){
        
        
        try {
            Connection con=DBConnection.getConnection();//trying to establish connection with the db
            Statement st=con.createStatement();//Creates a Statement object for sending SQL statements to the database. 
            //SQL statements without parameters are normally executed using Statement objects
            ResultSet rs= st.executeQuery("select * from student_details");
            
            while(rs.next()){
                //fetch the values from the table and store it inside the var's
                String studentId=rs.getString("student_id");
                String studentName=rs.getString("name");
                String course=rs.getString("course");
                String branch=rs.getString("branch");
                
                Object[] obj={studentId,studentName,course,branch}; //as add row method takes an obj array as parameter, we declare an obj array
                model=(DefaultTableModel)tbl_studentDetails.getModel(); //we are getting a model obj that will represent the studentdetails table
                //using this model obj we can add rows to our studentdetails table
                model.addRow(obj); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     //method to fetch the data from the db and display the book_details table data in our ui managebooks table
    public void setBookDetailsToTable(){
        
        
        try {
            Connection con=DBConnection.getConnection();//trying to establish connection with the db
            Statement st=con.createStatement();//Creates a Statement object for sending SQL statements to the database. 
            //SQL statements without parameters are normally executed using Statement objects
            ResultSet rs= st.executeQuery("select * from book_details");
            
            while(rs.next()){
                //fetch the values from the table and store it inside the var's
                String bookId=rs.getString("book_id");
                String bookName=rs.getString("book_name");
                String author=rs.getString("author");
                int quantity=rs.getInt("quantity");
                
                Object[] obj={bookId,bookName,author,quantity}; //as add row method takes an obj array, we declare an obj array
                model=(DefaultTableModel)tbl_bookDetails.getModel(); //we are getting a model obj that will represent the bookdetails table
                //using this model obj we can add rows to our bookdetails table
                model.addRow(obj); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //set data to the cards
    public void setDataToCards(){
        Statement st=null;
        ResultSet rs=null;
        
        long l=System.currentTimeMillis();
        Date todaysDate=new Date(l);
        
        try {
            Connection con=DBConnection.getConnection();
            st=con.createStatement();
            rs=st.executeQuery("Select count(*) from book_details");
            //it is placing what is returned from the database into a ResultSet
            //which is "A table of data representing a database result set".
            //You then must iterate through the ResultSet to retrieve your answer
            //which is what resultSet.next() and resultSet.getInt(); are doing.

            rs.next();
            int count=rs.getInt(1);
            lbl_noOfBooks.setText(Integer.toString(count)); //displaying the count of no. of books in the label
            
            rs=st.executeQuery("Select count(*) from student_details");
            rs.next();
            count=rs.getInt(1);//gets the count of num of rows in student table
            lbl_noOfStudents.setText(Integer.toString(count)); //displaying the count of no. of students in the label
            
            rs=st.executeQuery("Select count(*) from issue_book_details where status ='"+"pending'");
            rs.next();
            count=rs.getInt(1);//gets the count of num of rows in issue_book_details table whose status is pending
            lbl_issueBooks.setText(Integer.toString(count)); //displaying the count in the issued book label
            
            rs=st.executeQuery("Select count(*) from issue_book_details where status ='"+"pending' and due_date < '"+todaysDate+"'");
            rs.next();
            count=rs.getInt(1);//gets the count of num of rows in issue_book_details table whose status is pending and due date is over
            lbl_defaulterList.setText(Integer.toString(count)); //displaying the count in the defaulter list count label
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        managestudent_panel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        issueBook_panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        managebooks_panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        lbl_noOfBooks = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        lbl_noOfStudents = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        lbl_defaulterList = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        lbl_issueBooks = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_studentDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel27 = new javax.swing.JLabel();
        panelPieChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 250, -1));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_menu_48px_1.png")); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 10, 50));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("The Book Store");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 25)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\male_user_50px.png")); // NOI18N
        jLabel4.setText("Welcome , user");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, 240, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1370, 70));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 204));
        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Exit_26px.png")); // NOI18N
        jLabel2.setText("   Logout");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 40));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 320, 50));

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Home_26px_2.png")); // NOI18N
        jLabel5.setText("    Home Page");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 160, -1));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 320, 70));

        jPanel8.setBackground(new java.awt.Color(0, 204, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Home_26px_2.png")); // NOI18N
        jLabel6.setText("    Home Page");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 160, -1));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 320, 70));

        jPanel9.setBackground(new java.awt.Color(0, 102, 102));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 204));
        jLabel7.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Library_26px_1.png")); // NOI18N
        jLabel7.setText("    Dashboard");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 160, -1));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 320, 50));

        jPanel10.setBackground(new java.awt.Color(0, 102, 102));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 204));
        jLabel8.setText("    Features");
        jPanel10.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 160, -1));

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 320, 50));

        managestudent_panel.setBackground(new java.awt.Color(0, 102, 102));
        managestudent_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managestudent_panelMouseClicked(evt);
            }
        });
        managestudent_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 204));
        jLabel10.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Read_Online_26px.png")); // NOI18N
        jLabel10.setText("   Manage Students");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
        });
        managestudent_panel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 210, -1));

        jPanel5.add(managestudent_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 320, 50));

        issueBook_panel.setBackground(new java.awt.Color(0, 102, 102));
        issueBook_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Sell_26px.png")); // NOI18N
        jLabel11.setText("   Issue Book");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        issueBook_panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 210, -1));

        jPanel5.add(issueBook_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 320, 50));

        jPanel14.setBackground(new java.awt.Color(0, 102, 102));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 204, 204));
        jLabel12.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Return_Purchase_26px.png")); // NOI18N
        jLabel12.setText("   Return Books");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });
        jPanel14.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 210, -1));

        jPanel5.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 320, 60));

        jPanel15.setBackground(new java.awt.Color(0, 102, 102));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 204, 204));
        jLabel13.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_View_Details_26px.png")); // NOI18N
        jLabel13.setText("   View Records");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });
        jPanel15.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 40));

        jPanel5.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 320, 50));

        jPanel16.setBackground(new java.awt.Color(0, 102, 102));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 204, 204));
        jLabel14.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Books_26px.png")); // NOI18N
        jLabel14.setText("   View Issued Books");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });
        jPanel16.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 210, 40));

        jPanel5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 320, 50));

        jPanel17.setBackground(new java.awt.Color(0, 102, 102));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 204, 204));
        jLabel15.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Conference_26px.png")); // NOI18N
        jLabel15.setText("   Defaulters List");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });
        jPanel17.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 210, 40));

        jPanel5.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 320, 60));

        managebooks_panel.setBackground(new java.awt.Color(0, 102, 102));
        managebooks_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managebooks_panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managebooks_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                managebooks_panelMouseExited(evt);
            }
        });
        managebooks_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 204));
        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Books_26px.png")); // NOI18N
        jLabel9.setText("   Manage Books");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        managebooks_panel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 190, -1));

        jPanel5.add(managebooks_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 320, 60));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 710));

        jPanel20.setBackground(new java.awt.Color(204, 255, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel20.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 57, -1, -1));

        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, 210));

        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel20.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Defaulter's List");
        jPanel20.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 204, 204)));
        jPanel24.setForeground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfBooks.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_noOfBooks.setForeground(new java.awt.Color(0, 102, 102));
        lbl_noOfBooks.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Book_Shelf_50px.png")); // NOI18N
        lbl_noOfBooks.setText("20");
        jPanel24.add(lbl_noOfBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, -1));

        jPanel20.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 140, 80));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 204, 204)));
        jPanel25.setForeground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfStudents.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_noOfStudents.setForeground(new java.awt.Color(0, 102, 102));
        lbl_noOfStudents.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_People_50px.png")); // NOI18N
        lbl_noOfStudents.setText("20");
        jPanel25.add(lbl_noOfStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, -1));

        jPanel20.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 140, 80));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 204, 204)));
        jPanel26.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_defaulterList.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_defaulterList.setForeground(new java.awt.Color(0, 102, 102));
        lbl_defaulterList.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_List_of_Thumbnails_50px.png")); // NOI18N
        lbl_defaulterList.setText("20");
        jPanel26.add(lbl_defaulterList, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, -1));

        jPanel20.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 160, 80));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 204, 204)));
        jPanel28.setForeground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_issueBooks.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_issueBooks.setForeground(new java.awt.Color(0, 102, 102));
        lbl_issueBooks.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\adminIcons\\icons8_Sell_50px.png")); // NOI18N
        lbl_issueBooks.setText("20");
        jPanel28.add(lbl_issueBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 40));

        jPanel20.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 150, 80));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("Issued Books");
        jPanel20.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("No. Of Customers");
        jPanel20.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Id", "Name", "Course", "Branch"
            }
        ));
        tbl_studentDetails.setRowHeight(30);
        jScrollPane1.setViewportView(tbl_studentDetails);

        jPanel20.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 620, 210));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 102));
        jLabel24.setText("No. Of Books");
        jPanel20.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 102));
        jLabel26.setText("Student Details");
        jPanel20.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Id", "Name", "Author", "Quantity"
            }
        ));
        tbl_bookDetails.setRowHeight(30);
        jScrollPane2.setViewportView(tbl_bookDetails);

        jPanel20.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 620, 210));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 102));
        jLabel27.setText("Book Details");
        jPanel20.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, 20));

        panelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel20.add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 310, 300, 270));

        getContentPane().add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 1050, 710));

        setSize(new java.awt.Dimension(1402, 810));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        ManageBooks mb=new ManageBooks();
        mb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void managebooks_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managebooks_panelMouseEntered
       
    }//GEN-LAST:event_managebooks_panelMouseEntered

    private void managebooks_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managebooks_panelMouseExited
        
    }//GEN-LAST:event_managebooks_panelMouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        managebooks_panel.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        managebooks_panel.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel9MouseExited

    private void managebooks_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managebooks_panelMouseClicked
        
    }//GEN-LAST:event_managebooks_panelMouseClicked

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
       managestudent_panel.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        managestudent_panel.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel10MouseExited

    private void managestudent_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managestudent_panelMouseClicked
       
    }//GEN-LAST:event_managestudent_panelMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
         ManageStudent student=new ManageStudent();
        student.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        IssueBook issueBook=new IssueBook();
        issueBook.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        issueBook_panel.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
       issueBook_panel.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        ReturnBook returnBook=new ReturnBook();
        returnBook.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        jPanel14.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
        jPanel14.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        ViewAllRecords allRecord=new ViewAllRecords();
        allRecord.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        jPanel15.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
       jPanel15.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        IssuedBookDetails ibd=new IssuedBookDetails();
        ibd.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        jPanel16.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        jPanel16.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        DefaulterList dl=new DefaulterList();
        dl.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jPanel17.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        jPanel17.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel15MouseExited
    //logs out user
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        JOptionPane.showMessageDialog(this, "Logged out successfully");
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        jPanel6.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        jPanel6.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel2MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel issueBook_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_defaulterList;
    private javax.swing.JLabel lbl_issueBooks;
    private javax.swing.JLabel lbl_noOfBooks;
    private javax.swing.JLabel lbl_noOfStudents;
    private javax.swing.JPanel managebooks_panel;
    private javax.swing.JPanel managestudent_panel;
    private javax.swing.JPanel panelPieChart;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private rojeru_san.complementos.RSTableMetro tbl_studentDetails;
    // End of variables declaration//GEN-END:variables
}
