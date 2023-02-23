/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author arin1
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }
    //based on the entered bookId, it fetches the book details from the bookdetails table
    //and displays the book details in the respective text fields of the book_details panel
    public void getBookDetails(){
        int bookId=Integer.parseInt(txt_bookId.getText());
        
        try {
            Connection con=DBConnection.getConnection();
            String sql="select * from book_details where book_id = ?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, bookId); //setting val in the placeholder '?'
            ResultSet rs=pst.executeQuery();
            
            //iterates over every row of the result set
            if(rs.next()){
                //sets the values of the text fields by getting the specified col values from the db
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
            }
            else{
                JOptionPane.showMessageDialog(this,"Invalid Book ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //based on the entered studentId, it fetches the student details from the studentdetails table
    //and displays the student details in the respective text fields of the student_details panel
    public void getStudentDetails(){
        int studentId=Integer.parseInt(txt_studentId.getText());//store the entered Student id in this var
        
        try {
            Connection con=DBConnection.getConnection();
            String sql="select * from student_details where student_id = ?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, studentId); //setting val in the placeholder '?'
            ResultSet rs=pst.executeQuery();
            
            //iterates over every row of the result set
            if(rs.next()){
                //sets the values of the text fields by getting the specified col values from the db
                lbl_studentId.setText(rs.getString("student_id"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            }
            else{
                JOptionPane.showMessageDialog(this,"Invalid Student ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //insert issue book details to db
    public boolean issueBook(){
        boolean isIssued=false;
        int bookId=Integer.parseInt(lbl_bookId.getText());
        int studentId=Integer.parseInt(lbl_studentId.getText());
        String bookName=lbl_bookName.getText();
        String studentName=lbl_studentName.getText();
        Date uIssueDate=date_issueDate.getDatoFecha(); //gets the date from the issueDate field
        Date uDueDate=date_dueDate.getDatoFecha();//gets the date from the dueDate field
        
        //converting util package date to sql package date
        Long l1=uIssueDate.getTime();//converts date to long format
        Long l2=uDueDate.getTime();//converts date to long format
        
        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sdueDate = new java.sql.Date(l2);
        
        try {
            Connection con= DBConnection.getConnection();
            String sql="insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) values (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,bookId);
            pst.setString(2, bookName);
            pst.setInt(3,studentId);
            pst.setString(4,studentName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sdueDate);
            pst.setString(7,"pending"); //by default when book issued, status is pending
            int rowCount= pst.executeUpdate();
            System.out.println(rowCount);
            if(rowCount>0){
                isIssued=true;
            }
            else{
                isIssued=false;
                       
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         return isIssued;
    }
    
    //updating book count
    public void updateBookCount(){
        int bookId=Integer.parseInt(lbl_bookId.getText());
        
        try {
            Connection con=DBConnection.getConnection();
            //decrements the quantity of the book having book id = 'bookId'
            String sql="update book_details set quantity = quantity -1 where book_id = ?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,bookId);
            pst.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //checking whether current book allready allocated to same student or not
    public boolean isAlreadyIssued(){
        boolean isallReadyIssued=false;
        int bookId=Integer.parseInt(lbl_bookId.getText());
        int studentId=Integer.parseInt(lbl_studentId.getText());
        try {
            Connection con=DBConnection.getConnection();
            String sql="select * from issue_book_details where student_id=? and book_id=? and status = ?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,studentId);
            pst.setInt(2, bookId);
            pst.setString(3, "pending");
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                isallReadyIssued=true;
            }
            else{
                isallReadyIssued=false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isallReadyIssued;
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(204, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));

        jLabel11.setBackground(new java.awt.Color(0, 204, 204));
        jLabel11.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\AddNewBookIcons\\icons8_Rewind_48px.png")); // NOI18N
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 255, 255));
        jLabel13.setText("Quantity");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 120, -1));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(153, 255, 255));
        jPanel3.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 200, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 255, 255));
        jLabel15.setText("Book Name");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 120, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 255, 255));
        jLabel16.setText("Author");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 120, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 255, 255));
        jLabel17.setText("Book ID");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 90, -1));

        lbl_bookId.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(153, 255, 255));
        jPanel3.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 200, 30));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(153, 255, 255));
        jPanel3.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 200, 30));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(153, 255, 255));
        jPanel3.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 200, 30));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\AddNewBookIcons\\icons8_Literature_100px_1.png")); // NOI18N
        jLabel19.setText(" Book Details");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 260, -1));

        jPanel9.setBackground(new java.awt.Color(153, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 310, 5));

        panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 800));

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\AddNewBookIcons\\icons8_Student_Registration_100px_2.png")); // NOI18N
        jLabel2.setText(" Student Details");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 300, -1));

        jPanel7.setBackground(new java.awt.Color(153, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 310, 5));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 255, 255));
        jLabel4.setText("Branch");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 120, -1));

        lbl_branch.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(153, 255, 255));
        jPanel4.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 200, 30));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 255, 255));
        jLabel8.setText("Student Name");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 140, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 255, 255));
        jLabel9.setText("Course");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 120, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 255, 255));
        jLabel10.setText("Student ID");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, -1));

        lbl_studentId.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_studentId.setForeground(new java.awt.Color(153, 255, 255));
        jPanel4.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 200, 30));

        lbl_course.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(153, 255, 255));
        jPanel4.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 200, 30));

        lbl_studentName.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_studentName.setForeground(new java.awt.Color(153, 255, 255));
        jPanel4.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 200, 30));

        panel_main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 380, 800));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\AddNewBookIcons\\icons8_Student_Registration_100px_2.png")); // NOI18N
        jLabel14.setText(" Student Details");
        panel_main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 300, -1));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\AddNewBookIcons\\icons8_Student_Registration_100px_2.png")); // NOI18N
        jLabel18.setText(" Student Details");
        panel_main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 300, -1));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\AddNewBookIcons\\icons8_Books_52px_1.png")); // NOI18N
        jLabel12.setText(" Issue Book");
        panel_main.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 220, -1));

        jPanel8.setBackground(new java.awt.Color(153, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        panel_main.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 310, 5));

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        panel_main.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 190, -1, 5));

        txt_bookId.setBackground(new java.awt.Color(204, 255, 255));
        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_bookId.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book Id");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 290, 340, -1));

        jLabel26.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 0, 153));
        jLabel26.setText("Book Id");
        panel_main.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, 160, 40));

        jLabel27.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 0, 153));
        jLabel27.setText("Issue Date");
        panel_main.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 450, 120, 40));

        txt_studentId.setBackground(new java.awt.Color(204, 255, 255));
        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_studentId.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_studentId.setPlaceholder("Enter Student Id");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 360, 340, -1));

        date_issueDate.setBackground(new java.awt.Color(0, 204, 204));
        date_issueDate.setForeground(new java.awt.Color(204, 255, 255));
        date_issueDate.setPlaceholder("Select Issue Date");
        panel_main.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 450, 350, -1));

        jLabel28.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 0, 153));
        jLabel28.setText("Student Id");
        panel_main.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 360, 160, 40));

        jLabel29.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 0, 153));
        jLabel29.setText("Due Date");
        panel_main.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 530, 120, 40));

        date_dueDate.setBackground(new java.awt.Color(0, 204, 204));
        date_dueDate.setForeground(new java.awt.Color(204, 255, 255));
        date_dueDate.setPlaceholder("Select Due Date");
        panel_main.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 530, 350, -1));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(0, 102, 102));
        rSMaterialButtonCircle3.setText("Issue Book");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 640, 350, 70));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 800));

        setSize(new java.awt.Dimension(1402, 810));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
       HomePage home =new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
          if(!txt_bookId.getText().equals("")) {
              getBookDetails();
          }
                
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
             if(!txt_studentId.getText().equals("")) {
              getStudentDetails();
          }
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        int quantity=Integer.parseInt(lbl_quantity.getText());
        //if the current book id is not allready issued to the student and book's quantity is not <=0
        //then only issue him the curr book
        
        if(isAlreadyIssued()==false && quantity>0 ){
            if(issueBook()){
                updateBookCount(); //quantity of book is decremented 
                getBookDetails();  //get the updated bookdetails from the db and show in the bookdetails panel with quantity updated
                JOptionPane.showMessageDialog(this, "Book Issued Successfully");
            }
            else{
                JOptionPane.showMessageDialog(this, "Book Issue Failed");
            }
              
        }
        else{
            if(quantity<=0){
                    JOptionPane.showMessageDialog(this, "Book is not Available");
                }
            else{
                JOptionPane.showMessageDialog(this, "Book is allready Issued to Student");
            }
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private javax.swing.JPanel panel_main;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
