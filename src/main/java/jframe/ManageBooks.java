/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author arin1
 */
public class ManageBooks extends javax.swing.JFrame {

    /**
     * Creates new form ManageBooks
     */
    String bookName,author;
    int bookId,quantity;
    DefaultTableModel model;
    public ManageBooks() {
        initComponents();
        setBookDetailsToTable();
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
    
    //to add books to book_details table
    public boolean addBook(){
        boolean isAdded=false;
        //fetching the entered book details in the text fields and storing them in the respective variables
        bookId=Integer.parseInt(txt_bookid.getText());
        bookName=txt_bookname.getText();
        author=txt_authorname.getText();
        quantity=Integer.parseInt(txt_quantity.getText());
        
        try {
            Connection con=DBConnection.getConnection();
            String sql="insert into book_details values(?,?,?,?)";
            PreparedStatement pst=con.prepareStatement( sql);
            //filling the placeholders with the values stored in the respective variables
            pst.setInt(1,bookId);
            pst.setString(2, bookName);
            pst.setString(3, author);
            pst.setInt(4,quantity);
            
            int rowCount=pst.executeUpdate();
            //if new book is added,rowcount is =1
            if(rowCount>0){
                isAdded=true;
            }
            else{
                isAdded=false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }
    
    //to update book_details table
    public boolean updateBook(){
        boolean isUpdated=false;
        //fetching the entered book details in the text fields and storing them in the respective variables
        bookId=Integer.parseInt(txt_bookid.getText());
        bookName=txt_bookname.getText();
        author=txt_authorname.getText();
        quantity=Integer.parseInt(txt_quantity.getText());
        
        try {
            Connection con=DBConnection.getConnection();
            String sql="update book_details set book_name = ?,author= ?,quantity = ? where book_id = ?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, bookName);
            pst.setString(2, author);
            pst.setInt(3,quantity);
            pst.setInt(4,bookId);
            
            int affectedrow=pst.executeUpdate();
            
            //if record updated successfully, true
            if(affectedrow>0){
                isUpdated=true;
            }
            //record updattion failed, false
            else{
                isUpdated=false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }
    
    //delete a particular record from the book_details table
    public boolean deleteBook(){
        boolean isDeleted=false;
        bookId=Integer.parseInt(txt_bookid.getText());
        try {
            Connection con=DBConnection.getConnection();
            String sql="delete from book_details where book_id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
            int affectedRow=pst.executeUpdate();
            //if record deleted sucessfully
            if(affectedRow>0){
                isDeleted=true;
            }
            //record deletion failed
            else{
                isDeleted=false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
    
    //clears the table
    public void clearTable(){
        DefaultTableModel model=(DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0); //this will remove all the row from the table in UI
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
        txt_bookid = new app.bolivia.swing.JCTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_bookname = new app.bolivia.swing.JCTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_authorname = new app.bolivia.swing.JCTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txt_quantity = new app.bolivia.swing.JCTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_bookid.setBackground(new java.awt.Color(204, 255, 255));
        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_bookid.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_bookid.setPlaceholder("Enter Username");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 270, -1));

        jLabel26.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 0, 153));
        jLabel26.setText("Enter Book Id");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 130, 40));

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 0, 153));
        jLabel25.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Account_50px.png")); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 60, 50));

        txt_bookname.setBackground(new java.awt.Color(204, 255, 255));
        txt_bookname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_bookname.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_bookname.setPlaceholder("Enter Username");
        txt_bookname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_booknameFocusLost(evt);
            }
        });
        txt_bookname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_booknameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 270, -1));

        jLabel27.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 0, 153));
        jLabel27.setText("Enter Book Name");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 150, 40));

        jLabel28.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 0, 153));
        jLabel28.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Account_50px.png")); // NOI18N
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 60, 50));

        txt_authorname.setBackground(new java.awt.Color(204, 255, 255));
        txt_authorname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_authorname.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_authorname.setPlaceholder("Enter Username");
        txt_authorname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_authornameFocusLost(evt);
            }
        });
        txt_authorname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_authornameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_authorname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 270, -1));

        jLabel29.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 0, 153));
        jLabel29.setText("Author Name");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 130, 40));

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 0, 153));
        jLabel30.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Account_50px.png")); // NOI18N
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 60, 50));

        txt_quantity.setBackground(new java.awt.Color(204, 255, 255));
        txt_quantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_quantity.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_quantity.setPlaceholder("Enter Username");
        txt_quantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_quantityFocusLost(evt);
            }
        });
        txt_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityActionPerformed(evt);
            }
        });
        jPanel1.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 270, -1));

        jLabel31.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 0, 153));
        jLabel31.setText("Quantity");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 110, 40));

        jLabel32.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 0, 153));
        jLabel32.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Account_50px.png")); // NOI18N
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 60, 50));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(0, 102, 102));
        rSMaterialButtonCircle1.setText("Delete");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 610, 140, 60));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(0, 102, 102));
        rSMaterialButtonCircle2.setText("Update");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 610, 140, 60));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(0, 102, 102));
        rSMaterialButtonCircle3.setText("Add");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, 140, 60));

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\AddNewBookIcons\\icons8_Rewind_48px.png")); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 820));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Id", "Name", "Author", "Quantity"
            }
        ));
        tbl_bookDetails.setRowHeight(30);
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_bookDetails);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 730, 250));
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 0, 153));
        jLabel8.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\AddNewBookIcons\\icons8_Book_50px_1.png")); // NOI18N
        jLabel8.setText("  Manage Books");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 250, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 870, 820));

        setSize(new java.awt.Dimension(1402, 810));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost

    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_booknameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_booknameFocusLost

    }//GEN-LAST:event_txt_booknameFocusLost

    private void txt_booknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_booknameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_booknameActionPerformed

    private void txt_authornameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_authornameFocusLost

    }//GEN-LAST:event_txt_authornameFocusLost

    private void txt_authornameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_authornameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authornameActionPerformed

    private void txt_quantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_quantityFocusLost

    }//GEN-LAST:event_txt_quantityFocusLost

    private void txt_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        
        if(deleteBook()){
            JOptionPane.showMessageDialog(this, "Book Deleted");
            clearTable();//cleats the table
            setBookDetailsToTable();//fetch the bookdetails table from db and display in our UI
        }
        else{
             JOptionPane.showMessageDialog(this, "Book Deletion Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if(updateBook()){
            JOptionPane.showMessageDialog(this, "Book Details Updated");
            clearTable();//cleats the table
            setBookDetailsToTable();//fetch the bookdetails table from db and display in our UI
        }
        else{
             JOptionPane.showMessageDialog(this, "Book Details Updation Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if(addBook()){
            JOptionPane.showMessageDialog(this, "Book Added");
            clearTable();//cleats the table
            setBookDetailsToTable();//fetch the bookdetails table from db and display in our UI
        }
        else{
             JOptionPane.showMessageDialog(this, "Book Addition Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage home =new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
       
        int rowNo=tbl_bookDetails.getSelectedRow();
        //by using this model we can get the bookdetails from the table and set the values in the respective textfield in sidebar
        TableModel model=tbl_bookDetails.getModel();
        
        //when a specific row no. of the book_details table is clicked
        //records in that row num is displayed in the specified text fields in the sidebar
        txt_bookid.setText(model.getValueAt(rowNo, 0).toString());
        txt_bookname.setText(model.getValueAt(rowNo, 1).toString());
        txt_authorname.setText(model.getValueAt(rowNo, 2).toString());
        txt_quantity.setText(model.getValueAt(rowNo, 3).toString());
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

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
            java.util.logging.Logger.getLogger(ManageBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField txt_authorname;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_bookname;
    private app.bolivia.swing.JCTextField txt_quantity;
    // End of variables declaration//GEN-END:variables
}
