/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author arin1
 */
public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form SignupPage
     */
    public LoginPage() {
        initComponents();
    }
    
    //validate this login page
    public boolean validateLogin(){
        String name=txt_username.getText();
        String pwd=txt_password.getText();
        
        //if emptry username provided, then give validation msg
        if(name.equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter Username");
            return false;
        }
        //if empty password provided
        if(pwd.equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter password");
            return false;
        }
        return true;
    }
    
    //verify login credentials
    public void checkLoginCreds(){
        String name=txt_username.getText();
        String pwd=txt_password.getText();
        
        try {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select * from users where name=? and password =?");
            pst.setString(1, name);
            pst.setString(2, pwd);
            
           ResultSet rs= pst.executeQuery(); //stores the result fetched from the sql query, in this case usernmae and password
           //if the entered username and password is valid, that is credentials matches,homepage of the user will open up
           if(rs.next()){
               JOptionPane.showMessageDialog(this, "Login Successfull");
               HomePage home=new HomePage();
               home.setVisible(true); //display the homepage frame
               this.dispose(); //close the current frame
           }
           else{
               JOptionPane.showMessageDialog(this, "Login Failed! Please provide valid credentials");
           }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jCTextField2 = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jCTextField3 = new app.bolivia.swing.JCTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jCTextField4 = new app.bolivia.swing.JCTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_username = new app.bolivia.swing.JCTextField();
        jLabel26 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        txt_password = new javax.swing.JPasswordField();
        chk_box = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 153));
        jLabel2.setText("THE BOOK STORE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 250, 40));

        jLabel3.setFont(new java.awt.Font("Snap ITC", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 204));
        jLabel3.setText("NOOB");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Snap ITC", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("DEVELOPER");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Snap ITC", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("DEVELOPER");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel6.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 204));
        jLabel6.setText("Welcome To");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 190, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\library-3.png.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 740, 660));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 850, 780));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 0, 153));
        jLabel8.setText("Login Page");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 140, 40));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 0, 153));
        jLabel9.setText("Login To Your Account");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 190, 40));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 0, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Account_50px.png")); // NOI18N
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 60, 50));

        jLabel12.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 0, 153));
        jLabel12.setText("Signup Page");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 250, 40));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 0, 153));
        jLabel13.setText("Create New Account");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 300, 40));

        jCTextField2.setBackground(new java.awt.Color(204, 255, 255));
        jCTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jCTextField2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jCTextField2.setPlaceholder("Enter Username");
        jCTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jCTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 0, 153));
        jLabel14.setText("Username");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 110, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 340, 650));

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 0, 153));
        jLabel15.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Account_50px.png")); // NOI18N
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 60, 50));

        jLabel16.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 0, 153));
        jLabel16.setText("Signup Page");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 250, 40));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 0, 153));
        jLabel17.setText("Create New Account");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 300, 40));

        jCTextField3.setBackground(new java.awt.Color(204, 255, 255));
        jCTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jCTextField3.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jCTextField3.setPlaceholder("Enter Username");
        jCTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCTextField3ActionPerformed(evt);
            }
        });
        jPanel4.add(jCTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 0, 153));
        jLabel18.setText("Username");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 110, 40));

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 0, 153));
        jLabel19.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Account_50px.png")); // NOI18N
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 60, 50));

        jLabel20.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 0, 153));
        jLabel20.setText("Signup Page");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 250, 40));

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 0, 153));
        jLabel21.setText("Create New Account");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 300, 40));

        jCTextField4.setBackground(new java.awt.Color(204, 255, 255));
        jCTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jCTextField4.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jCTextField4.setPlaceholder("Enter Username");
        jCTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCTextField4ActionPerformed(evt);
            }
        });
        jPanel5.add(jCTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel22.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 0, 153));
        jLabel22.setText("Username");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 110, 40));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 340, 650));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 340, 650));

        jLabel23.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 0, 153));
        jLabel23.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Secure_50px.png")); // NOI18N
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 60, 50));

        jLabel24.setFont(new java.awt.Font("Verdana", 2, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Show Password");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 140, 40));

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 0, 153));
        jLabel25.setIcon(new javax.swing.ImageIcon("D:\\Projects\\LibraryManagementSys\\src\\main\\java\\icons\\icons8_Account_50px.png")); // NOI18N
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 60, 50));

        txt_username.setBackground(new java.awt.Color(204, 255, 255));
        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_username.setPlaceholder("Enter Username");
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 230, -1));

        jLabel26.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 0, 153));
        jLabel26.setText("Username");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 110, 40));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(0, 102, 102));
        rSMaterialButtonCircle1.setText("LOG IN");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 260, 60));

        rSMaterialButtonCircle2.setText("SIGN UP");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 260, 60));

        txt_password.setBackground(new java.awt.Color(204, 255, 255));
        txt_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 230, 30));

        chk_box.setBackground(new java.awt.Color(204, 255, 255));
        chk_box.setForeground(new java.awt.Color(204, 255, 255));
        chk_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_boxActionPerformed(evt);
            }
        });
        jPanel2.add(chk_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, -1, -1));

        jLabel31.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 0, 153));
        jLabel31.setText("Password");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 110, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 380, 780));

        setSize(new java.awt.Dimension(1262, 810));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCTextField2ActionPerformed

    private void jCTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCTextField3ActionPerformed

    private void jCTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCTextField4ActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
       if(validateLogin()){
           checkLoginCreds();
       }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
          //redirects user to the signup page when clicked on signup
          SignupPage sp=new SignupPage();
          sp.setVisible(true);
          this.dispose();
        
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed
    
    private void chk_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_boxActionPerformed
        // TODO add your handling code here:
        //when checkbox is selected - pasword is visible
        if(chk_box.isSelected()){
            txt_password.setEchoChar((char)0);
        }
        //pass not visible- when chkbox not slected
        else{
            txt_password.setEchoChar('*');
        }
    }//GEN-LAST:event_chk_boxActionPerformed

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
            
    }//GEN-LAST:event_txt_usernameFocusLost

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chk_box;
    private app.bolivia.swing.JCTextField jCTextField2;
    private app.bolivia.swing.JCTextField jCTextField3;
    private app.bolivia.swing.JCTextField jCTextField4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private javax.swing.JPasswordField txt_password;
    private app.bolivia.swing.JCTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
